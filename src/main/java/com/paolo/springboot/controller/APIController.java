package com.paolo.springboot.controller;

import com.paolo.springboot.config.Config;
import com.paolo.springboot.config.FeatureEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    //1st WAY use @Value
    //@Value("${some.config}")
    private String pingMessage;

    //2nd WAY use Environment
    @Autowired
    private Environment environment;

    @Autowired
    private Config conf;

    @GetMapping("/")
    public String getRequest() {
        return pingMessage + " + " +
                environment.getProperty("otherconfig", "default value") + " + " +
                conf.getOtherconfig();

    }
}
