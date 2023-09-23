package br.com.fiap.infra;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

import java.io.Closeable;
import java.io.IOException;

public class EntityManagerProvider implements Factory<EntityManager> {

    private EntityManagerFactory emf;
    private CloseableService closeableService;

    @Inject
    public EntityManagerProvider(EntityManagerFactory emf, CloseableService closeableService) {
        this.emf = emf;
        this.closeableService = closeableService;
    }

    /**
     * This method will create instances of the type of this factory.  The provide
     * method must be annotated with the desired scope and qualifiers.
     *
     * @return The produces object
     */
    @Override
    public EntityManager provide() {
        final EntityManager em = emf.createEntityManager();
        this.closeableService.add( new Closeable() {
            @Override
            public void close() throws IOException {
                if (emf.isOpen()) {
                    emf.close();
                }
            }
        } );
        return em;
    }

    /**
     * This method will dispose of objects created with this scope.  This method should
     * not be annotated, as it is naturally paired with the provide method
     *
     * @param instance The instance to dispose of
     */
    @Override
    public void dispose(EntityManager instance) {
        if (instance.isOpen()) {
            instance.close();
        }
    }
}
