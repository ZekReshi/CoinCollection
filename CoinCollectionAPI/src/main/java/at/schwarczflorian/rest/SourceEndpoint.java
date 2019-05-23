package at.schwarczflorian.rest;

import at.schwarczflorian.database.SourceFacade;
import at.schwarczflorian.model.Source;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sources")
@Stateless
public class SourceEndpoint {

    @Inject
    SourceFacade sourceFacade;

    @GET
    public Response getAll() {
        List<Source> sources = sourceFacade.getAll();
        return Response.ok().entity(sources).build();
    }

}
