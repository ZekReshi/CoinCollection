package at.schwarczflorian.coincollection.rest;

import at.schwarczflorian.coincollection.database.CurrencyRepository;
import at.schwarczflorian.coincollection.model.Currency;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("currencies")
public class CurrencyEndpoint {

    @Inject
    CurrencyRepository currencyRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Currency> currencies = currencyRepository.listAll();
        return Response
                .ok(currencies)
                .build();
    }

}
