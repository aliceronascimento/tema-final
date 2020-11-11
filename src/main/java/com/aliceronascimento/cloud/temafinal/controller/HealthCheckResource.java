package com.aliceronascimento.cloud.temafinal.controller;

import netflix.karyon.health.HealthCheckHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/healthcheck")
public class HealthCheckResource implements HealthCheckHandler {

    @Override
    public int getStatus() {
        return 200;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthCheck(){
        return Response.status(getStatus()).build();
    }
}