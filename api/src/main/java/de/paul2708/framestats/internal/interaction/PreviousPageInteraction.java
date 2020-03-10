package de.paul2708.framestats.internal.interaction;

import de.paul2708.framestats.configuration.TableConfiguration;
import de.paul2708.framestats.internal.image.calculator.PageBar;
import de.paul2708.framestats.internal.image.calculator.PageBarCalculator;
import de.paul2708.framestats.internal.state.PageShift;
import de.paul2708.framestats.table.Table;
import org.bukkit.entity.Player;

import java.awt.Shape;

public final class PreviousPageInteraction implements TableInteraction {

    private final PageBar pageBar;

    // TODO: Rename classes

    /**
     * Create a new search interaction and calculate the needed position.
     *
     * @param configuration table configuration
     */
    public PreviousPageInteraction(TableConfiguration configuration) {
        PageBarCalculator calculator = new PageBarCalculator(configuration);
        calculator.calculate();

        this.pageBar = calculator.result();
    }

    /**
     * Run the interaction, if the player clicks on the {@link #position()}.
     *
     * @param player player that interacts with the table
     * @param table  table that the player clicked
     */
    @Override
    public void interact(Player player, Table table) {
        table.getState(player).changePage(PageShift.PREVIOUS);

        player.sendMessage("Go back one page");
    }

    /**
     * Get the trigger position.
     * The coordinate system stats in the left upper corner (0,0).
     *
     * @return trigger position
     */
    @Override
    public Shape position() {
        return pageBar.getBack();
    }
}