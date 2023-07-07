package de.dreadlabs.infrastructure.di;

import com.google.inject.AbstractModule;
import de.dreadlabs.examplefeature.NamesProviderService;
import de.dreadlabs.examplefeature.NamesRepository;
import de.dreadlabs.examplefeature.FixedNamesRepository;

public class ProductionDependenciesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NamesProviderService.class);

        bind(NamesRepository.class).to(FixedNamesRepository.class);
    }
}
