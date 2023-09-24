package com.abhinav.kaiburr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ProductRestController {

    @Autowired
    private OSService os;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OSSystem createProduct(@RequestBody OSSystem o){
        return os.addOS(o);
    }

    @Operation(summary = "Updating Existing Product")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable("id") Integer id, @RequestBody OSSystem o){
        if(os.isPresent(id))
            return os.updateOS(id, o);
        return "404 No Product Found";
    }

    @GetMapping("/id={id}")
    public ResponseEntity<?> getProductbyId(@PathVariable() Integer id){
            if(os.isPresent(id)){
                OSSystem o = os.getOSbyId(id);
                return new ResponseEntity<OSSystem>(o,HttpStatus.OK);
            }
        return new ResponseEntity<String>("No OS Found",HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OSSystem> getAllProduct(){
        return os.getallOS();
    }

    @GetMapping("/name={name}")
  //  @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProductbyName(@PathVariable("name") String name){
        List<OSSystem> o = os.getOSByName(name);
        return o.isEmpty()?new ResponseEntity<String>("No OS Found",HttpStatus.OK):new ResponseEntity<List<OSSystem>>(o,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public String deteleProduct(@PathVariable Integer id){
        if(os.isPresent(id)){
            os.deleteOS(id);
            return "Product Deleted";}
        return "404 OS Not Found"; 
        }
}
