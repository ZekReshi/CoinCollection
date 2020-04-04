package at.schwarczflorian.coincollection.rest;

import at.schwarczflorian.coincollection.database.SourceRepository;
import at.schwarczflorian.coincollection.model.Source;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sources")
public class SourceEndpoint {

    @Inject
    SourceRepository sourceRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Source> sources = sourceRepository.listAll();
        return Response
                .ok(sources)
                .build();
    }

}
