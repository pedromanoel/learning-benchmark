package io.pedromanoel.benchmark;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;

import static java.util.Collections.synchronizedCollection;

public enum ListType {
    ARRAY(ArrayList::new),
    LINKED(LinkedList::new),
    COPY_ON_WRITE_ARRAY(CopyOnWriteArrayList::new),
    SYNC_ARRAY(() -> synchronizedCollection(new ArrayList<>())),
    SYNC_LINKED(() -> synchronizedCollection(new LinkedList<>()));

    private final Supplier<Collection<Element>> supplier;

    ListType(Supplier<Collection<Element>> supplier) {
        this.supplier = supplier;
    }

    public Collection<Element> getNewCollection() {
        return supplier.get();
    }
}
