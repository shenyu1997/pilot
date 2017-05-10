package shenyu.pilot.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/5/8.
 */
@Configuration
@ComponentScan("shenyu.pilot.dao")
@PropertySource("classpath:h2.properties")
public class DaoConfig {

    @Value("${driver.class.name}")
    private String driverName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${db.username}")
    private String dbUserName;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    @Profile("default")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    @Bean
    public DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setIgnoreFailedDrops(true);
        databasePopulator.addScripts(new ClassPathResource("schema.sql"));
        return databasePopulator;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource ds, DatabasePopulator dp) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDatabasePopulator(dp);
        dataSourceInitializer.setDataSource(ds);
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
