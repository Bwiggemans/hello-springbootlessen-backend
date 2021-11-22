package nl.novi.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NameController {

    //Attributen
    private List<String> names = new ArrayList<>();

    //Constructor
    public NameController() {
        names.add("Johan");
        names.add("Bas");
        names.add("Peter");
        names.add("Jessica");
        names.add("Jan");
        names.add("Henk");
        names.add("Jan");
        names.add("Frits");
        names.add("Frans");

    }
    @GetMapping(value = "/names")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getNames() {
        return names;
    }

    @GetMapping(value = "/names/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getName(@PathVariable int id) {
        return names.get(id);
    }

    @DeleteMapping(value = "/names/{id}")
    public String deleteName(@PathVariable int id) {
        names.remove(id);
        return "Deleted!";
    }
    @PostMapping(value = "/names")
    @ResponseStatus(HttpStatus.CREATED)
    public String addName(@RequestBody String name) {
        names.add(name);
        return "Added!";
    }

}
