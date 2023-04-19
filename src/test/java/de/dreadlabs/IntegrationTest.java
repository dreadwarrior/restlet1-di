package de.dreadlabs;

import com.google.inject.Guice;
import com.google.inject.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.restlet.Component;
import org.restlet.data.Protocol;

public abstract class IntegrationTest {

    private Component component;

    @BeforeEach
    void startAppServer() throws Exception {
        component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);

        Module dependencies = provideTestDependencies();
        component.getDefaultHost().attach(new ExampleRestletApplication(Guice.createInjector(dependencies)));

        component.start();
    }

    @AfterEach
    void stopAppServer() throws Exception {
        component.stop();
    }

    abstract protected Module provideTestDependencies();
}
