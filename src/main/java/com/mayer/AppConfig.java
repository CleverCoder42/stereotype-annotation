package com.mayer;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mayer.ContactService;
import com.mayer.domain.Data;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;




@Configuration
@ComponentScan(basePackages = "com.mayer")
public class AppConfig {
	
	
	
	 @Bean(name = "dataSource")
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/data?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.mayer.domain");
		sessionFactory.setAnnotatedClasses(Data.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
		
	}
	
	private Properties hibernateProperties(){
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		//hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		
		return hibernateProperties;
		
	}
	
	@Primary
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();		
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.mayer.domain");
		entityManagerFactory.setJpaProperties(hibernateProperties());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		return entityManagerFactory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		//HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		// transactionManager.setSessionFactory(sessionFactory().getObject());
		JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
		return transactionManager;
	}
/*	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setViewClass(JstlView.class);		
		return viewResolver;
	}*/
	@Bean
	ContactService contactService(){
		return new ContactService();
	}


	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){		
		return new NamedParameterJdbcTemplate(dataSource());
	}
	@Bean
	public MessageSource messageSource(){		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/message/messages");		
		return  messageSource;
		
	}

	
}
