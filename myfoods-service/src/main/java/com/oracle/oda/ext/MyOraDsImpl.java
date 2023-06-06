/***************************************************************************
 *
 * This document contains confidential and proprietary information 
 * subject to non-disclosure agreements with Hysun He. This information 
 * shall not be distributed or copied without written permission from 
 * Hysun He.
 *
 ***************************************************************************/

package com.oracle.oda.ext;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.OracleShardingKey;
import oracle.jdbc.datasource.impl.OracleConnectionBuilderImpl;
import oracle.jdbc.replay.OracleDataSourceImpl;

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
public class MyOraDsImpl extends OracleDataSourceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyOraDsImpl.class);

    public MyOraDsImpl() throws SQLException {
        super();
    }

    @Override
    public Connection getConnection() throws SQLException {
        LOGGER.info("*** getConnection()");
        String key = MyShardingKeyContext.getShardingKey();
        LOGGER.info("*** Got sharding key: " + key);
        return this.createConnectionBuilder().build();
        // return this.createConnectionBuilder()
        // .shardingKey(getShardingKey())
        // .build();
    }

    @Override
    public Connection getConnection(String username, String passwd) throws SQLException {
        LOGGER.info("*** getConnection(u,p)");
        String key = MyShardingKeyContext.getShardingKey();
        LOGGER.info("*** Got sharding key: " + key);
        return this.createConnectionBuilder()
                .user(username)
                .password(passwd)
                .build();
        // return this.createConnectionBuilder()
        // .user(username)
        // .password(passwd)
        // .shardingKey(getShardingKey())
        // .build();
    }

    @Override
    public Connection getConnectionNoProxy(OracleConnectionBuilderImpl connBldr)
            throws SQLException {
        LOGGER.info("*** getConnectionNoProxy()");
        String key = MyShardingKeyContext.getShardingKey();
        LOGGER.info("*** Got sharding key: " + key);
        return connBldr.build();
        // return connBldr.shardingKey(getShardingKey()).build();
    }

    private OracleShardingKey getShardingKey() throws SQLException {
        String key = MyShardingKeyContext.getShardingKey();
        OracleShardingKey shardKey = this.createShardingKeyBuilder()
                .subkey(key, JDBCType.VARCHAR).build();
        return shardKey;
    }
}
