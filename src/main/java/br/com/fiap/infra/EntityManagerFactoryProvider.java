package br.com.fiap.infra;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.hk2.api.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {
    private static volatile EntityManagerFactoryProvider instance;
    private final EntityManagerFactory emf;

    private EntityManagerFactoryProvider(String persistenceUnit) {
        emf = Persistence.createEntityManagerFactory( persistenceUnit, getProperties() );
    }

    public static EntityManagerFactoryProvider build(String persistenceUnit) {
        EntityManagerFactoryProvider result = instance;
        if (Objects.nonNull( result )) return result;
        synchronized (EntityManagerFactoryProvider.class) {
            if (Objects.isNull( instance )) {
                instance = new EntityManagerFactoryProvider( persistenceUnit );
            }
            return result;
        }
    }

    /**
     * This method will create instances of the type of this factory.  The provide
     * method must be annotated with the desired scope and qualifiers.
     *
     * @return The produces object
     */
    @Override
    public EntityManagerFactory provide() {
        return emf;
    }

    /**
     * This method will dispose of objects created with this scope.  This method should
     * not be annotated, as it is naturally paired with the provide method
     *
     * @param instance The instance to dispose of
     */
    @Override
    public void dispose(EntityManagerFactory instance) {
        instance.close();
    }

    private Map<String, Object> getProperties() {
        Map<String, String> env = System.getenv();
        Map<String, Object> properties = new HashMap<>();

        for (String chave : env.keySet()) {
            System.out.println( chave );
            if (chave.contains( "USER_FIAP" )) {
                properties.put( "jakarta.persistence.jdbc.user", env.get( chave ) );
            }
            if (chave.contains( "PASSWORD_FIAP" )) {
                properties.put( "jakarta.persistence.jdbc.password", env.get( chave ) );
            }
            // Outras configurações de propriedade ....
            properties.put( "hibernate.hbm2ddl.auto", "update" );
        }
        return properties;
    }
}
