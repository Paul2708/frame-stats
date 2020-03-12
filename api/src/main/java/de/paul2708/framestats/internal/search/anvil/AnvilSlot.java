package de.paul2708.framestats.internal.search.anvil;

import java.util.Arrays;

public enum AnvilSlot {

    INPUT_LEFT(0),
    INPUT_RIGHT(1),
    OUTPUT(2);

    private final int slot;

    AnvilSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    public static AnvilSlot bySlot(int slot) {
        return Arrays.stream(values()).filter((data) -> data.slot == slot).findFirst().orElse(null);
    }
}
