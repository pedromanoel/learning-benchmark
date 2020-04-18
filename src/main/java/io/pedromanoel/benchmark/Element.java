package io.pedromanoel.benchmark;

import java.util.Objects;
import java.util.UUID;

class Element {
    private final UUID uuid;
    private final int group;

    Element(UUID uuid, int group) {
        this.uuid = Objects.requireNonNull(uuid);
        this.group = group;
    }

    public UUID getKey() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, group);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Element)) {
            return false;
        }

        Element other = (Element) o;

        return group == other.group
                && Objects.equals(uuid, other.uuid);
    }

    @Override
    public String toString() {
        return String.format("Element(%d, %s)", group, uuid);
    }
}
