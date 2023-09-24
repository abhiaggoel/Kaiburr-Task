package com.abhinav.kaiburr;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OSRepo extends MongoRepository<OSSystem,Integer>{

    List<OSSystem> findByNameContaining(String name);

    
}