package de.dreadlabs.infrastructure.restletguicebridge;

import com.google.inject.Injector;
import org.restlet.Context;
import org.restlet.Finder;
import org.restlet.Handler;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Resource;

public class GuiceFinder extends Finder {

    private final Injector injector;

    public GuiceFinder(Context context, Class<? extends Resource> targetClass, Injector injector) {
        super(context, targetClass);
        this.injector = injector;
    }

    @Override
    public Handler createTarget(Class<? extends Handler> targetClass, Request request, Response response) {
        Handler result = injector.getInstance(targetClass);
        result.init(getContext(), request, response);

        return result;
    }
}
