package com.distribuida.rest;

import com.distribuida.db.Persona;
import com.distribuida.rep.PersonaRepository;
import io.lettuce.core.RedisClient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

class Message{

    String msg = "Hola mundo";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
@Path("/persona")
public class PersonaRest{

    @Inject
    PersonaRepository rep;

//    private List<Persona> personas = Persona.listAll();

    @Path("/personas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> hello(){
        return rep.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Persona> getPersonaById(@PathParam("id") Long id){
        return rep.findByIdOptionalCache(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Persona createPersona(Persona p){
        rep.persist(p);
        return p;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Persona updatePersona(@PathParam("id")Long id, String name){
        Persona p = rep.findById(id);
        if(p != null){
            p.setName(name);
            return p;
        }else {
            throw new NotFoundException("Persona no encontrada con ID:");
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String deletePersona(@PathParam("id")Long id){
        Persona p = rep.findById(id);
        if(p != null){
            rep.delete(p);
            return "Persona Eliminada";
        }else{
            throw new NotFoundException("Persona no Encontrada");
        }
    }

    //Cache de Redis






}
