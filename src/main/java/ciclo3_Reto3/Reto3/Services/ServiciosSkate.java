/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3_Reto3.Reto3.Services;

import ciclo3_Reto3.Reto3.Model.Skate;
import ciclo3_Reto3.Reto3.Repository.RepositorioSkate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Angie Moreno
 */
@Service
public class ServiciosSkate {
    @Autowired
    private RepositorioSkate metodosCrud;

    public List<Skate> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Skate> getSkate(int skateId) {
        return metodosCrud.getSkate(skateId);
    }

    public Skate save(Skate skate){
        if(skate.getId()==null){
            return metodosCrud.save(skate);
        }else{
            Optional<Skate> e=metodosCrud.getSkate(skate.getId());
            if(e.isEmpty()){
                return metodosCrud.save(skate);
            }else{
                return skate;
            }
        }
    }

    public Skate update(Skate skate){
        if(skate.getId()!=null){
            Optional<Skate> e=metodosCrud.getSkate(skate.getId());
            if(!e.isEmpty()){
                if(skate.getName()!=null){
                    e.get().setName(skate.getName());
                }
                if(skate.getBrand()!=null){
                    e.get().setBrand(skate.getBrand());
                }
                if(skate.getYear()!=null){
                    e.get().setYear(skate.getYear());
                }
                if(skate.getDescription()!=null){
                    e.get().setDescription(skate.getDescription());
                }
                if(skate.getCategory()!=null){
                    e.get().setCategory(skate.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return skate;
            }
        }else{
            return skate;
        }
    }


    public boolean deleteSkate(int skateId) {
        Boolean aBoolean = getSkate(skateId).map(skate -> {
            metodosCrud.delete(skate);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
