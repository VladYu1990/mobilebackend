package com.istudyenglish.mobilebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSource extends DriverManagerDataSource{

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String DRIVER = "org.postgresql.Driver";
    private final String PASSWORD = "5240";


    public DataSource() {
        setUrl(URL);
        setUsername(USER);
        setPassword(PASSWORD);
        setDriverClassName(DRIVER);
    }

}
