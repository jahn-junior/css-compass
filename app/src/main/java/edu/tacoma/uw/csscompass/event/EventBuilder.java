package edu.tacoma.uw.csscompass.event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.tacoma.uw.csscompass.event.Event;

public class EventBuilder {

    //Pattern for title
    public static final String TITLEPATTERN = "<title.*?</title>";

    //Patterns used to find the time
    public final static String[] TIMEPATTERNS = {
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)"};

    //Pattern used to look for date
    public static final String DATEPATTERN = "(\\w+, \\w+ \\d{1,2}, \\d{4})|"                   //Match for dates like "Thursday, May 16, 2024"
            + "(\\w+, \\w+ \\d{1,2}(?:&nbsp;)?(?:&ndash;| - )\\w+, \\w+ \\d{1,2}, \\d{4})|"     // Match for dates like "Thursday, May 16&nbsp;&ndash; Sunday, June 16, 2024"
            + "(Ongoing through \\w+, \\w+ \\d{1,2}, \\d{4})";                                  // Match for dates like "Ongoing through Friday, May 31, 2024"

    //Pattern used to look for description
    public static final String DESCRIPTIONPATTERN = "&lt;br/&gt;&lt;br/&gt;(?:(?!&lt;img).)*?&lt;br/&gt;&lt;br/&gt;";

    //Pattern used to look for link
    public static final String LINKPATTERN = "<link rel=\"alternate\" type=\"text/html\" href=\".*?\" />";

    //Xml or html pattern
    public static final String XMLPATTERN = "<[^>]+>";


    /**
     * Build and event object using the passed xml entry as a string. If some of the elements
     * of the event are not found within the passed entry, it will set them as "no (elementName)"
     */
    public static Event buildEvent(String item){
        //first compile the patterns
        Pattern titlePa = Pattern.compile(TITLEPATTERN);
        Pattern datePa = Pattern.compile(DATEPATTERN);
        Pattern descriptionPa = Pattern.compile(DESCRIPTIONPATTERN);
        Pattern linkPa = Pattern.compile(LINKPATTERN);

        //We look for a time
        String title = "no title";
        Matcher titleMatch = titlePa.matcher(item);
        if(titleMatch.find()){
            title = titleMatch.group(0).replaceAll(XMLPATTERN, "").replaceAll("&amp;", "&");
        }

        //We look for a time
        String time = "";
        for(int i = 0; i < TIMEPATTERNS.length; i++){
            Pattern timeParser = Pattern.compile(TIMEPATTERNS[i]);
            Matcher timeMatcher = timeParser.matcher(item);
            if(timeMatcher.find()){
                time = timeMatcher.group(0).replaceAll("&amp;nbsp;", " ").replaceAll("&amp;ndash;", "-");
                break;
            }
        }

        //We look for a date
        String date = "no date";
        Matcher dateMatch = datePa.matcher(item);
        if(dateMatch.find()){
            date = removeHTML(dateMatch.group(0));
        }

        //We look for a description
        String description = "no Description";
        Matcher descriptionMatch = descriptionPa.matcher(item);
        if(descriptionMatch.find()){
            description = removeHTML(descriptionMatch.group(0));
        }

        String link = "no link";
        Matcher linkMatch = linkPa.matcher(item);
        if(linkMatch.find()){
            link = linkMatch.group(0)
                    .replaceAll("<link rel=\"alternate\" type=\"text/html\" href=\"", "")
                    .replaceAll("\" />", "");
        }

        return new Event(title, time, date, description, link);
    }

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
