/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3_Reto3.Reto3.Repository;

import ciclo3_Reto3.Reto3.Interface.InterfaceCategoria;
import ciclo3_Reto3.Reto3.Model.Categoria;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Angie Moreno
 */
@Repository
public class RepositorioCategoria {
    @Autowired
    private InterfaceCategoria crud;
    public List<Categoria> getAll(){
        return (List<Categoria>) crud.findAll();
    }
    public Optional<Categoria> getCategoria(int id){
        return crud.findById(id);
    }

    public Categoria save(Categoria categoria){
        return crud.save(categoria);
    }
    public void delete(Categoria categoria){
       crud.delete(categoria);
    }
}
