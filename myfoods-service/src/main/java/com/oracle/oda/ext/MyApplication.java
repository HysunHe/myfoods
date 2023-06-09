/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import javafx.application.Application;

/***************************************************************************
 * <PRE>
 *  Project Name    : bot-gateway-springboot
 * 
 *  Package Name    : com.oracle.oda.ext
 * 
 *  File Name       : Application.java
 * 
 *  Creation Date   : 2019年2月21日
 * 
 *  Author          : hysun
 * 
 *  Purpose         : TODO
 * 
 * 
 *  History         : TODO
 * 
 * </PRE>
 ***************************************************************************/
// equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan.
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyApplication.class);
	}

	public static void main(String[] args) {
		LOGGER.info("*********** Start Application ***********");
		SpringApplication.run(Application.class, args);
	}

	private void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		LOGGER.info("*****************************************");
		LOGGER.info("***** Here are all registered beans *****");
		for (String beanName : allBeanNames) {
			LOGGER.info(beanName);
		}
		LOGGER.info("*** All registered beans listed above ***");
		LOGGER.info("*****************************************");
	}

	@Override
	public void run(String... args) throws Exception {
		displayAllBeans();
	}
}
