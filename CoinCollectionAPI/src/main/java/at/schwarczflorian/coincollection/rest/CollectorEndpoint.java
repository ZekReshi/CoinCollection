package at.schwarczflorian.coincollection.rest;

import at.schwarczflorian.coincollection.database.CollectorRepository;
import at.schwarczflorian.coincollection.model.Collector;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("collectors")
public class CollectorEndpoint {

    @Inject
    CollectorRepository collectorRepository;

    @GET
    public Response getAll() {
        List<Collector> collectors = collectorRepository.listAll();
        return Response.ok().entity(collectors).build();
    }

}
