/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3_Reto3.Reto3.Repository;

import ciclo3_Reto3.Reto3.Controller.custom.CountClient;
import ciclo3_Reto3.Reto3.Interface.InterfaceReservaciones;
import ciclo3_Reto3.Reto3.Model.Cliente;
import ciclo3_Reto3.Reto3.Model.Reservaciones;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Angie Moreno
 */
@Repository
public class RepositorioReservaciones {
    @Autowired
    private InterfaceReservaciones crud4;

    public List<Reservaciones> getAll(){
        return (List<Reservaciones>) crud4.findAll();
    }
    public Optional<Reservaciones> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservaciones save(Reservaciones reservation){
        return crud4.save(reservation);
    }
    public void delete(Reservaciones reservation){
        crud4.delete(reservation);
    }

    public List<Reservaciones> getReservationByStatus (String status){
        return crud4.findAllByStatus(status);
    }
     
    public List<Reservaciones> getReservationByPeriod (Date dateOne, Date dateTwo){
        return crud4.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
     
    }
     
     public List<CountClient> getTopClient(){
         List<CountClient> res = new ArrayList<>();
         List<Object[]> report=crud4.countTotalReservationsByClient();
         for(int i=0; i<report.size(); i++){
             res.add(new CountClient((Long)report.get(i)[1],(Cliente) report.get(i)[0]));
         }
         return res;
     }
}
