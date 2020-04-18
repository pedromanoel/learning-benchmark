package io.pedromanoel.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.Collection;

@State(Scope.Benchmark)
public class ListExecutionPlan {

    public Collection<Element> collection;

    @Param({"ARRAY",
            "LINKED",
            "COPY_ON_WRITE_ARRAY",
            "SYNC_ARRAY",
            "SYNC_LINKED"})
    private ListType listType;

    @Param("1000000")
    private int numberOfElements;

    @Param("10")
    private int maximumGroupSize;

    @Param("0.05")
    private double probabilityOfGroupedElements;

    @Setup(Level.Invocation)
    public void setUp() {
        collection = listType.getNewCollection();

        Collection<Element> elements =
                new ElementFactory(
                        probabilityOfGroupedElements,
                        maximumGroupSize
                ).generate(numberOfElements);

        collection.addAll(elements);
    }

}
