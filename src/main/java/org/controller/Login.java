package org.controller;

import org.model.User;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class Login {

    @Context
    HttpServletRequest request;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = User.findByUsernameAndPassword(username, password);
        Gson gson = new Gson();
        if (user != null) {
            // request.getSession(true).setAttribute("user", username);
            return Response.ok(gson.toJson(user)).build();
        } else
            return Response.status(401).build();
    }
}
