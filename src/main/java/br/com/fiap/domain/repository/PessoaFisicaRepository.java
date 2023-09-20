package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class PessoaFisicaRepository implements Repository<PessoaFisica, Long> {

    private EntityManagerFactory factory;

    public PessoaFisicaRepository() {
        this.factory = Persistence.createEntityManagerFactory("oracle", getProperties());
    }

    @Override
    public List<PessoaFisica> findAll() {
        String jpql = "FROM PessoaFisica p";
        EntityManager manager = factory.createEntityManager();
        List<PessoaFisica> list = manager.createQuery(jpql).getResultList();
        manager.close();
        return list;
    }

    @Override
    public PessoaFisica findById(Long id) {
        EntityManager manager = factory.createEntityManager();
        PessoaFisica pessoaFisica = manager.find(PessoaFisica.class, id);
        manager.close();
        return pessoaFisica;
    }

    @Override
    public List<PessoaFisica> findByName(String texto) {
        String jpql = "FROM PessoaFisica p where Lower(p.name)=:name";
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createQuery(jpql);
        query.setParameter("name", texto);
        List<PessoaFisica> list = query.getResultList();
        manager.close();
        return list;
    }

    @Override
    public PessoaFisica persist(PessoaFisica pessoaFisica) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pessoaFisica);
        manager.getTransaction().commit();
        return pessoaFisica;
    }
}
