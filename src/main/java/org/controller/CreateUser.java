package org.controller;

import org.model.User;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/createuser")
public class CreateUser {

    @Context
    HttpServletRequest request;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@FormParam("username") String username, @FormParam("password") String password) {
        User user = User.findByUsername(username);
        Gson gson = new Gson();
        if (user != null) {
            // request.getSession(true).setAttribute("user", username);
            return Response.status(401).build();
        } else {
            user = new User(username,password);
            user.persist();
            return Response.ok(gson.toJson(user)).build();
        }
    }
}
