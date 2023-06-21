package com.distribuida;

import com.distribuida.db.Persona;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

class Message{

    String msg = "Hola mundo";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
@Path("/personas")
public class GreetingResource implements PanacheRepository<Persona> {

    private List<Persona> personas = Persona.listAll();

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> hello(){
        return listAll();
    }

    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Persona getPersonaById(@PathParam("id") Long id){
        return findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Persona createPersona(Persona p){
        persist(p);
        return p;
    }

    @PUT
    @Path("/crear/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Persona updatePersona(@PathParam("id")Long id, String name){
        Persona p = findById(id);
        if(p != null){
            p.setName(name);
            return p;
        }else {
            throw new NotFoundException("Persona no encontrada con ID:");
        }
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Transactional
    public String deletePersona(@PathParam("id")Long id){
        Persona p = findById(id);
        if(p != null){
            delete(p);
            return "Persona Eliminada";
        }else{
            throw new NotFoundException("Persona no Encontrada");
        }
    }





}
