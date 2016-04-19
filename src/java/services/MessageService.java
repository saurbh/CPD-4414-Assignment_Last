/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Saurabh
 */
@Path("/message")
@RequestScoped
public class MessageService {
    List<Message> messageList;
    MessageController mc = new MessageController();

    /**
     *
     * @return
     */
    @GET
    @Path("/v1/messages")

    @Produces("applcation/json")
    public JsonArray getAll() {
        messageList = mc.getAll();
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : messageList) {
            json.add(m.toJSON());
        }
        return json.build();
    }

    @GET
    @Path("{id}")
    @Produces("applcation/json")
    public JsonObject getMessageByID(@PathParam("id") int id) {
        return mc.getById(id).toJSON();
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("{startDate}/{endDate}")
    @Produces("application/json")

    public JsonArray getMEssageByDate(@PathParam("startDate") Date startDate, @PathParam("endDate") Date endDate) {
         List<Message> message = mc.getFromTo(startDate, endDate);
         JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : message) {
            json.add(m.toJSON());
        }
        return json.build();

    }

    @POST
    @Consumes("application/json")
    public Response add(JsonObject json) {
        Message m =new Message(json);
        return Response.ok().build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteMessageById(@PathParam("id") int id){
         mc.remove(id);
         return Response.ok().build();
    }
    
    @PUT
    @Consumes("application/json")
    public Response editMessageById(JsonObject json){
        int id = json.getInt("id");
        Message m =new Message(json);
        mc.edit(id, m);
        return Response.ok().build();
    }
}
