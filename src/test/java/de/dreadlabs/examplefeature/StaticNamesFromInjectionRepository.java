package de.dreadlabs.examplefeature;

import com.google.inject.name.Named;

import javax.inject.Inject;
import java.util.List;

public class StaticNamesFromInjectionRepository implements NamesRepository {
    private final List<String> names;

    @Inject
    public StaticNamesFromInjectionRepository(@Named("Names") List<String> names) {
        this.names = names;
    }

    @Override
    public List<String> findNames() {
        return names;
    }
}
