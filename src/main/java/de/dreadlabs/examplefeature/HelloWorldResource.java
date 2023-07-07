package de.dreadlabs.examplefeature;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;

import javax.inject.Inject;

public class HelloWorldResource extends Resource {

    private final NamesProviderService namesProvider;

    @Inject
    public HelloWorldResource(NamesProviderService namesProvider) {
        this.namesProvider = namesProvider;
    }

    @Override
    public void init(Context context, Request request, Response response) {
        super.init(context, request, response);

        getVariants().add(new Variant(MediaType.TEXT_PLAIN));
    }

    @Override
    public Representation represent(Variant variant) {
        return new StringRepresentation("Hello, " + namesProvider.getRandomName() + "!", variant.getMediaType());
    }
}
