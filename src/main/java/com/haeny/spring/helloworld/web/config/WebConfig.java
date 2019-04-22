package com.haeny.spring.helloworld.web.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.haeny.spring.helloworld.web")
@PropertySource("classpath:/application.properties")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	private static Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);
	
	@Autowired
	private Environment environment;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		LOGGER.info("=================== Configuring ViewResolvers ===================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		
		registry.viewResolver(resolver);
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		String tmpDir = environment.getProperty("fileupload.tmp.dir", String.class, "/temp/fileupload");
		long maxSize = environment.getProperty("fileupload.max.size", Long.class, 10 * 1024 * 1024L);
		int minSize = environment.getProperty("fileupload.min.size", Integer.class, 0);
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setUploadTempDir(new FileSystemResource(tmpDir));
		multipartResolver.setMaxUploadSize(maxSize);
		multipartResolver.setMaxInMemorySize(minSize);
		
		return multipartResolver;
	}
	
}
