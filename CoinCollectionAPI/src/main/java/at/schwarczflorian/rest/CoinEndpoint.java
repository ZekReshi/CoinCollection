package at.schwarczflorian.rest;

import at.schwarczflorian.database.CoinFacade;
import at.schwarczflorian.model.Coin;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Path("coins")
@Stateless
public class CoinEndpoint {

    @Inject
    CoinFacade coinFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Coin> coins = coinFacade.getAll();
        return Response.ok().entity(coins).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        Coin coin = coinFacade.getById(id);
        if (coin == null) {
            return Response.status(404).build();
        }
        return Response.ok().entity(coin).build();
    }

    @GET
    @Path("{id}/front")
    @Produces("image/*")
    public Response getFront(@PathParam("id") Long id) {
        BufferedImage image = coinFacade.getFront(id);
        return convertImage(image);
    }

    @GET
    @Path("{id}/back")
    @Produces("image/*")
    public Response getBack(@PathParam("id") Long id) {
        BufferedImage image = coinFacade.getBack(id);
        return convertImage(image);
    }

    @POST
    @Path("{id}/front")
    @Consumes("image/*")
    public Response postFront(@PathParam("id") Long id, byte[] imageBytes) {
        Coin coin = coinFacade.getById(id);
        if (coin == null) {
            return Response.status(404).build();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        coinFacade.setFront(id, bais);
        return Response.noContent().build();
    }

    @POST
    @Path("{id}/back")
    @Consumes("image/*")
    public Response postBack(@PathParam("id") Long id, byte[] imageBytes) {
        Coin coin = coinFacade.getById(id);
        if (coin == null) {
            return Response.status(404).build();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        coinFacade.setBack(id, bais);
        return Response.noContent().build();
    }

    private Response convertImage(BufferedImage image) {
        if (image == null) {
            return Response.status(404).build();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        byte[] imageBytes = baos.toByteArray();
        return Response.ok().entity(imageBytes).build();
    }

}
