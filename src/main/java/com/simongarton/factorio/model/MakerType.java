package com.simongarton.factorio.model;

public enum MakerType {

    ASSEMBLER("Assembler"),
    FURNACE("Furnace"),
    REFINERY("Refinery");

    private final String type;

    MakerType(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
