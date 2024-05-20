package edu.tacoma.uw.csscompass;

import java.io.Serializable;

public class Classes implements Serializable {
    private int mId;
    private String mTitle;
    private String mCourse;

    public final static String ID = "id";
    public final static String COURSE = "course";
    public final static String TITLE = "title";

    public Classes(int mId, String mTitle, String mCourse) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mCourse = mCourse;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCourse() {
        return mCourse;
    }

    public void setCourse(String mCourse) {
        this.mCourse = mCourse;
    }
}
