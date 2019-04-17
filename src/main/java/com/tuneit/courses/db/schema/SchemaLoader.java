package com.tuneit.courses.db.schema;

import com.tuneit.courses.lab1.schema.Schema01;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */
public class SchemaLoader {
    private static HashMap<Integer, Schema> schemas = new HashMap<>();
    private static SchemaConnection schemaConnection;

    static {
        loadDBDriver();
        loadSchemaConnection();
        loadSchemas();
    }

    private static void loadSchemaConnection() {
        schemaConnection = SchemaConnection.load();
    }

    private static void loadDBDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(SchemaLoader.class.getName()).log(Level.SEVERE, null, e);
            //TODO - replace to coorect shutdown SpringBoot Application
            System.exit(-1);
        }
    }

    private static void loadSchemas() {
        schemas.put(1, new Schema01().load("lab1.xml", "connection.xml"));
    }

    public static Schema getSchema(Integer labId) {
        return schemas.get(labId);
    }

    public static SchemaConnection getSchemaConnection() {
        return schemaConnection;
    }
}