package io.pedromanoel.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BenchmarkArrayTraversal {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 5)
    public List<Integer> traverse_a_collection(ListExecutionPlan plan) {
        return plan.collection
                .stream()
                .map(Element::hashCode)
                .filter(hash -> hash % 2 == 0)
                .collect(Collectors.toList());
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 5)
    public List<Integer> traverse_a_map(MapExecutionPlan plan) {
        return plan.map
                .values()
                .stream()
                .flatMap(Collection::stream)
                .map(Element::hashCode)
                .filter(hash -> hash % 2 == 0)
                .collect(Collectors.toList());
    }

}
