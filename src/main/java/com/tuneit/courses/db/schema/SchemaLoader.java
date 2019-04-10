package com.tuneit.courses.db.schema;

import com.tuneit.courses.lab1.db.schema.Schema01;
import com.tuneit.courses.lab2.db.schema.Schema02;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */
public class SchemaLoader {
    private static final HashMap<String, Schema> SCHEMAS = new HashMap<>();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(SchemaLoader.class.getName()).log(Level.SEVERE, null, e);
            //TODO - replace to coorect shutdown SpringBoot Application
            System.exit(-1);
        }
        loadSchemas();
    }

    private static void loadSchemas() {
        SCHEMAS.put("lab01", new Schema01().load("lab1.xml", "connection.xml"));
        SCHEMAS.put("lab02", new Schema02().load("lab2.xml", "connection.xml"));
    }

    // TODO - should refactor this -- need more dependency
    public static Schema getSchema(String labId) {
        return SCHEMAS.get(labId);
    }
}