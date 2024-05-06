package com.scotiabank.studentmanagement;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Clase principal de la aplicación de gestión de estudiantes..<br/>
 * <b>Class</b>: StudentManagementApplication<br/>
 */
@EnableWebFlux
@SpringBootApplication
public class StudentManagementApplication {

  @Bean
  ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    initializer.setDatabasePopulator(
        new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

    return initializer;
  }

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

}
