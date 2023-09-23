package br.com.fiap.domain.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Repository<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByName(String texto);

    public T persist(T t);

}
