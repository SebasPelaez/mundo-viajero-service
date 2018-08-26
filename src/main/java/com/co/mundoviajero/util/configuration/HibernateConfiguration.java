package com.co.mundoviajero.util.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Value("${db.driver}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;
        
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
      
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }
        
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @SuppressWarnings("serial")
    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
                setProperty("hibernate.dialect", HIBERNATE_DIALECT);
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.id.new_generator_mappings", "false");
                setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            }
        };
    }

}
