package br.com.fiap.domain.service;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.repository.PessoaFisicaRepository;
import br.com.fiap.infra.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaFisicaService implements Service<PessoaFisica, Long> {

    private static volatile PessoaFisicaService instance;

    private PessoaFisicaRepository repo;

    private PessoaFisicaService(PessoaFisicaRepository repo) {
        this.repo = repo;
    }

    public static PessoaFisicaService build() {
        String persistenceUnit = Main.PERSISTENCE_UNIT;
        PessoaFisicaService result = instance;
        if (Objects.nonNull( result )) return result;

        synchronized (PessoaFisicaService.class) {
            if (Objects.isNull( instance )) {
                EntityManagerFactory factory = EntityManagerFactoryProvider.build( persistenceUnit ).provide();
                PessoaFisicaRepository pessoaRepository = PessoaFisicaRepository.build( factory.createEntityManager() );
                instance = new PessoaFisicaService( pessoaRepository );
            }
            return instance;
        }
    }

    @Override
    public List<PessoaFisica> findAll() {
        return repo.findAll();
    }

    @Override
    public PessoaFisica findById(Long id) {
        return repo.findById( id );
    }

    @Override
    public List<PessoaFisica> findByName(String texto) {
        if (Objects.isNull( texto )) return new ArrayList<>();
        return repo.findByName( texto.toLowerCase() );
    }

    @Override
    public PessoaFisica persist(PessoaFisica pessoaFisica) {
        if (Objects.isNull( pessoaFisica )) return null;
        return repo.persist( pessoaFisica );
    }
}
