package edu.tacoma.uw.csscompass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.tacoma.uw.csscompass.event.Event;
import edu.tacoma.uw.csscompass.event.EventBuilder;

public class EventBuilderTest {

    private Event validEvent;

    private Event eventNoTitle;

    private Event eventNoDate;

    private Event eventNoDescription;

    private Event eventNoLink;

    @Before
    public void setup() {
        String validEntry = "<entry>\n" +
                "        <id>http://uid.trumba.com/event/164164896</id>\n" +
                "        <category scheme=\"http://schemas.google.com/g/2005#kind\" term=\"http:" +
                "//schemas.google.com/g/2005#event\" />\n" +
                "        <title type=\"text\">Registration Period 2 - Summer Quarter</title>\n" +
                "        <content type=\"html\">Ongoing through Sunday, June 16, 2024 &lt;br/&gt" +
                ";&lt;br/&gt;Registration for summer quarter opens at midnight. Applies to newly" +
                " admitted students and returning former students. &lt;br/&gt;&lt;br/&gt;&lt;b&g" +
                "t;Event interval&lt;/b&gt;:&amp;nbsp;Ongoing event &lt;br/&gt;&lt;b&gt;Year&lt;" +
                "/b&gt;:&amp;nbsp;2024 &lt;br/&gt;&lt;b&gt;Quarter&lt;/b&gt;:&amp;nbsp;Spring &l" +
                "t;br/&gt;&lt;b&gt;Event Types&lt;/b&gt;:&amp;nbsp;Academics &lt;br/&gt;&lt;b&gt" +
                ";More info&lt;/b&gt;:&amp;nbsp;&lt;a href=\"https://registrar.washington.edu/re" +
                "gistration/registration-periods/\" target=\"_blank\" rel=\"noopener\" title=\"h" +
                "ttps://registrar.washington.edu/registration/registration-periods/\"&gt;registr" +
                "ar.washington.edu&amp;#8230;&lt;/a&gt;</content>\n" +
                "        <link rel=\"alternate\" type=\"text/html\" href=\"https://www.tacoma.uw" +
                ".edu/home/events-calendar?trumbaEmbed=view%3devent%26eventid%3d164164896\" />";

        String noTitleEntry = "<entry>\n" +
                "        <id>http://uid.trumba.com/event/164164896</id>\n" +
                "        <category scheme=\"http://schemas.google.com/g/2005#kind\" term=\"http:" +
                "//schemas.google.com/g/2005#event\" />\n" +
                "        <content type=\"html\">Ongoing through Sunday, June 16, 2024 &lt;br/&gt" +
                ";&lt;br/&gt;Registration for summer quarter opens at midnight. Applies to newly" +
                " admitted students and returning former students. &lt;br/&gt;&lt;br/&gt;&lt;b&g" +
                "t;Event interval&lt;/b&gt;:&amp;nbsp;Ongoing event &lt;br/&gt;&lt;b&gt;Year&lt;" +
                "/b&gt;:&amp;nbsp;2024 &lt;br/&gt;&lt;b&gt;Quarter&lt;/b&gt;:&amp;nbsp;Spring &l" +
                "t;br/&gt;&lt;b&gt;Event Types&lt;/b&gt;:&amp;nbsp;Academics &lt;br/&gt;&lt;b&gt" +
                ";More info&lt;/b&gt;:&amp;nbsp;&lt;a href=\"https://registrar.washington.edu/re" +
                "gistration/registration-periods/\" target=\"_blank\" rel=\"noopener\" title=\"h" +
                "ttps://registrar.washington.edu/registration/registration-periods/\"&gt;registr" +
                "ar.washington.edu&amp;#8230;&lt;/a&gt;</content>\n" +
                "        <link rel=\"alternate\" type=\"text/html\" href=\"https://www.tacoma.uw" +
                ".edu/home/events-calendar?trumbaEmbed=view%3devent%26eventid%3d164164896\" />";

        String noDateEntry = "<entry>\n" +
                "        <id>http://uid.trumba.com/event/164164896</id>\n" +
                "        <category scheme=\"http://schemas.google.com/g/2005#kind\" term=\"http:" +
                "//schemas.google.com/g/2005#event\" />\n" +
                "        <title type=\"text\">Registration Period 2 - Summer Quarter</title>\n" +
                "        <content type=\"html\">&lt;br/&gt" +
                ";&lt;br/&gt;Registration for summer quarter opens at midnight. Applies to newly" +
                " admitted students and returning former students. &lt;br/&gt;&lt;br/&gt;&lt;b&g" +
                "t;Event interval&lt;/b&gt;:&amp;nbsp;Ongoing event &lt;br/&gt;&lt;b&gt;Year&lt;" +
                "/b&gt;:&amp;nbsp;2024 &lt;br/&gt;&lt;b&gt;Quarter&lt;/b&gt;:&amp;nbsp;Spring &l" +
                "t;br/&gt;&lt;b&gt;Event Types&lt;/b&gt;:&amp;nbsp;Academics &lt;br/&gt;&lt;b&gt" +
                ";More info&lt;/b&gt;:&amp;nbsp;&lt;a href=\"https://registrar.washington.edu/re" +
                "gistration/registration-periods/\" target=\"_blank\" rel=\"noopener\" title=\"h" +
                "ttps://registrar.washington.edu/registration/registration-periods/\"&gt;registr" +
                "ar.washington.edu&amp;#8230;&lt;/a&gt;</content>\n" +
                "        <link rel=\"alternate\" type=\"text/html\" href=\"https://www.tacoma.uw" +
                ".edu/home/events-calendar?trumbaEmbed=view%3devent%26eventid%3d164164896\" />";

        String noDescriptionEntry = "<entry>\n" +
                "        <id>http://uid.trumba.com/event/164164896</id>\n" +
                "        <category scheme=\"http://schemas.google.com/g/2005#kind\" term=\"http:" +
                "//schemas.google.com/g/2005#event\" />\n" +
                "        <title type=\"text\">Registration Period 2 - Summer Quarter</title>\n" +
                "        <content type=\"html\">Ongoing through Sunday, June 16, 2024 &lt;b&g" +
                "t;Event interval&lt;/b&gt;:&amp;nbsp;Ongoing event &lt;br/&gt;&lt;b&gt;Year&lt;" +
                "/b&gt;:&amp;nbsp;2024 &lt;br/&gt;&lt;b&gt;Quarter&lt;/b&gt;:&amp;nbsp;Spring &l" +
                "t;br/&gt;&lt;b&gt;Event Types&lt;/b&gt;:&amp;nbsp;Academics &lt;br/&gt;&lt;b&gt" +
                ";More info&lt;/b&gt;:&amp;nbsp;&lt;a href=\"https://registrar.washington.edu/re" +
                "gistration/registration-periods/\" target=\"_blank\" rel=\"noopener\" title=\"h" +
                "ttps://registrar.washington.edu/registration/registration-periods/\"&gt;registr" +
                "ar.washington.edu&amp;#8230;&lt;/a&gt;</content>\n" +
                "        <link rel=\"alternate\" type=\"text/html\" href=\"https://www.tacoma.uw" +
                ".edu/home/events-calendar?trumbaEmbed=view%3devent%26eventid%3d164164896\" />";

        String noLinkEntry = "<entry>\n" +
                "        <id>http://uid.trumba.com/event/164164896</id>\n" +
                "        <category scheme=\"http://schemas.google.com/g/2005#kind\" term=\"http:" +
                "//schemas.google.com/g/2005#event\" />\n" +
                "        <title type=\"text\">Registration Period 2 - Summer Quarter</title>\n" +
                "        <content type=\"html\">Ongoing through Sunday, June 16, 2024 &lt;br/&gt" +
                ";&lt;br/&gt;Registration for summer quarter opens at midnight. Applies to newly" +
                " admitted students and returning former students. &lt;br/&gt;&lt;br/&gt;&lt;b&g" +
                "t;Event interval&lt;/b&gt;:&amp;nbsp;Ongoing event &lt;br/&gt;&lt;b&gt;Year&lt;" +
                "/b&gt;:&amp;nbsp;2024 &lt;br/&gt;&lt;b&gt;Quarter&lt;/b&gt;:&amp;nbsp;Spring &l" +
                "t;br/&gt;&lt;b&gt;Event Types&lt;/b&gt;:&amp;nbsp;Academics &lt;br/&gt;&lt;b&gt" +
                ";More info&lt;/b&gt;:&amp;nbsp;&lt;a href=\"https://registrar.washington.edu/re" +
                "gistration/registration-periods/\" target=\"_blank\" rel=\"noopener\" title=\"h" +
                "ttps://registrar.washington.edu/registration/registration-periods/\"&gt;registr" +
                "ar.washington.edu&amp;#8230;&lt;/a&gt;</content>\n";

        validEvent = EventBuilder.buildEvent(validEntry);
        eventNoTitle = EventBuilder.buildEvent(noTitleEntry);
        eventNoDate = EventBuilder.buildEvent(noDateEntry);
        eventNoDescription = EventBuilder.buildEvent(noDescriptionEntry);
        eventNoLink = EventBuilder.buildEvent(noLinkEntry);
    }

    @Test
    public void testRemoveHTML() {
        String input = "&lt;br /&gt;&amp;quot;&amp;amp;&amp;lt;&amp;gt;" +
                "&lt;/li&gt;&lt;/ul&gt;&lt;ul&gt; &lt;li&gt;&lt;/li&gt;&lt;li&gt;&lt;.*?&gt;" +
                "&amp;nbsp;ndash;";
        String expected = "\n\"&<>\n\n\n\n - \n - '-";
        String actual = EventBuilder.removeHTML(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventValidTitle() {
        String expected = "Registration Period 2 - Summer Quarter";
        String actual = validEvent.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventValidDate() {
        String expected = "Ongoing through Sunday, June 16, 2024";
        String actual = validEvent.getDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventValidDescription() {
        String expected = "Registration for summer quarter opens at midnight. Applies to newly a" +
                "dmitted students and returning former students. ";
        String actual = validEvent.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventValidLink() {
        String expected = "https://www.tacoma.uw.edu/home/events-calendar?trumbaEmbed=view%3deve" +
                "nt%26eventid%3d164164896";
        String actual = validEvent.getLink();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventNoTitle() {
        String expected = "No Title";
        String actual = eventNoTitle.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventNoDate() {
        String expected = "No Date";
        String actual = eventNoDate.getDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventNoDescription() {
        String expected = "No Description";
        String actual = eventNoDescription.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void testBuildEventNoLink() {
        String expected = "No Link";
        String actual = eventNoLink.getLink();
        assertEquals(expected, actual);
    }

}
