package hello;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

@Configuration
@EnableTransactionManagement
public class DatabaseContext implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                         .driverClassName("com.mysql.cj.jdbc.Driver")
                         .url("jdbc:mysql://localhost:3306/test")
                         .username("root")
                         .build();
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Sql2o sql2o() {

        return new Sql2o(dataSource());
    }

}