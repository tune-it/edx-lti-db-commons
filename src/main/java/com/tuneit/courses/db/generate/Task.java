package com.tuneit.courses.db.generate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Stores data for randomness when generating tasks, as well lab id and task id
 */
@ToString
public class Task {

    private String id; // yearOfStudy + studentId + labId + taskId + variant
    private String yearOfStudy;
    @Getter
    @Setter
    private String studentId;
    @Getter
    @Setter
    private int labId;
    @Getter
    @Setter
    private int taskId;
    @Getter
    @Setter
    private String variant;
    @Getter
    @Setter
    private String question;
    @Getter
    @Setter
    private boolean isComplete;
    @Getter
    @Setter
    private String answer;
    @Getter
    @Setter
    private float rating;

    public String getId() {
        if (getYearOfStudy() == null || studentId == null || variant == null) {
            throw new RuntimeException("Task ID could not be generated. Please fill required fields in Task");
        }
        id = yearOfStudy + "-" + studentId + ":" +
                labId + "-" + taskId + "-" + variant;
        return id;
    }

    public String getYearOfStudy() {
        if (yearOfStudy == null) {
            int educationYear1;
            int educationYear2;
            //set current education year
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            if (month >= 8) {
                educationYear1 = year;
                educationYear2 = year + 1;
            } else {
                educationYear1 = year - 1;
                educationYear2 = year;
            }
            setYearOfStudy(educationYear1 + "/" + educationYear2);
        }
        return yearOfStudy;
    }

    public Task setYearOfStudy(String yearOfStudy) {
        StringTokenizer t = new StringTokenizer(yearOfStudy, "/");
        int year1;
        int year2;
        try {
            year1 = Integer.parseInt(t.nextToken());
            year2 = Integer.parseInt(t.nextToken());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Education year must be if form YYYY/YYYY+1 and YYYY must be a number");
        } catch (NoSuchElementException e) {
            throw new NumberFormatException("There has to be education year in form YYYY/YYYY+1 after ':'");
        }
        if (year1 != year2 - 1) {
            throw new NumberFormatException("Education year must be if form YYYY/YYYY+1");
        }
        if (year1 < 2000 || year1 > 2050) {
            throw new NumberFormatException("Education year has to be more than 2000 and less than 2050");
        }
        //reassembly
        this.yearOfStudy = year1 + "/" + year2;
        id = null;
        return this;
    }

    public Random getRandom() {
        return new Random(getId().toUpperCase().hashCode() * 7);
    }

}
