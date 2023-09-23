package br.com.fiap.infra;

import br.com.fiap.Main;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.hk2.api.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EntityManagerFactoryProvider implements Factory<EntityManagerFactory> {

    private static volatile EntityManagerFactoryProvider instance;

    private final EntityManagerFactory emf;

    public static EntityManagerFactoryProvider build(String persistentUnit) {
        EntityManagerFactoryProvider result = instance;
        if (Objects.nonNull( result )) return result;
        synchronized (EntityManagerFactoryProvider.class) {
            if (Objects.isNull( instance )) {
                instance = new EntityManagerFactoryProvider( persistentUnit );
            }
            //foi aqui o erro.... eu estava retornando result e o correto é instance...
            return instance;
        }
    }

    private EntityManagerFactoryProvider(String persistentUnit) {
        emf = Persistence.createEntityManagerFactory( persistentUnit, getProperties() );
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

    /**
     * Protegendo informações críticas como usuário e senha do banco de dados
     *
     * @return
     */
    static Map<String, Object> getProperties() {

        Map<String, String> env = System.getenv();

        Map<String, Object> properties = new HashMap<>();

        for (String chave : env.keySet()) {

            if (Main.PERSISTENCE_UNIT.equals( "oracle" )) {

                if (chave.contains( "USER_FIAP" )) {
                    properties.put( "jakarta.persistence.jdbc.user", env.get( chave ) );
                }
                if (chave.contains( "PASSWORD_FIAP" )) {
                    properties.put( "jakarta.persistence.jdbc.password", env.get( chave ) );
                }

            }

            // Outras configurações de propriedade ....
            properties.put( "hibernate.hbm2ddl.auto", "update" );

        }
        return properties;
    }

}