package com.epsi.epsi_pixel_power_brawl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Component
public class PublicRoutes {
    private List<String> routes = new ArrayList<String>();
    
    public PublicRoutes() {
    	this.routes.add("/user/login");
        this.routes.add("/user/registration");
    }
    
    public List<String> getPublicRoutes() {
    	return this.routes;
    }
    
    public String[] getPublicRoutesAsArray(){
    	return this.routes.toArray(new String[0]);
    }

}