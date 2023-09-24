package com.abhinav.kaiburr;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OSService {
    
    @Autowired
    private OSRepo osrepo;

    public OSSystem addOS(OSSystem o){
        return osrepo.save(o);

    }

    public OSSystem getOSbyId(Integer id){
        return  osrepo.findById(id).get();
    }

    public List<OSSystem> getOSByName(String name) {
        return osrepo.findByNameContaining(name);
    }

    public List<OSSystem> getallOS(){
        return osrepo.findAll();
    }

    public boolean isPresent(Integer id) {
        return !osrepo.findById(id).isEmpty();
     }

    public String deleteOS(Integer i){ 
        osrepo.deleteById(i);
        return " Product ID {"+i+"} Deleted";
    }

    public String updateOS(Integer id, OSSystem o) {
        OSSystem os = osrepo.findById(id).get();
        if(Objects.nonNull(o.getName())&&!"".equalsIgnoreCase(o.getName())){
            os.setName(o.getName());
        }
        if(Objects.nonNull(o.getLanguage())&&!"".equalsIgnoreCase(o.getLanguage())){
            os.setLanguage(o.getLanguage());
        }
        if(Objects.nonNull(o.getFramework())&&!"".equalsIgnoreCase(o.getFramework())){
            os.setFramework(o.getFramework());
        }
        osrepo.deleteById(id);
        osrepo.save(os);
        return "OS Details Updated";
    }

}
