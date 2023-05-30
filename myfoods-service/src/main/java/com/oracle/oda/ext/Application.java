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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
@SpringBootApplication // equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan.
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(Application.class, args);
		displayAllBeans();
	}

	public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		LOGGER.info("*****************************************");
		LOGGER.info("***** Here are all registered beans *****");
        for(String beanName : allBeanNames) {
            LOGGER.info(beanName);
        }
		LOGGER.info("*** All registered beans listed above ***");
		LOGGER.info("*****************************************");
    }
}
