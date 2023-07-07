package de.dreadlabs.examplefeature;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import de.dreadlabs.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.restlet.Client;
import org.restlet.data.Protocol;
import org.restlet.data.Response;
import org.restlet.data.Status;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldITest extends IntegrationTest
{
    @Test
    void shouldGreet() throws IOException {
        Response response = new Client(Protocol.HTTP).get("http://localhost:8080/hello");

        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS_OK);
        assertThat(response.getEntity().getText()).isEqualTo("Hello, London!");
    }

    @Override
    protected Module provideTestDependencies() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bind(NamesProviderService.class);

                bind(NamesRepository.class).to(InjectableNamesRepository.class);
                bind(new TypeLiteral<List<String>>() {}).annotatedWith(Names.named("Names"))
                        .toInstance(List.of("London"));
            }
        };
    }
}
