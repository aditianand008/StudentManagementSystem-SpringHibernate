package com.studentmanagement.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring Configuration Class for Student Management System
 * Java-based configuration (no XML)
 */
@Configuration
@EnableTransactionManagement
public class AppConfig {

    /**
     * DataSource Bean - Connection Pooling with Apache Commons DBCP2
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student_management_db?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        
        // Connection pool settings
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        
        return dataSource;
    }

    /**
     * SessionFactory Bean - Hibernate Configuration
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.studentmanagement.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        
        return sessionFactory;
    }

    /**
     * Hibernate Properties Configuration
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        
        return properties;
    }

    /**
     * TransactionManager Bean - Manages Hibernate Transactions
     */
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        
        return transactionManager;
    }
}
