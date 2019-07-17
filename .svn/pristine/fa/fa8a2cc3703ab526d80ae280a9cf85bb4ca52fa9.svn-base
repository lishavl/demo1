package com.pumex.tms.configurations;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pumex.tms.util.AppConstants;

/**
 * This class loads hibernate configurations
 * 
 * @Author JINSHAD P.T.
 * @Date 13/01/2016
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.pumex.tms.models" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	/*
	 * Method to get session factory
	 * 
	 * @return LocalSessionFactoryBean
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory
				.setPackagesToScan(new String[] { "com.pumex.tms.*" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/*
	 * Method to get data source
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource
				.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource
				.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	/*
	 * Method to get hibernate properties
	 * 
	 * @return Properties
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",
				environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("hibernate.table_autocreate"));

		properties.put("hibernate.c3p0.min_size",
				environment.getRequiredProperty("hibernate.c3p0.min_size"));
		properties.put("hibernate.c3p0.max_size",
				environment.getRequiredProperty("hibernate.c3p0.max_size"));
		properties.put("hibernate.c3p0.timeout",
				environment.getRequiredProperty("hibernate.c3p0.timeout"));
		properties.put("hibernate.c3p0.max_statements", environment
				.getRequiredProperty("hibernate.c3p0.max_statements"));
		
		AppConstants.FILES_PATH = environment
				.getRequiredProperty("tms.files.directory");

		return properties;
	}
	
	/*
	 * Method to get Hibernate TransactionManager
	 * 
	 * @return HibernateTransactionManager
	 */

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
