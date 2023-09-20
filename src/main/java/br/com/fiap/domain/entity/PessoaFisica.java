package br.com.fiap.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PF", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PF_CPF", columnNames = "NR_CPF")
})
public class PessoaFisica extends Pessoa {

    @Column(name = "NR_CPF", nullable = false)
    private String cpf;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String nome, LocalDate nascimento, String cpf) {
        super( id, nome, nascimento );
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public PessoaFisica setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }


    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                "} " + super.toString();
    }
}
