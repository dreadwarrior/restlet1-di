package de.dreadlabs.examplefeature;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

public class NamesProviderService {
    private final NamesRepository namesRepository;

    @Inject
    public NamesProviderService(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;
    }

    public String getRandomName() {
        List<String> names = namesRepository.findNames();

        return names.get(randomIndex(names.size()));
    }

    private int randomIndex(int bound) {
        return new Random(System.currentTimeMillis()).nextInt(bound);
    }
}
