/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3_Reto3.Reto3.Repository;

import ciclo3_Reto3.Reto3.Interface.InterfaceSkate;
import ciclo3_Reto3.Reto3.Model.Skate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Angie Moreno
 */
@Repository
public class RepositorioSkate {
    @Autowired
    private InterfaceSkate crud;

    public List<Skate> getAll(){
        return (List<Skate>) crud.findAll();
    }

    public Optional<Skate> getSkate(int id){
        return crud.findById(id);
    }

    public Skate save(Skate skate){
        return crud.save(skate);
    }
    public void delete(Skate skate){
        crud.delete(skate);
    }
}
