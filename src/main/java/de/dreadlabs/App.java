package de.dreadlabs;

import com.google.inject.Guice;
import com.google.inject.Module;
import de.dreadlabs.infrastructure.di.ProductionDependenciesModule;
import org.restlet.Component;
import org.restlet.data.Protocol;

public class App {
    public static void main(String[] args) {
        try {
            Component component = new Component();
            component.getServers().add(Protocol.HTTP, 8080);

            Module dependencies = new ProductionDependenciesModule();
            component.getDefaultHost().attach(new ExampleRestletApplication(Guice.createInjector(dependencies)));

            component.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
