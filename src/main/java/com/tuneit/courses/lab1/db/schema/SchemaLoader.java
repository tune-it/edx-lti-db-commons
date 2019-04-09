package com.tuneit.courses.lab1.db.schema;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */
public class SchemaLoader {
    private static final ArrayList<Schema01> SCHEMA_01s = new ArrayList<>();

    static {
        //System.out.println("Loading underlying JDBC driver.");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(SchemaLoader.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("Cannot continue without Postgresql JDBC driver.");
            //TODO - replace to coorect shutdown SpringBoot Application
            System.exit(-1);
        }
        //System.out.println("Done.");

        SCHEMA_01s.add(new Schema01().load("lab1.xml", "connection.xml"));
        //TODO add other SCHEMA_01s
    }

    // TODO - should refactor this -- need more dependency
    public static Schema01 getSchema(String yearOfStudy, String studentId) {
        if (yearOfStudy == null || studentId == null)
            throw new IllegalArgumentException("Cant get Schema01 for student and year of study. Null args.");
        int seed = (yearOfStudy + "-" + studentId).toUpperCase().hashCode();
        int schemaNo = (new Random(seed)).nextInt(SCHEMA_01s.size());
        return SchemaLoader.getSchema(schemaNo);
    }

    public static Schema01 getSchema(int index) {
        return SCHEMA_01s.get(index);
    }

    public static int getSchemasCount() {
        return SCHEMA_01s.size();
    }

    public ListIterator<Schema01> schemas() {
        return SCHEMA_01s.listIterator();
    }

}