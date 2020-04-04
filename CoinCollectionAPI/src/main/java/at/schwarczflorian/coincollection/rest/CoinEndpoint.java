package at.schwarczflorian.coincollection.rest;

import at.schwarczflorian.coincollection.database.CoinRepository;
import at.schwarczflorian.coincollection.model.Coin;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("coins")
public class CoinEndpoint {

    @Inject
    CoinRepository coinRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Coin> coins = coinRepository.listAll();
        return Response
                .ok(coins)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        Coin coin = coinRepository.findById(id);
        if (coin == null) {
            return Response
                    .status(404)
                    .build();
        }
        return Response
                .ok(coin)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Coin coin) {
        coinRepository.persistAndFlush(coin);
        return Response
                .ok(coin)
                .build();
    }

    @GET
    @Path("{id}/front")
    @Produces("image/*")
    @Transactional
    public Response getFront(@PathParam("id") Long id) {
        Coin coin = coinRepository.findById(id);
        if (coin == null || coin.getFront() == null) {
            return Response
                    .status(404)
                    .build();
        }
        return Response
                .ok(coin.getFront())
                .build();
    }

    @GET
    @Path("{id}/back")
    @Produces("image/*")
    @Transactional
    public Response getBack(@PathParam("id") Long id) {
        Coin coin = coinRepository.findById(id);
        if (coin == null || coin.getBack() == null) {
            return Response.status(404).build();
        }
        return Response.ok(coin.getBack()).build();
    }

    @POST
    @Path("{id}/front")
    @Consumes("image/*")
    @Transactional
    public Response saveFront(@PathParam("id") Long id, byte[] image) {
        Coin coin = coinRepository.findById(id);
        if (coin == null) {
            return Response
                    .status(404)
                    .build();
        }
        coinRepository.update("front = ?1 where id = ?2", image, id);
        return Response
                .noContent()
                .build();
    }

    @POST
    @Path("{id}/back")
    @Consumes("image/*")
    @Transactional
    public Response saveBack(@PathParam("id") Long id, byte[] image) {
        Coin coin = coinRepository.findById(id);
        if (coin == null) {
            return Response
                    .status(404)
                    .build();
        }
        coinRepository.update("back = ?1 where id = ?2", image, id);
        return Response
                .noContent()
                .build();
    }

}
