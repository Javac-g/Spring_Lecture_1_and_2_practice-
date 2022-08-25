package com.company.Configuration;

import com.company.Controller.Controller;
import com.company.Service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SpringConfigurationFactory {

    @Bean("Service")
//  @Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS) will erase our datalist and give empty response
    public Service serviceBean(){
        return new Service();
    }

    @Bean("Controller")
    @Scope(value = "prototype")
    public Controller controllerBean(){
        return new Controller(serviceBean());
    }

}
