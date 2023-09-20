package br.com.fiap;

import br.com.fiap.domain.entity.Pessoa;
import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.entity.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "oracle" );
        EntityManager manager = factory.createEntityManager();


        Pessoa bene = new PessoaFisica();
        bene.setNome( "Benefrancis do Nascimento" ).setNascimento( LocalDate.of( 1977, 03, 8 ) );
        ((PessoaFisica) bene).setCpf( "2135465465" );


        Pessoa holding = new PessoaJuridica();
        holding.setNome( "Benezinho Holding" ).setNascimento( LocalDate.now().minusYears( 5 ) );
        ((PessoaJuridica) holding).setCnpj( "2132123132/0001-30" );


        manager.getTransaction().begin();
        manager.persist( bene );
        manager.persist( holding );
        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }




}