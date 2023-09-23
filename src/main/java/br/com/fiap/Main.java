package br.com.fiap;

import br.com.fiap.infra.EntityManagerFactoryProvider;
import br.com.fiap.infra.EntityManagerProvider;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {


    public static final String BASE_URI = "http://localhost/";

    public static final String PERSISTENCE_UNIT = "oracle";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()

                .register(
                        new AbstractBinder() {
                            @Override
                            protected void configure() {
                                bindFactory( EntityManagerFactoryProvider.class )
                                        .to( EntityManagerFactory.class )
                                        .in( Singleton.class );

                                bindFactory( EntityManagerProvider.class )
                                        .to( EntityManager.class )
                                        .in( RequestScoped.class );
                            }
                        }
                )
                .register( EntityManagerFactoryProvider.build( PERSISTENCE_UNIT ).provide() )
                .packages( "br.com.fiap.domain.resources" );
        return GrizzlyHttpServerFactory.createHttpServer( URI.create( BASE_URI ), rc );
    }

    public static void main(String[] args) {
        final HttpServer server = startServer();
        System.out.println(
                String.format( "Bookstore app started with endpoints available " +
                        "as %s%nHit Ctrl-C to stop it....", BASE_URI )
        );
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}