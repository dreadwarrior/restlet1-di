package de.dreadlabs.examplefeature;

import java.util.List;

public class StaticNamesRepository implements NamesRepository {

    private final List<String> names = List.of("Jane", "John", "Tommy", "World");

    @Override
    public List<String> findNames() {
        return names;
    }
}
