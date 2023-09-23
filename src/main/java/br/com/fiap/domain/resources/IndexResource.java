package br.com.fiap.domain.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path( "/" )
public class IndexResource {

    @GET
    public Response index(){
        return Response.ok("Hello").build();
    }

}
