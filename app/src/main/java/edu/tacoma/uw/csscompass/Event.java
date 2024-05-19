package edu.tacoma.uw.csscompass;

import java.io.Serializable;

public class Event implements Serializable {

    private String mTime;

    private String mDate;

    private String mTitle;

    private String mDescription;

    private String mCampusLocation;

    private String mOnlineMeetingLink;

    private String mCampusRoom;

    private String mEventInterval;

    private String mEventType;

    private String mLink;

    public final static String TITLE = "title";

    public final static String DATE = "date";

    public final static String DESCRIPTION = "description";

    public Event(String mTitle, String mTime, String mDate, String mDescription) {
        this.mTitle = mTitle;
        this.mTime = mTime;
        this.mDate = mDate;
        this.mDescription = mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
