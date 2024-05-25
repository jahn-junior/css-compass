package edu.tacoma.uw.csscompass.event;

import java.io.Serializable;

/** An event class used to store event information.
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class Event implements Serializable {

    /** The time of this event. */
    private String mTime;

    /** The date of this event. */
    private String mDate;

    /** The title of this event. */
    private String mTitle;

    /** The description of this event. */
    private String mDescription;

    /** The link of this event to the event on the web. */
    private String mLink;

    /** The title String. */
    public final static String TITLE = "title";

    /** The time String. */
    public final static String TIME = "time";

    /** The date String. */
    public final static String DATE = "date";

    /** The description String. */
    public final static String DESCRIPTION = "description";

    /** The link String. */
    public final static String LINK = "link";

    /** The constructor for this event that initializes all the data of the event. */
    public Event(String mTitle, String mTime, String mDate, String mDescription, String mlink) {
        this.mTitle = mTitle;
        this.mTime = mTime;
        this.mDate = mDate;
        this.mDescription = mDescription;
        this.mLink = mlink;
    }

    /**
     * Returns the title of this event.
     * @return the title of this event a String.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Sets the title of this event to the passed title.
     * @param mTitle the title to assign to this event as a String.
     */
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Returns the time of this event.
     * @return the time of this event as a String.
     */
    public String getTime() {
        return mTime;
    }

    /**
     * Sets the time of this event to the passed time.
     * @param mTime the time to assign to this event as a String.
     */
    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    /**
     * Returns the date of this event.
     * @return the date of this event as a String.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Sets the date of this event to the passed date.
     * @param mDate the date to be assigned to this event as a String.
     */
    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * Returns the description of this event.
     * @return the description of this event as a String.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Sets the description of this event to the passed description.
     * @param mDescription the description to be assigned to this event as a String.
     */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Returns the link of this event.
     * @return the link of this event as a String.
     */
    public String getLink() {
        return mLink;
    }

    /**
     * Sets the link of tis event to the passed link.
     * @param mLink the link to be assigned to his event as a String.
     */
    public void setLink(String mLink) {
        this.mLink = mLink;
    }
}
