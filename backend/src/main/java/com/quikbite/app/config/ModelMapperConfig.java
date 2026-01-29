package com.quikbite.app.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Configures and provides a ModelMapper bean for object mapping.
     * by adding @Bean annotation, Spring will manage the lifecycle of the ModelMapper instance.
     * This allows for easy dependency injection wherever ModelMapper is needed in the application.
     * @return Configured ModelMapper instance.
     * modelMapper is used to map between DTOs and entities, simplifying data transfer and transformation tasks.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(AccessLevel.PRIVATE)
            .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}
