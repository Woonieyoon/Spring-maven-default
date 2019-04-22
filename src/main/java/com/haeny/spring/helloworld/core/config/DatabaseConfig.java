package com.haeny.spring.helloworld.core.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan("com.haeny.spring.helloworld.core")
@MapperScan("com.haeny.spring.helloworld.core.repository")
public class DatabaseConfig {
	@Autowired
	private Environment environment;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		String jdbcDriverClassName = environment.getProperty("jdbcDriverClassName");
		String jdbcUrl = environment.getProperty("jdbcUrl");
		String jdbcUser = environment.getProperty("jdbcUserName");
		String jdbcPassword = environment.getProperty("jdbcPassword");
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(jdbcDriverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(jdbcUser);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
}
