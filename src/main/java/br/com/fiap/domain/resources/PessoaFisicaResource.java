package br.com.fiap.domain.resources;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.PessoaFisica;
import br.com.fiap.domain.service.PessoaFisicaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/pf")
public class PessoaFisicaResource implements Resource<PessoaFisica, Long> {

    @Context
    UriInfo uriInfo;

    private PessoaFisicaService service = PessoaFisicaService.build( );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        List<PessoaFisica> pessoas = service.findAll();
        return Response.ok( pessoas ).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findById(@PathParam("id") Long id) {
        PessoaFisica pf = service.findById( id );
        if (Objects.isNull( pf )) return Response.status( 404 ).build();
        return Response.ok( pf ).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response persist(PessoaFisica entity) {
        PessoaFisica persist = service.persist( entity );
        UriBuilder uriInfoAbsolutePathBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriInfoAbsolutePathBuilder.path( String.valueOf( persist.getId() ) ).build();
        return Response.created( uri ).entity( persist ).build();
    }
}
