package de.dreadlabs.infrastructure.restletguicebridge;

import com.google.inject.Injector;
import org.restlet.Context;
import org.restlet.Finder;
import org.restlet.Router;
import org.restlet.resource.Resource;

public class GuiceRouter extends Router {


    private final Injector injector;

    public GuiceRouter(Context context, Injector injector) {
        super(context);
        this.injector = injector;
    }

    @Override
    protected Finder createFinder(Class<? extends Resource> targetClass) {
        return new GuiceFinder(getContext(), targetClass, this.injector);
    }
}
