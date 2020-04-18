package io.pedromanoel.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class MapExecutionPlan {

    public Map<UUID, Collection<Element>> map;

    @Param({
            "HASH",
            "CONCURRENT",
            "LINKED",
            "SYNCED_HASH",
            "SYNCED_LINKED"})
    private MapType mapType;

    @Param("1000000")
    private int numberOfElements;

    @Param("10")
    private int maximumGroupSize;

    @Param("0.05")
    private double probabilityOfGroupedElements;

    @Setup(Level.Invocation)
    public void setUp() {
        map = mapType.getNewMap();

        Map<UUID, List<Element>> grouped = new ElementFactory(
                probabilityOfGroupedElements,
                maximumGroupSize
        ).generate(numberOfElements)
                .stream()
                .collect(Collectors.groupingBy(Element::getKey));

        map.putAll(grouped);
    }

}
