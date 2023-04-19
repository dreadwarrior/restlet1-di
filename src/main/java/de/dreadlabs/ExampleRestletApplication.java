package de.dreadlabs;

import com.google.inject.Injector;
import de.dreadlabs.examplefeature.HelloWorldResource;
import de.dreadlabs.infrastructure.restletguicebridge.GuiceRouter;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Router;

public class ExampleRestletApplication extends Application {

    private final Injector injector;

    public ExampleRestletApplication(Injector injector) {
        this.injector = injector;
    }

    @Override
    public synchronized Restlet createRoot() {
        Router router = new GuiceRouter(getContext(), injector);

        router.attach("/hello", HelloWorldResource.class);

        return router;
    }
}
