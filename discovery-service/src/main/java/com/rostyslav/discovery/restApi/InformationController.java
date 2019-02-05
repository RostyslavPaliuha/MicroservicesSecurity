package com.rostyslav.discovery.restApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("instances/{instanceName}")
    public ResponseEntity getInstanceInformation(@PathVariable("instanceName") String instance) {


        return new ResponseEntity(discoveryClient.getInstances(instance), HttpStatus.OK);
    }
}
