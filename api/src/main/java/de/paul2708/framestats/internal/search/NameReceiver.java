package de.paul2708.framestats.internal.search;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

/**
 * This interface is used to receive the searching string from the player.
 *
 * @author Paul2708
 */
@FunctionalInterface
public interface NameReceiver {

    /**
     * Receive the searched user by the user.
     * The consumer accepts the searched string.
     *
     * @param player player that searches
     * @param consumer consumer that accepts the search objective
     */
    void receive(Player player, Consumer<String> consumer);

    /**
     * Get a dummy receiver that accepts the player name itself every time.
     *
     * @return dummy name receiver
     */
    static NameReceiver dummyReceiver() {
        return (player, consumer) -> consumer.accept(player.getName());
    }

    /**
     * Get an anvil receiver instance.
     *
     * @return anvil name receiver
     */
    static NameReceiver anvilReceiver() {
        return new AnvilNameReceiver();
    }
}