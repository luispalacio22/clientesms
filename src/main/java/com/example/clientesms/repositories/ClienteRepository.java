package com.example.clientesms.repositories;

import com.example.clientesms.models.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente,String> {
}
