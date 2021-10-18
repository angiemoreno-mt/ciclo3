/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo3_Reto3.Reto3.Controller;

import ciclo3_Reto3.Reto3.Model.Skate;
import ciclo3_Reto3.Reto3.Services.ServiciosSkate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Angie Moreno
 */
@RestController
@RequestMapping("/api/Skate")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class ControladorSkate {
    @Autowired
    private ServiciosSkate servicio;
    @GetMapping("/all")
    public List<Skate> getSkates(){
        return servicio.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Skate> getBike(@PathVariable("id") int skateId) {
        return servicio.getSkate(skateId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Skate save(@RequestBody Skate skate) {
        return servicio.save(skate);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Skate update(@RequestBody Skate skate) {
        return servicio.update(skate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int skateId) {
        return servicio.deleteSkate(skateId);
    } 
}
