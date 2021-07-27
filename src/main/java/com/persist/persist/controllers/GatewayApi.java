package com.persist.persist.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.persist.persist.libraries.connections.Connections;
import com.persist.persist.libraries.connections.Slave;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;


@Controller
public class GatewayApi {
    @CrossOrigin
    @GetMapping("/api/slaves")
    @ResponseBody
    public SlaveJson getSlaves(){
        return new SlaveJson(Connections.slaves.keySet().toArray());
    }
}

class SlaveJson{
    public SlaveJson(Object[] data){
        this.data = Arrays.toString(data);
    }
    @JsonProperty("data")
    public String data;
}