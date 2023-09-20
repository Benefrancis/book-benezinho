package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.repository.PessoaFisicaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaFisicaService implements Service<PessoaFisica, Long> {

    private PessoaFisicaRepository repo;

    public PessoaFisicaService() {
        this.repo = new PessoaFisicaRepository();
    }

    @Override
    public List<PessoaFisica> findAll() {
        return repo.findAll();
    }

    @Override
    public PessoaFisica findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<PessoaFisica> findByName(String texto) {
        if(Objects.isNull(texto)) return new ArrayList<>();
        return repo.findByName(texto.toLowerCase());
    }

    @Override
    public PessoaFisica persist(PessoaFisica pessoaFisica) {
        if(Objects.isNull(pessoaFisica)) return null;
        return repo.persist(pessoaFisica);
    }
}
