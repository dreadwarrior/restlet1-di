package de.dreadlabs.examplefeature;

import com.google.inject.name.Named;

import javax.inject.Inject;
import java.util.List;

public class InjectableNamesRepository implements NamesRepository {
    private final List<String> names;

    @Inject
    public InjectableNamesRepository(@Named("Names") List<String> names) {
        this.names = names;
    }

    @Override
    public List<String> findNames() {
        return names;
    }
}
