package com.distribuida.rep;

import com.distribuida.db.Persona;
import io.lettuce.core.RedisClient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class PersonaRepository implements PanacheRepositoryBase<Persona, Long> {
    @Inject
    RedisClient redisClient;
    public Optional<Persona> findByIdOptionalCache(Long id){
        /**
         * Buscar en el cache
         * Está en el cache?
         *  retornar la instancia
         * Si no está?
         *  buscar en la BD
         *  poner en el cache
         *  retornar la instancia
         */

        try( var conn = redisClient.connect()){
            String obj = conn.sync().get(id.toString());
            if(obj != null){
                System.out.println(obj);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return this.findByIdOptional(id);
    }
}
