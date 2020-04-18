package io.pedromanoel.benchmark;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Collections.synchronizedMap;

public enum MapType {

    HASH(HashMap::new),
    CONCURRENT(ConcurrentHashMap::new),
    LINKED(LinkedHashMap::new),
    SYNCED_HASH(() -> synchronizedMap(new HashMap<>())),
    SYNCED_LINKED(() -> synchronizedMap(new LinkedHashMap<>()));

    private final Supplier<Map<UUID, Collection<Element>>> supplier;

    MapType(Supplier<Map<UUID, Collection<Element>>> supplier) {
        this.supplier = supplier;
    }

    public Map<UUID, Collection<Element>> getNewMap() {
        return supplier.get();
    }
}
