package at.schwarczflorian.rest;

import at.schwarczflorian.database.CurrencyFacade;
import at.schwarczflorian.model.Currency;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("currencies")
@Stateless
public class CurrencyEndpoint {

    @Inject
    CurrencyFacade currencyFacade;

    @GET
    public Response getAll() {
        List<Currency> currencies = currencyFacade.getAll();
        return Response.ok().entity(currencies).build();
    }

}
