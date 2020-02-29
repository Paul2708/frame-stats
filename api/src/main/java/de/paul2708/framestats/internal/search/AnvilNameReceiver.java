package de.paul2708.framestats.internal.search;

import de.paul2708.framestats.internal.search.anvil.AnvilGUI;
import de.paul2708.framestats.internal.search.anvil.AnvilSlot;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * This name receiver uses an anvil gui to read in the searched name.
 *
 * @author Paul2708
 */
public final class AnvilNameReceiver implements NameReceiver {

    /**
     * Receive the searched user by the user.
     * The consumer accepts the searched string.
     *
     * @param player   player that searches
     * @param consumer consumer that accepts the search objective
     */
    @Override
    public void receive(Player player, Consumer<String> consumer) {
        AnvilGUI gui = new AnvilGUI(player, (event) -> {
            if (event.getSlot() == AnvilSlot.OUTPUT) {
                event.setWillDestroy(true);
                event.setWillClose(true);

                consumer.accept(event.getName());
            }
        });

        gui.setSlot(AnvilSlot.INPUT_LEFT, new ItemStack(Material.PAPER));
        gui.open();
    }
}