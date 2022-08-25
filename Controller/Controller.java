package com.company.Controller;

import com.company.Service.ResponseDTO;
import com.company.Service.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Main")
public class Controller {
    private final static Logger logger  = LoggerFactory.getLogger(Controller.class);
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(value = "/a",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO addUser(@RequestBody RequestDTO user){
        return service.create(user);
    }

    @GetMapping(value = "/f",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO findUser(@RequestParam Integer id){
        return service.read(id);

    }
    @PutMapping(value = "/u",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateUser(@RequestParam Integer id,@RequestBody RequestDTO json){
        return service.update(id,json);

    }
    @DeleteMapping(value = "/d",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@RequestParam Integer id){
        if(id > 2){
            return service.Print("Was deleted person N: " + service.delete_A(id));
        } else if (id <= 2) {
            return service.Print("Was deleted person N: ") + service.delete_B(id);
        }
        return "Nobody was deleted";

    }
}
