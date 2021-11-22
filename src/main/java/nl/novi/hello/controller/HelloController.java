package nl.novi.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Gebruik maken van end-points
@RestController
public class HelloController {

    // Onderstaand 2 end-points
    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello World! Again, again") ;
    }
    @GetMapping(value = "/goodbye")
    public String goodbye(){
        return "Goodbye. Have a nice day. Again, again";
    }
}
