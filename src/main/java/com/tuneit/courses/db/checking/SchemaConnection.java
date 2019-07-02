package com.tuneit.courses.db.checking;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection manager
 * @author serge
 */
public abstract class SchemaConnection {
    private DataSource datasource = null;
    private DBInfo dbInfo;

    /**
     * Loads data for jdbc connection
     * @return jdbc connection info
     */
    public abstract DBInfo load();

    @PostConstruct
    private void initDBInfo(){
        dbInfo = load();
    }

    /**
     * Creates a dataSource if it isn't already exist and create connection
     * @return newly created constructed connection
     * @throws SQLException see {@link DataSource#getConnection()}
     */
    public Connection getConnection() throws SQLException {
        if (datasource == null) {
            datasource = setupDataSource();
        }
        Connection connection = datasource.getConnection();
        connection.setAutoCommit(true);
        return connection;
    }

    /**
     * Create and configure a dataSource
     * @return newly constructed dataSource
     */
    private DataSource setupDataSource() {
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(dbInfo.uri, dbInfo.username, dbInfo.password);
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(10);
        config.setMinIdle(4);
        config.setMaxTotal(100);

        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);

        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory, config);

        poolableConnectionFactory.setPool(connectionPool);

        PoolingDataSource<PoolableConnection> dataSource =
                new PoolingDataSource<>(connectionPool);


        return dataSource;
    }

    /**
     * database connection info
     */
    @AllArgsConstructor
    @NonNull
    protected class DBInfo {
        private String uri;
        private String username;
        private String password;
    }

}
