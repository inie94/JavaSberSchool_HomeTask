package ru.anani.lesson18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.anani.lesson18.entities.Dish;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.anani.lesson18")
public class Config {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource postgres = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/dish_db", "postgres", "frS?hq&7");
        postgres.setDriverClassName("org.postgresql.Driver");
        return postgres;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Dish dish() {
        Dish dish =  new Dish();
        dish.setId(1);
        dish.setName("abracadabra");
        return dish;
    }
}
