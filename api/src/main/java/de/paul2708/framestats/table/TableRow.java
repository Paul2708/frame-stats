package de.paul2708.framestats.table;

import java.util.Arrays;
import java.util.Objects;

/**
 * A table row is a list of strings.
 * Note: The count of columns is not checked.
 * So make sure to use the correct count of strings.
 *
 * @author Paul2708
 */
public final class TableRow {

    private final String[] entries;

    /**
     * Create a new immutable table row.
     * Note that the class will not check the amount of columns.
     *
     * @param entries row entries, be sure to use the correct amount of strings
     */
    public TableRow(String... entries) {
        Objects.requireNonNull(entries);

        this.entries = entries;
    }

    /**
     * Create a new immutable table row.
     * Note that the class will not check the amount of columns.
     * Every object will call {@link #toString()}, so make sure that the method is implemented.
     *
     * @param entries row entries, be sure to use the correct amount of objects
     */
    public TableRow(Object... entries) {
        Objects.requireNonNull(entries);

        this.entries = Arrays.stream(entries).map(Object::toString).toArray(String[]::new);
    }

    /**
     * Get the row entries.
     *
     * @return row entries
     */
    public String[] getEntries() {
        return entries;
    }

    /**
     * Two table rows are equal, if every row entry are equal.
     *
     * @param o object to check
     * @return true if the rows are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TableRow tableRow = (TableRow) o;

        return Arrays.equals(entries, tableRow.entries);
    }

    /**
     * Get the hash code.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(entries);
    }

    /**
     * Get the row as string.
     *
     * @return row entries
     */
    @Override
    public String toString() {
        return "TableRow{"
                + "entries=" + Arrays.toString(entries)
                + '}';
    }
}