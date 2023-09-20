package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PJ", uniqueConstraints = {@UniqueConstraint(name = "UK_PJ_CNPJ", columnNames = "NR_CNPJ")})
public class PessoaJuridica extends Pessoa {

    @Column(name = "NR_CNPJ", nullable = false)
    String cnpj;


    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String nome, LocalDate nascimento, String cnpj) {
        super( id, nome, nascimento );
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }


    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                "} " + super.toString();
    }
}
