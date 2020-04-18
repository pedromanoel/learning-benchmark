package io.pedromanoel.benchmark;

import java.util.*;
import java.util.stream.IntStream;

public class ElementFactory {

    private static final int MINIMUM_GROUP_SIZE = 2;

    private final double probabilityOfGroupedElements;
    private final Random random;
    private final int maximumGroupSize;

    public ElementFactory(double probabilityOfGroupedElements,
                             int maximumGroupSize) {
        this.probabilityOfGroupedElements = probabilityOfGroupedElements;
        this.maximumGroupSize = maximumGroupSize;
        this.random = new Random(1);
    }

    public Collection<Element> generate(int numberOfElements) {
        List<Element> elements = new LinkedList<>();

        while(elements.size() < numberOfElements) {
            Collection<Element> group = getMultipleElements();

            elements.addAll(group);
        }

        int elementCountAboveMaximum = elements.size() - numberOfElements;
        for (int i = elementCountAboveMaximum; i > 0; i--) {
            elements.iterator().remove();
        }

        return elements;
    }



    private Set<Element> getMultipleElements() {
        Set<Element> result = new LinkedHashSet<>();

        UUID uuid = UUID.randomUUID();
        Element firstElement = new Element(uuid, 1);
        result.add(firstElement);

        if (isSingle(probabilityOfGroupedElements)) {
            return result;
        }

        int randomUpperBound =
                random.nextInt(maximumGroupSize - MINIMUM_GROUP_SIZE);
        IntStream
                .rangeClosed(
                        MINIMUM_GROUP_SIZE,
                        MINIMUM_GROUP_SIZE + randomUpperBound)
                .forEach(groupIndex -> result.add(new Element(uuid, groupIndex)));

        return result;
    }


    private boolean isSingle(double probability) {
        return random.nextDouble() > probability;
    }
}
