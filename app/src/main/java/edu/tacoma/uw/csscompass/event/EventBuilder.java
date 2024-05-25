package edu.tacoma.uw.csscompass.event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.tacoma.uw.csscompass.event.Event;

/**
 * A event builder class that creates event objects using strings.
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class EventBuilder {

    /** The pattern used to find the title of the event. */
    public static final String TITLEPATTERN = "<title.*?</title>";

    /** The pattern used to find the time of the event.*/
    public final static String[] TIMEPATTERNS = {
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)"};

    /** Pattern used to find the date of the event. */
    public static final String DATEPATTERN = "(\\w+, \\w+ \\d{1,2}, \\d{4})|"                   //Match for dates like "Thursday, May 16, 2024"
            + "(\\w+, \\w+ \\d{1,2}(?:&nbsp;)?(?:&ndash;| - )\\w+, \\w+ \\d{1,2}, \\d{4})|"     // Match for dates like "Thursday, May 16&nbsp;&ndash; Sunday, June 16, 2024"
            + "(Ongoing through \\w+, \\w+ \\d{1,2}, \\d{4})";                                  // Match for dates like "Ongoing through Friday, May 31, 2024"

    /** The pattern used to find the description of the event. */
    public static final String DESCRIPTIONPATTERN = "&lt;br/&gt;&lt;br/&gt;(?:(?!&lt;img).)*?&lt;br/&gt;&lt;br/&gt;";

    /** The pattern used to look for the link of the event. */
    public static final String LINKPATTERN = "<link rel=\"alternate\" type=\"text/html\" href=\".*?\" />";

    /** The pattern used to identify some xml strings. */
    public static final String XMLPATTERN = "<[^>]+>";

    /**
     * Build and event object using the passed xml entry as a string. If some of the elements
     * of the event are not found within the passed entry, it will set them as "no (elementName)"
     *
     * @param item the item that will we parsed through using patterns to identify the title, time,
     *             date, description and link of the event as a String.
     * @return an event as Event object containing all the data of the event.
     */
    public static Event buildEvent(String item){
        //first compile the patterns
        Pattern titlePa = Pattern.compile(TITLEPATTERN);
        Pattern datePa = Pattern.compile(DATEPATTERN);
        Pattern descriptionPa = Pattern.compile(DESCRIPTIONPATTERN);
        Pattern linkPa = Pattern.compile(LINKPATTERN);

        // We look for a time
        String title = "No Title";
        Matcher titleMatch = titlePa.matcher(item);
        if(titleMatch.find()){
            title = titleMatch.group(0).replaceAll(XMLPATTERN, "").replaceAll("&amp;", "&");
        }

        // We look for a time
        String time = "";
        for (int i = 0; i < TIMEPATTERNS.length; i++){
            Pattern timeParser = Pattern.compile(TIMEPATTERNS[i]);
            Matcher timeMatcher = timeParser.matcher(item);
            if(timeMatcher.find()){
                time = timeMatcher.group(0).replaceAll("&amp;nbsp;", " ").replaceAll("&amp;ndash;", "-");
                break;
            }
        }

        // We look for a date
        String date = "No Date";
        Matcher dateMatch = datePa.matcher(item);
        if(dateMatch.find()){
            date = removeHTML(dateMatch.group(0));
        }

        // We look for a description
        String description = "No Description";
        Matcher descriptionMatch = descriptionPa.matcher(item);
        if(descriptionMatch.find()){
            description = removeHTML(descriptionMatch.group(0));
        }

        String link = "No Link";
        Matcher linkMatch = linkPa.matcher(item);
        if(linkMatch.find()){
            link = linkMatch.group(0)
                    .replaceAll("<link rel=\"alternate\" type=\"text/html\" href=\"", "")
                    .replaceAll("\" />", "");
        }

        return new Event(title, time, date, description, link);
    }

    /**
     * Removes the html elements in the passed input and replaces them with their actual values,
     * or just removes them.
     * @param input the input html that we will remove the elements from.
     * @return the input html as a String without the html elements.
     */
    public static String removeHTML(String input){

        return input
                .replaceAll("&lt;br /&gt;", "\n")
                .replaceAll("&amp;quot;", "\"")
                .replaceAll("&amp;amp;", "&")
                .replaceAll("&amp;#\\d{1,6};", " ")
                .replaceAll("&amp;lt;", "<")
                .replaceAll("&amp;gt;", ">")
                .replaceAll("&lt;/li&gt;&lt;/ul&gt;", "\n\n")
                .replaceAll("&lt;ul&gt; &lt;li&gt;", "\n\n - ")
                .replaceAll("&lt;/li&gt;&lt;li&gt;", "\n - ")
                .replaceAll("&lt;.*?&gt;", "")
                .replaceAll("&amp;", "'")
                .replaceAll("nbsp;", "")
                .replaceAll("ndash;", "-");
    }
}
