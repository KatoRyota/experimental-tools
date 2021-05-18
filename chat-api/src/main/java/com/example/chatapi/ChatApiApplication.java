package com.example.chatapi;

import com.example.coremodules.propertysource.ReloadablePropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:application.properties", factory = ReloadablePropertySourceFactory.class),
})
public class ChatApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
    }

}
