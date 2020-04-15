package com.grushka.heroes.rest;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.grushka.heroes.dto.HeroModel;
import com.grushka.heroes.dto.AutocompleteModel;
import com.grushka.heroes.entity.Hero;
import net.java.ao.DBParam;
import net.java.ao.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private Hero[] findHeroes(String name) {
        Hero[] heroes;
        if (name.trim() == "") {
            heroes = ao.find(Hero.class);
        } else {
            heroes = ao.find(
                Hero.class,
                Query.select().where(
                    "LOWER(NAME) LIKE LOWER(?)",
                    "%" + name + "%"
                )
            );
        }
        return heroes;
    }

    @GET
    public Response getHeroes(@DefaultValue("") @QueryParam("name") String name)
    {
        Hero[] heroes = findHeroes(name);
        List<HeroModel> heroModels = Arrays.stream(heroes)
            .map(HeroModel::new)
            .collect(Collectors.toList());
        return Response.ok(heroModels).build();
    }

    @GET
    @Path("autocomplete")
    public Response getHeroesAutocomplete(@DefaultValue("") @QueryParam("q") String name)
    {
        Hero[] heroes = findHeroes(name);
        List<AutocompleteModel> autocompleteModels = Arrays.stream(heroes)
            .map(h ->
                new AutocompleteModel(h.getName(), h.getID())
            )
            .collect(Collectors.toList());
        return Response.ok(autocompleteModels).build();
    }

    @POST
    public Response createHero(HeroModel heroModel)
    {
        if (heroModel.name != null) {
            Hero hero = ao.create(
                Hero.class,
                new DBParam("NAME", heroModel.name)
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

        if (heroModel.name != null) {
            hero.setName(heroModel.name);
            hero.save();
            return Response.ok(new HeroModel(hero)).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
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
