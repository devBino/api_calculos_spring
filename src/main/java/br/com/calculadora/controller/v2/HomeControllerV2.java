package br.com.calculadora.controller.v2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/v2")
public class HomeControllerV2 {

    @GetMapping(value = "/")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok().build();
    }
    
}
