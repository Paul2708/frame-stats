package de.paul2708.framestats.internal.search.anvil;

import de.paul2708.framestats.TablePluginHook;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class AnvilGUI {

    private Player player;

    private AnvilClickEventHandler handler;

    private HashMap<AnvilSlot, ItemStack> items = new HashMap<>();

    private Inventory inv;

    private Listener listener;

    public AnvilGUI(Player player, final AnvilClickEventHandler handler) {
        this.player = player;
        this.handler = handler;

        this.listener = new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player) {
                    Player clicker = (Player) event.getWhoClicked();

                    if (event.getInventory().equals(inv)) {
                        event.setCancelled(true);

                        ItemStack item = event.getCurrentItem();
                        int slot = event.getRawSlot();
                        String name = "";

                        if (item != null) {
                            if (item.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();

                                if (meta.hasDisplayName()) {
                                    name = meta.getDisplayName();
                                }
                            }
                        }

                        AnvilClickEvent clickEvent = new AnvilClickEvent(AnvilSlot.bySlot(slot), name);

                        handler.onAnvilClick(clickEvent);

                        if (clickEvent.getWillClose())
                            event.getWhoClicked().closeInventory();

                        if (clickEvent.getWillDestroy())
                            destroy();
                    }
                }
            }

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    Player player = (Player) event.getPlayer();
                    Inventory inv = event.getInventory();

                    if (inv.equals(AnvilGUI.this.inv)) {
                        inv.clear();
                        destroy();
                    }
                }
            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                if (event.getPlayer().equals(getPlayer())) {
                    destroy();
                }
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, TablePluginHook.getPlugin());
    }

    public Player getPlayer() {
        return player;
    }

    public void setSlot(AnvilSlot slot, ItemStack item) {
        items.put(slot, item);
    }

    public void open() {
        EntityPlayer p = ((CraftPlayer) player).getHandle();

        AnvilContainer container = new AnvilContainer(p);

        inv = container.getBukkitView().getTopInventory();

        items.keySet().forEach((slot) -> inv.setItem(slot.getSlot(), items.get(slot)));

        int c = p.nextContainerCounter();

        p.playerConnection.sendPacket(new PacketPlayOutOpenWindow(c, "minecraft:anvil", new ChatMessage("Repairing"), 0));

        //Set their active container to the container
        p.activeContainer = container;

        //Set their active container window id to that counter stuff
        p.activeContainer.windowId = c;

        //Add the slot listener
        p.activeContainer.addSlotListener(p);
    }

    public void destroy() {
        player = null;
        handler = null;
        items = null;

        HandlerList.unregisterAll(listener);

        listener = null;
    }

    private static class AnvilContainer extends ContainerAnvil {
        public AnvilContainer(EntityHuman entity) {
            super(entity.inventory, entity.world, new BlockPosition(0, 0, 0), entity);
        }

        @Override
        public boolean a(EntityHuman entityhuman) {
            return true;
        }
    }

}
