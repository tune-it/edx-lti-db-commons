package com.tuneit.courses.db.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for data extraction and processing
 */
public class Schema {
    public static <T> T getRandomElement(Random random, List<T> listElement) {
        return listElement.get(random.nextInt(listElement.size()));
    }

    public static <T extends Clone<T>> List<T> cloneList(List<T> tables) {
        List<T> cloneTables = new ArrayList<>();
        for (Clone<T> clone : tables) {
            cloneTables.add(clone.clone());
        }
        return cloneTables;
    }
}
