package com.tuneit.courses.db.generate;

import com.tuneit.courses.db.checking.SchemaConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Schema loader
 * @author serge
 */

@Component
public abstract class SchemaLoader {
    protected HashMap<Integer, Schema> schemas = new HashMap<>();

    @Autowired
    protected SchemaConnection schemaConnection;

    @PostConstruct
    public void init() {
        loadDBDriver();
        loadSchemas();
    }

    /**
     * load database driver
     */
    protected abstract void loadDBDriver();

    /**
     * load data for task generation
     */
    protected abstract void loadSchemas();

    /**
     * searches for data for the selected lab
     * @param labId identifier lab
     * @return data to generate
     */
    public Schema getSchema(Integer labId) {
        return schemas.get(labId);
    }
}