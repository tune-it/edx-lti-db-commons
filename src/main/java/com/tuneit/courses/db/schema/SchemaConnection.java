package com.tuneit.courses.db.schema;

import com.tuneit.courses.lab1.schema.Schema01;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */
@XmlRootElement(name = "connection")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchemaConnection {
    @XmlTransient
    private DataSource datasource = null;

    @XmlElement(name = "uri")
    private String uri;
    @XmlElement(name = "username")
    private String username;
    @XmlElement(name = "password")
    private String password;


    public static SchemaConnection load() {
        SchemaConnection connection;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SchemaConnection.class);
            InputStream inputStream = SchemaConnection.class.getResourceAsStream("connection.xml");
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            connection = (SchemaConnection) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("SchemaConnection could not be loaded", ex);
        }
        return connection;
    }

    public Connection getConnection() throws SQLException {
        if (datasource == null) {
            datasource = setupDataSource();
        }
        return datasource.getConnection();
    }

    public String getUri() {
        return uri;
    }

    public SchemaConnection setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SchemaConnection setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SchemaConnection setPassword(String password) {
        this.password = password;
        return this;
    }

    private DataSource setupDataSource() {
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(uri, username, password);
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

}
