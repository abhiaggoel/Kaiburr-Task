package com.abhinav.kaiburr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/server")
@Tag(name = "OS Product API")
public class ProductRestController {

    @Autowired
    private OSService os;

    @Operation(summary = "Creating New Product")
    @PostMapping()@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully Created")
    })
    @Schema(name = "Product ID", example = "1", required = true)
    @ResponseStatus(HttpStatus.CREATED)
    public OSSystem createProduct(@RequestBody OSSystem o){
        return os.addOS(o);
    }

    @Operation(summary = "Updating Existing Product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully Updated"), 
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable("id") Integer id, @RequestBody OSSystem o){
        if(os.isPresent(id))
            return os.updateOS(id, o);
        return "404 No Product Found";
    }

    @Operation(summary = "Reading an Existing Product by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully Retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductbyId(@PathVariable() Integer id){
            if(os.isPresent(id)){
                OSSystem o = os.getOSbyId(id);
                return new ResponseEntity<OSSystem>(o,HttpStatus.OK);
            }
        return new ResponseEntity<String>("No OS Found",HttpStatus.OK);
    }

    @Operation(summary = "Fetching all Products in Mongo")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OSSystem> getAllProduct(){
        return os.getallOS();
    }

    @Operation(summary = "Fetching Product by Name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully Retrieved"), 
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @GetMapping("/name={name}")
  //  @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getProductbyName(@PathVariable("name") String name){
        List<OSSystem> o = os.getOSByName(name);
        return o.isEmpty()?new ResponseEntity<String>("No OS Found",HttpStatus.OK):new ResponseEntity<List<OSSystem>>(o,HttpStatus.OK);
    }

    @Operation(summary = "Deleting Product by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully Product Deleted"), 
        @ApiResponse(responseCode = "404", description = "Not found - The product was not found")
    })
    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public String deteleProduct(@PathVariable Integer id){
        if(os.isPresent(id)){
            os.deleteOS(id);
            return "Product Deleted";}
        return "404 OS Not Found"; 
        }
}
