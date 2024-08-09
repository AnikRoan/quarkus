package com.example.web;

import com.example.pojo.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/employee")
public class ExampleResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") Long id) {
        User user = User.findById(id);
        if (user!=null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        return Response.ok(User.listAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createEmployee(User user) {
        user.persist();
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateEmployee(@PathParam("id") Integer id, User user) {
        User u = User.findById(id);
        if (u!=null) {
            u.setName(user.getName());
            u.setSurname(user.getSurname());
            u.persist();
            return Response.ok(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteEmployee(@PathParam("id") Long id) {
        User u = User.findById(id);
        if (u!=null) {
            u.delete();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
//    @Transactional
//    @PostConstruct
//    public void init() {
//        var user=new User();
//        user.setId(1);
//        user.setName("2");
//        user.setSurname("3");
//        userRepository.persist(user);
//    }
}
