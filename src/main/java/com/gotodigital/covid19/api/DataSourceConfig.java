package com.gotodigital.covid19.api;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataSourceConfig {
	@Bean
    @Primary
    public DataSource getDataSource() {

        String url = "jdbc:postgresql://" + "covid-19-gtd.mysql.database.azure.com" + ":" + "3306" + "/cod12";
        String username = "covid19gtd@covid-19-gtd";
        String password = "GN97olY*aV1Qoozp";
        String driverClassName = "com.mysql.jdbc.Driver";

        /*
         * Create the datasource and return it
         * 
         * You could create the specific DS 
         * implementation (ie: org.postgresql.ds.PGPoolingDataSource) 
         * or ask Spring's DataSourceBuilder to autoconfigure it for you, 
         * whichever works best in your eyes
        */

        return DataSourceBuilder
                .create()
                .url( url )
                .username( username )
                .password( password )
                .driverClassName( driverClassName )
                .build();
    }
}