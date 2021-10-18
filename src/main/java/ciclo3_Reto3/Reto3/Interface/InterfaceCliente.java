/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ciclo3_Reto3.Reto3.Interface;

import ciclo3_Reto3.Reto3.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Angie Moreno
 */
public interface InterfaceCliente extends CrudRepository<Cliente, Integer>{
    
}
