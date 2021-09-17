package com.neathorium.thorium.framework.selenium.records.lazy;

import java.util.Objects;

public class CachedLookupKeysData {
    public final String name;
    public final String entryName;
    public final String strategy;
    public final int index;

    public CachedLookupKeysData(String name, String entryName, String strategy, int index) {
        this.name = name;
        this.entryName = entryName;
        this.strategy = strategy;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (CachedLookupKeysData) o;
        return index == that.index && Objects.equals(name, that.name) && Objects.equals(entryName, that.entryName) && Objects.equals(strategy, that.strategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, entryName, strategy, index);
    }
}
