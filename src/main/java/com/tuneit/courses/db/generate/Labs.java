package com.tuneit.courses.db.generate;

/**
 * Lab factory
 */
public interface Labs {
    /**
     * Construct lab by it's number
     * @param labId lab number
     * @return generated lab
     */
    Lab getLab(int labId);
}
