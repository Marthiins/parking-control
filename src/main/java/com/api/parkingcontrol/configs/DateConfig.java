package com.api.parkingcontrol.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig { //Criei a configuração na classe Global para configuração customizada, pode ser criado a nivel de classe e atributo

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"; //Formatação data ISO 8601 UTC Horas 3 horas adiantada
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    @Primary //Questão de prioridade
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        return new ObjectMapper()
                .registerModule(module);
    }

}

