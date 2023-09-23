package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;

public class PessoaFisicaRepository implements Repository<PessoaFisica, Long> {

    private static volatile PessoaFisicaRepository instance;
    private EntityManager manager;

    private PessoaFisicaRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static PessoaFisicaRepository build(EntityManager manager) {
        PessoaFisicaRepository result = instance;
        if (Objects.nonNull( result )) return result;

        synchronized (PessoaFisicaRepository.class) {
            if (Objects.isNull( instance )) {
                instance = new PessoaFisicaRepository( manager );
            }
            return instance;
        }
    }


    @Override
    public List<PessoaFisica> findAll() {
        List<PessoaFisica> list = manager.createQuery( "FROM PessoaFisica" ).getResultList();
        return list;
    }

    @Override
    public PessoaFisica findById(Long id) {
        PessoaFisica pessoa = manager.find( PessoaFisica.class, id );
        return pessoa;
    }

    @Override
    public List<PessoaFisica> findByName(String texto) {
        String jpql = "SELECT p FROM PessoaFisica p  where lower(p.nome) LIKE CONCAT('%',lower(:nome),'%')";
        Query query = manager.createQuery( jpql );
        query.setParameter( "nome", texto );
        List<PessoaFisica> list = query.getResultList();
        return list;
    }

    @Override
    public PessoaFisica persist(PessoaFisica pessoa) {
        manager.getTransaction().begin();
        manager.persist( pessoa );
        manager.getTransaction().commit();
        return pessoa;
    }
}
