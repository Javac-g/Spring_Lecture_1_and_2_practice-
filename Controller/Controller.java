package com.company.Controller;

import com.company.Service.ResponseDTO;
import com.company.Service.Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Main")
public class Controller {

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
    public ResponseDTO updateUser(@RequestParam Integer id,@RequestBody RequestDTO json){
        return service.update(id,json);

    }
}
