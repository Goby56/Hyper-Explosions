package com.alpha.hyperexplosions.property;

import net.minecraft.util.StringIdentifiable;

public enum NukePart implements StringIdentifiable {
    HEAD("head"),
    FIN("fin");

    private final String name;

    NukePart(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
