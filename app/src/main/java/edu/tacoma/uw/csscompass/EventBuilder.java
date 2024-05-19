package edu.tacoma.uw.csscompass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventBuilder {

    //Pattern for title
    public static final String TITLEPATTERN = "<title.*?</title>";

    // pattern for the time
//    public static final String TIMEPATTERN = "\\d{1,2}(:\\d{2})?&nbsp;&ndash;&nbsp;\\d{1,2}:\\d{2}&nbsp;(a|p)\\.m\\.&nbsp;\\w+";
//    public static final String TIMEPATTERN = "(\\d{1,2}:\\d{2})&nbsp;&ndash;&nbsp;(\\d{1,2}:\\d{2})&nbsp;([ap]\\.m\\.)";
//    public static final String FIRSTTIMEPATTERN = "\\d{1,2}:\\d{2}&.*?(\\d{1,2}:\\d{2}).*?([ap]\\.m\\.)";
//
//    public static final String SECONDTIMEPATTERN = "\\d{1,2}:\\d{2}";
    //9&nbsp;&ndash;&nbsp;11&nbsp;a.m.
    //9&nbsp;&ndash;&nbsp;11:30&nbsp;a.m.
    //9:30&nbsp;&ndash;&nbsp;11&nbsp;a.m.
    //9:30&nbsp;&ndash;&nbsp;11:30&nbsp;a.m.
    //9&nbsp;&ndash;&nbsp;11&nbsp;p.m.
    //9&nbsp;&ndash;&nbsp;11:30&nbsp;p.m.
    //9&amp;nbsp;a.m.&amp;nbsp;&amp;ndash;&amp;nbsp;12&amp;nbsp;p.m.
    //9&amp;nbsp;a.m.&amp;nbsp;&amp;ndash;&amp;nbsp;12&amp;nbsp;p.m.
    //2:30&amp;nbsp;&amp;ndash;&amp;nbsp;4&amp;nbsp;p.m.

    // real ones
    //9&amp;nbsp;&amp;ndash;&amp;nbsp;11:30&amp;nbsp;a.m.
    //9&amp;nbsp;&amp;ndash;&amp;nbsp;9:15&amp;nbsp;a.m
    //11&amp;nbsp;&amp;ndash;&amp;nbsp;11:15&amp;nbsp;a.m.
    //10&amp;nbsp;&amp;ndash;&amp;nbsp;11&amp;nbsp;a.m.
    //9&amp;nbsp;&amp;ndash;&amp;nbsp;9:15&amp;nbsp;a.m.
    //11&amp;nbsp;&amp;ndash;&amp;nbsp;11:15&amp;nbsp;a.m.
    //9&amp;nbsp;&amp;ndash;&amp;nbsp;9:15&amp;nbsp;a.m.
    //11&amp;nbsp;&amp;ndash;&amp;nbsp;11:15&amp;nbsp;a.m.

    //1&amp;nbsp;&amp;ndash;&amp;nbsp;2&amp;nbsp;p.m.
    //1&amp;nbsp;&amp;ndash;&amp;nbsp;2&amp;nbsp;p.m.
    //2&amp;nbsp;&amp;ndash;&amp;nbsp;4&amp;nbsp;p.m.
    //3&amp;nbsp;&amp;ndash;&amp;nbsp;6&amp;nbsp;p.m.
    //10&amp;nbsp;&amp;ndash;&amp;nbsp;11&amp;nbsp;a.m.
    //12&amp;nbsp;&amp;ndash;&amp;nbsp;1&amp;nbsp;p.m.

    //2:30&amp;nbsp;&amp;ndash;&amp;nbsp;4&amp;nbsp;p.m.
    //4:30&amp;nbsp;&amp;ndash;&amp;nbsp;7&amp;nbsp;p.m.

    //9:30&amp;nbsp;&amp;ndash;&amp;nbsp;11:30&amp;nbsp;a.m.
    //12:30&amp;nbsp;&amp;ndash;&amp;nbsp;1:30&amp;nbsp;p.m.
    //3:30&amp;nbsp;&amp;ndash;&amp;nbsp;5:30&amp;nbsp;p.m.
    //6:15&amp;nbsp;&amp;ndash;&amp;nbsp;6:45&amp;nbsp;a.m.
    //7:15&amp;nbsp;&amp;ndash;&amp;nbsp;7:45&amp;nbsp;a.m.

    //9&amp;nbsp;a.m.&amp;nbsp;&amp;ndash;&amp;nbsp;12:30&amp;nbsp;p.m.
    //11&amp;nbsp;a.m.&amp;nbsp;&amp;ndash;&amp;nbsp;1&amp;nbsp;p.m.

    //Created
    //9:30&amp;nbsp;a.m.&amp;nbsp;&amp;ndash;&amp;nbsp;12:30&amp;nbsp;p.m.
    //11:30&amp;nbsp;p.m.&amp;nbsp;&amp;ndash;&amp;nbsp;1&amp;nbsp;p.m.

//    public final static String[] TIMEPATTERNS = {"\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
//            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)"};

    public final static String[] TIMEPATTERNS = {
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}:\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}:\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;([ap]\\.m\\.)&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)",
            "\\d{1,2}&amp;nbsp;&amp;ndash;&amp;nbsp;\\d{1,2}&amp;nbsp;([ap]\\.m\\.)"};

    //Pattern for date
//    public static final String DATEPATTERN = "\\b\\w+day,\\b\\s+\\w+\\s+\\d{1,2}(,\\s+\\d{4})?(&nbsp;&ndash;\\b\\w+day,\\b\\s+\\w+\\s+\\d{1,2},\\s+\\d{4})?";
    public static final String DATEPATTERN = "(\\w+, \\w+ \\d{1,2}, \\d{4})|"                   //Match for dates like "Thursday, May 16, 2024"
            + "(\\w+, \\w+ \\d{1,2}(?:&nbsp;)?(?:&ndash;| - )\\w+, \\w+ \\d{1,2}, \\d{4})|"     // Match for dates like "Thursday, May 16&nbsp;&ndash; Sunday, June 16, 2024"
            + "(Ongoing through \\w+, \\w+ \\d{1,2}, \\d{4})";                                  // Match for dates like "Ongoing through Friday, May 31, 2024"
    // Looking for Thursday, May 16, 2024, 2:30&nbsp;&ndash;&nbsp;3:30&nbsp;p.m. <br/>

    //Patterb fir description
    public static final String DESCRIPTIONPATTERN = "<br/><br/>[a-zA-Z0-9].*?<br/><br/>";

    //Xml or html pattern
    public static final String XMLPATTERN = "<[^>]+>";

//    public static Event buildEvent(SyndEntry item){
//        //first compile all the patterns
//        Pattern timePa = Pattern.compile(TIMEPATTERN);
//        Pattern datePa = Pattern.compile(DATEPATTERN);
//        Pattern descriptionPa = Pattern.compile(DESCRIPTIONPATTERN);

//        //We look for a time
//        String time = "no time";
//        Matcher timeMatch = timePa.matcher(item.getDescription().getValue());
//        if(timeMatch.find()){
//            time = timeMatch.group(0);
//        }
//
//        //We look for a date
//        String date = "no date";
//        Matcher dateMatch = datePa.matcher(item.getDescription().getValue());
//        if(dateMatch.find()){
//            date = dateMatch.group(0);
//        }
//
//        //We look for a description
//        String description = "no Description";
//        Matcher descriptionMatch = descriptionPa.matcher(item.getDescription().getValue());
//        if(descriptionMatch.find()){
//            description = descriptionMatch.group(0);
//        }

        //We get the title
//        String title = item.getTitle();

//        return new Event(title, "", "");
//    }

    /**
     * Build and event object using the passed xml entry as a string. If some of the elements
     * of the event are not found within the passed entry, it will set them as "no (elementName)"
     */
    public static Event buildEvent(String item){
        //first compile all the patterns
        Pattern titlePa = Pattern.compile(TITLEPATTERN);
//        Pattern firTimePa = Pattern.compile(FIRSTTIMEPATTERN);
//        Pattern secTimePa = Pattern.compile(SECONDTIMEPATTERN);
        Pattern datePa = Pattern.compile(DATEPATTERN);
        Pattern descriptionPa = Pattern.compile(DESCRIPTIONPATTERN);

        //We look for a time
        String title = "no title";
        Matcher titleMatch = titlePa.matcher(item);
        if(titleMatch.find()){
            title = titleMatch.group(0).replaceAll(XMLPATTERN, "").replaceAll("&amp;", "&");
        }

        //We look for a time
        String time = "                     ";
//        Matcher firTimeMatch = firTimePa.matcher(item);
//        Matcher secTimeMatch = secTimePa.matcher(item);
//        if(firTimeMatch.find() && false){
//            time = firTimeMatch.group(0).replaceAll("nbsp;", "").replaceAll("ndash;", "-").replaceAll("&amp;", "");
//        } else if (secTimeMatch.find()){
//            time = secTimeMatch.group(0).replaceAll("nbsp;", "").replaceAll("ndash;", "-").replaceAll("&amp;", "");
//        }
        for(int i = 0; i < TIMEPATTERNS.length; i++){
            Pattern timeParser = Pattern.compile(TIMEPATTERNS[i]);
            Matcher timeMatcher = timeParser.matcher(item);
            if(timeMatcher.find()){
                time = timeMatcher.group(0).replaceAll("nbsp;", "").replaceAll("ndash;", "-").replaceAll("&amp;", "");
                break;
            }
        }
//        Matcher firTimeMatch = firTimePa.matcher(item);
//        Matcher secTimeMatch = secTimePa.matcher(item);
//        if(firTimeMatch.find() && false){
//            time = firTimeMatch.group(0).replaceAll("nbsp;", "").replaceAll("ndash;", "-").replaceAll("&amp;", "");
//        } else if (secTimeMatch.find()){
//            time = secTimeMatch.group(0).replaceAll("nbsp;", "").replaceAll("ndash;", "-").replaceAll("&amp;", "");
//        }

        //We look for a date
        String date = "no date";
        Matcher dateMatch = datePa.matcher(item);
        if(dateMatch.find()){
            date = dateMatch.group(0).replaceAll(XMLPATTERN, "");
        }

        //We look for a description
        String description = "no Description";
        Matcher descriptionMatch = descriptionPa.matcher(item);
        if(descriptionMatch.find()){
            description = descriptionMatch.group(0).replaceAll(XMLPATTERN, "");
        }

        return new Event(title, time, date, description);
    }
}
