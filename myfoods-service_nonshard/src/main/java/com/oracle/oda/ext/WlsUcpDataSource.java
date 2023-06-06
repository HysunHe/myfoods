package com.oracle.oda.ext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.oracle.oda.ext.exceptions.ApplicationException;

@Configuration
public class WlsUcpDataSource {
	private static final Logger LOGGER = LoggerFactory.getLogger(WlsUcpDataSource.class);

	@Primary
	@Bean(destroyMethod="")
	public DataSource ds_ucp_myfoods() {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			LOGGER.info("*** Looking up WebLogic DataSource jdbc/myfoodsDs...");
			DataSource pds = (DataSource) ctx.lookup("jdbc/myfoodsDs");
			LOGGER.info("*** Looking up WebLogic DataSource jdbc/myfoodsDs...[Good]");
			return pds;
		} catch (NamingException e) {
			LOGGER.info("*** Looking up WebLogic DataSource jdbc/myfoodsDs...[Failed]");
			throw new ApplicationException("Failed to load datasource jdbc/myfoodsDs");
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					LOGGER.error(e.toString());
				}
			}
		}
	}
}