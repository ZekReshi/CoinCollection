package at.schwarczflorian.rest;

import at.schwarczflorian.database.CollectorFacade;
import at.schwarczflorian.model.Collector;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("collectors")
@Stateless
public class CollectorEndpoint {

    @Inject
    CollectorFacade collectorFacade;

    @GET
    public Response getAll() {
        List<Collector> collectors = collectorFacade.getAll();
        return Response.ok().entity(collectors).build();
    }

}
