package br.com.fiap.domain.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Repository<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByName(String texto);

    public T persist(T t);

    default Map<String, Object> getProperties() {
        Map<String, String> env = System.getenv();
        Map<String, Object> properties = new HashMap<>();

        for (String chave : env.keySet()) {
            System.out.println(chave);
            if (chave.contains("USER_FIAP")) {
                properties.put("jakarta.persistence.jdbc.user", env.get(chave));
            }
            if (chave.contains("PASSWORD_FIAP")) {
                properties.put("jakarta.persistence.jdbc.password", env.get(chave));
            }
            // Outras configurações de propriedade ....
        }
        return properties;
    }


}
