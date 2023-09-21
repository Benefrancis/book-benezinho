package br.com.fiap;

import br.com.fiap.domain.entity.Pessoa;
import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.entity.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String BASE_URI = "http://localhost";


    public static HttpServer startServer(){
       final ResourceConfig  rc = new ResourceConfig();
       return null;
    }


    public static void main(String[] args) {




    }


}