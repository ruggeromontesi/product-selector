package seb.homework.productselector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class SpringJdbcConfig {
   @Bean
   public DataSource mysqlDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.h2.Driver");
      dataSource.setUrl("spring.datasource.url");
      dataSource.setUsername("spring.datasource.username");
      dataSource.setPassword("spring.datasource.password");

      return dataSource;
   }
}
