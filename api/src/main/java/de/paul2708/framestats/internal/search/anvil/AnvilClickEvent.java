package de.paul2708.framestats.internal.search.anvil;

public class AnvilClickEvent {

    private final AnvilSlot slot;

    private final String name;

    private boolean close;
    private boolean destroy;

    public AnvilClickEvent(AnvilSlot slot, String name) {
        this.slot = slot;
        this.name = name;

        this.close = true;
        this.destroy = true;
    }

    public AnvilSlot getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public boolean getWillClose() {
        return close;
    }

    public void setWillClose(boolean close) {
        this.close = close;
    }

    public boolean getWillDestroy() {
        return destroy;
    }

    public void setWillDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
