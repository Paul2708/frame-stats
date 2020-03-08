package de.paul2708.framestats.internal.state;

import de.paul2708.framestats.internal.TableView;
import de.paul2708.framestats.table.Table;
import de.paul2708.framestats.table.TableRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class holds a table state of a player.
 * This includes data like current page number and methods to handle page changing.
 * It also holds a set of subscribed views that will be updated on state changes.
 *
 * @author Paul2708
 */
public final class TableState {

    // General table properties
    private final Table table;
    private final int rows;

    // State properties
    private List<TableRow> content;
    private int page;
    private String searchTerm;

    // Subscribed table views
    private Set<TableView> views;

    /**
     * Create a new table state and initialize the state with default values.
     *
     * @param table based table
     */
    private TableState(Table table) {
        this.table = table;
        this.rows = table.getConfiguration().getRows() - 1;

        this.content = new ArrayList<>(table.getContent());
        this.page = 1;
        // TODO: Replace all "Suche.."
        this.searchTerm = "Suche..";

        this.views = new HashSet<>();
    }

    /**
     * Set the table state content.
     *
     * @param changedContent changed table rows
     */
    public void setContent(List<TableRow> changedContent) {
        this.content = Collections.unmodifiableList(changedContent);
    }

    /**
     * Change the players table page.
     *
     * @param pageShift page shift
     */
    public void changePage(PageShift pageShift) {
        int delta = pageShift.getDelta();

        // Lower page bound
        if (page + delta < 1) {
            return;
        }
        // TODO: Check upper page bound
        // TODO: Fix back page?

        this.page += delta;
        this.content = content.stream().skip((page - 1) * rows).collect(Collectors.toList());

        for (TableView view : views) {
            view.drawContent();
        }
    }

    /**
     * Set the searching term the player searches for.
     *
     * @param searchTerm searching term
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;

        for (TableView view : views) {
            view.drawSearch();
        }
    }

    /**
     * Subscribe to the state.
     * If the state changes, the view will be updated and re-drawn.
     *
     * @param view table view
     */
    public void addView(TableView view) {
        this.views.add(view);
    }

    /**
     * Get the base table.
     *
     * @return table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get the state content.
     *
     * @return list of rows
     */
    public List<TableRow> getContent() {
        return content;
    }

    /**
     * Get the searching term.
     *
     * @return searching term
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * Create a new table state.
     *
     * @param table base table
     * @return created table state
     */
    public static TableState create(Table table) {
        return new TableState(table);
    }
}