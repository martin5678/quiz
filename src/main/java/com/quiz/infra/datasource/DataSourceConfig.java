package com.quiz.infra.datasource;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {
        "com.quiz.**.repo*",
    }
)
@EnableConfigurationProperties({
    HibernateProperties.class,
    JpaProperties.class,
})
public class DataSourceConfig {

  @Value("${spring.datasource.password}")
  private String mysqlPassword;

  @Value("${spring.datasource.username}")
  private String mysqlUserName;

  @Value("${spring.datasource.url}")
  private String mysqlUrl;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUsername(mysqlUserName);
    dataSource.setPassword(mysqlPassword);
    dataSource.setUrl(mysqlUrl);

    return dataSource;
  }
}
