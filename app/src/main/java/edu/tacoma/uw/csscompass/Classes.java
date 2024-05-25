/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

import java.io.Serializable;

/**
 * Serializable Classes for the course list.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class Classes implements Serializable {

    /** The ID of this class. */
    private int mId;

    /** The title of this course. */
    private String mTitle;

    /** The course of this class. */
    private String mCourse;

    /** The id String */
    public final static String ID = "id";

    /** The course String */
    public final static String COURSE = "course";

    /** The title String */
    public final static String TITLE = "title";

    /**
     * Initializes the data in this class.
     * @param mId the id of this class.
     * @param mTitle the title of this class.
     * @param mCourse the course of this class.
     */
    public Classes(int mId, String mTitle, String mCourse) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mCourse = mCourse;
    }

    /**
     * Returns the id of this class.
     * @return the id of this class as an int.
     */
    public int getId() {
        return mId;
    }

    /**
     * Sets the id of this class to the passed id.
     * @param mId the id to be assigned to his class as am int.
     */
    public void setId(int mId) {
        this.mId = mId;
    }

    /**
     * Returns the title of this class.
     * @return the title of this class as a String.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets the title of this class to the passed title.
     * @param mTitle the title to assign to this class.
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Returns the course of this class.
     * @return the course of this class as a String.
     */
    public String getCourse() {
        return mCourse;
    }

    /**
     * Sets the course of this class to the passed course.
     * @param mCourse the course to assign to this class as a String.
     */
    public void setCourse(String mCourse) {
        this.mCourse = mCourse;
    }
}
