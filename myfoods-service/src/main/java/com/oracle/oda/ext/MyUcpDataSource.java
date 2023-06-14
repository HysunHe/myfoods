/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;

import oracle.jdbc.replay.OracleDataSourceFactory;

/***************************************************************************
 * <PRE>
 *  Project Name    : bot-gateway-springboot
 * 
 *  Package Name    : com.oracle.oda.ext.dao
 * 
 *  File Name       : FoodsMapper.java
 * 
 *  Creation Date   : 2023年5月30日
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
@Configuration
public class MyUcpDataSource {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUcpDataSource.class);

	@Bean("ds_shard")
	@DependsOn({ "ds_shard_sg", "ds_shard_in", "ds_shard_au", "ds_shard_gsm" })
	@Primary
	public DataSource ds_shard() throws SQLException {
		DynamicRoutingDataSource drs = new DynamicRoutingDataSource();
		drs.addDataSource("ds_shard_sg", ds_shard_sg());
		drs.addDataSource("ds_shard_in", ds_shard_in());
		drs.addDataSource("ds_shard_au", ds_shard_au());
		drs.addDataSource("ds_shard_gsm", ds_shard_gsm());
		drs.setPrimary("ds_shard_sg");
		return drs;
	}

	@Bean("ds_shard_sg")
	@ConfigurationProperties("spring.datasource.shardsg")
	public DataSource ds_shard_sg() throws SQLException {
		LOGGER.info("*** Initialize datasource ds_shard_sg.");
		return OracleDataSourceFactory.getOracleDataSource();
	}

	@Bean("ds_shard_in")
	@ConfigurationProperties("spring.datasource.shardin")
	public DataSource ds_shard_in() throws SQLException {
		LOGGER.info("*** Initialize datasource ds_shard_in.");
		return OracleDataSourceFactory.getOracleDataSource();
	}

	@Bean("ds_shard_au")
	@ConfigurationProperties("spring.datasource.shardau")
	public DataSource ds_shard_au() throws SQLException {
		LOGGER.info("*** Initialize datasource ds_shard_au.");
		return OracleDataSourceFactory.getOracleDataSource();
	}

	@Bean("ds_shard_gsm")
	@ConfigurationProperties("spring.datasource.shardgsm")
	public DataSource ds_shard_gsm() throws SQLException {
		LOGGER.info("*** Initialize datasource ds_shard_gsm.");
		return OracleDataSourceFactory.getOracleDataSource();
	}
}