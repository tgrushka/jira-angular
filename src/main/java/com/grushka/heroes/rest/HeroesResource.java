package com.grushka.heroes.rest;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.grushka.heroes.dto.HeroModel;
import com.grushka.heroes.entity.Hero;
import net.java.ao.DBParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/heroes")
@Produces(MediaType.APPLICATION_JSON)
@AnonymousAllowed
@Scanned
@Transactional
public class HeroesResource {
    @ComponentImport
    private final ActiveObjects ao;

    public HeroesResource(ActiveObjects ao) {
        this.ao = ao;
    }

    @GET
    public Response getHeroes()
    {
        Hero[] heroes = ao.find(Hero.class);
        return Response.ok(new HeroModel(heroes)).build();
    }

    @POST
    public Response createHero(HeroModel heroModel)
    {
        if (heroModel.hero != null) {
            Hero hero = ao.create(
                Hero.class,
                new DBParam("NAME", heroModel.hero.name)
            );
            return Response.ok(new HeroModel(hero)).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateHero(
        @PathParam("id") Integer id,
        HeroModel heroModel
    ) {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        hero.setName(heroModel.hero.name);
        hero.save();
        return Response.ok(new HeroModel(hero)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHero(@PathParam("id") Integer id)
    {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        ao.delete(hero);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    public Response getHero(@PathParam("id") Integer id)
    {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(new HeroModel(hero)).build();
    }
}
