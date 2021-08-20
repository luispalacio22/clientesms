package com.example.clientesms.controllers;

import ch.qos.logback.core.net.server.Client;
import com.example.clientesms.models.Cliente;
import com.example.clientesms.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping("/products")
    @ResponseBody
    public ResponseEntity<List<Cliente>> getAllItems(){
        List<Cliente> clientes =  clienteRepository.findAll();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/{clienteId}")
    @ResponseBody
    public ResponseEntity<Cliente> getProduct(@PathVariable String clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/add",consumes = {"application/json"},produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Cliente> addProduct(@RequestBody Cliente cliente, UriComponentsBuilder builder){
        clienteRepository.save(cliente);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addcliente/{id}").buildAndExpand(cliente.getId()).toUri());
        return new ResponseEntity<Cliente>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<Cliente> updateProduct(@RequestBody Cliente cliente){
        if(cliente != null){
            clienteRepository.save(cliente);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        clienteRepository.delete(cliente.get());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
