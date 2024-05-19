package edu.tacoma.uw.csscompass;

import static android.content.ContentValues.TAG;

import static edu.tacoma.uw.csscompass.EventBuilder.buildEvent;

import android.app.Application;
import android.util.Log;
import android.util.Xml;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.sun.syndication.feed.synd.SyndCategory;
//import com.sun.syndication.feed.synd.SyndEntry;
//import com.sun.syndication.feed.synd.SyndFeed;
//import com.sun.syndication.io.FeedException;
//import com.sun.syndication.io.SyndFeedInput;
//import com.sun.syndication.io.XmlReader;
////import com.sun.syndication.io.XmlReader;
//
//import org.json.JSONObject;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.xml.sax.InputSource;
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.StringReader;
//import java.net.MalformedURLException;
//import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EventViewModel extends AndroidViewModel {

    private MutableLiveData<String> mResponse;

    private MutableLiveData<List<Event>> mEventList;

    public EventViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue("");

        mEventList = new MutableLiveData<>();
        mEventList.setValue(new ArrayList<>());

    }

//    public void addResponseObserver(@NonNull LifecycleOwner owner,
//                                    @NonNull Observer<? super JSONObject> observer) {
//        mResponse.observe(owner, observer);
//    }

//    public void addEvent(String id, String kind, String name) {
//        String url = "https://students.washington.edu/djruiz49/add_event.php";
//        JSONObject body = new JSONObject();
//        try {
//            body.put("id", id);
//            body.put("kind", kind);
//            body.put("name", name);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Request request = new JsonObjectRequest(
//                Request.Method.POST,
//                url,
//                body, //no body for this get request
//                mResponse::setValue,
//                this::handleError);
//
//        Log.i("AnimalViewModel", request.getUrl().toString());
//        request.setRetryPolicy(new DefaultRetryPolicy(
//                10_000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        //Instantiate the RequestQueue and add the request to the queue
//        Volley.newRequestQueue(getApplication().getApplicationContext())
//                .add(request);
//    }

    // If we are unable to get the url
    private void handleError(final VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            mResponse.setValue("{" +
                    "error:\"" + error.getMessage() +
                    "\"}");
        } else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset())
                    .replace('\"', '\'');
            mResponse.setValue("{" +
                    "code:" + error.networkResponse.statusCode +
                    ", data:\"" + data +
                    "\"}");
        }
    }

    public void addEventListObserver(@NonNull LifecycleOwner owner,
                                     @NonNull Observer<? super List<Event>> observer) {
        mEventList.observe(owner, observer);
    }

//    private void handleResult(final String result) {
//        SyndFeedInput input = new SyndFeedInput();
//        try {
////            SyndFeed rssFeed = input.build(new XmlReader(new StringReader(result)));
//            SyndFeed rssFeed = input.build(new InputSource(new StringReader(result)));
//
//            // Since the data is large determine how many to display at a time.
////            int eventsToDisplay = 11;
//
//            List<SyndEntry> rssItems = new ArrayList<>();
//            rssItems.addAll(rssFeed.getEntries());
//
//            //first get the data, in this case from the feed
//            for (SyndEntry item : rssItems) {
////                //The title of the event
////                String title = item.getTitle();
////                // Mixed description
////                String description = item.getDescription().getValue();
////
////                // Using the description get the time along with the date
////                String date = "Ongoing";
////                // Extract the first <br/> content from the description
////                int brIndex = description.indexOf("<br/>");
////                if (brIndex != -1) {
////                    String firstBrContent = description.substring(0, brIndex);
////                    date = firstBrContent;
////                }
////
////                //Now get the time
////                String time = "No time";
////                if(date.endsWith("&nbsp;PDT ")){
////
////                }
////
////                Log.v(TAG, title);
////
////                Then create an event with the data.
////                Event event = new Event("title", "date", "description");
//                if(item.getContents() != null){
//                    Event event = EventBuilder.buildEvent(item);
//                    mEventList.getValue().add(event);
//                }
//            }

//            List<SyndCategory> rssDates = new ArrayList<>();
//             rssDates.addAll(rssFeed.getCategories());

//            if(!rssItems.isEmpty() && !rssDates.isEmpty()){
//                for (int i = 0; i <= eventsToDisplay; i++) {
//                    //first get the data
//                    // From the url thing in this case
//                    String title = rssItems.get(i).getTitle();
//                    String date = rssDates.get(i).getName();
//                    String description = rssItems.get(i).getAuthor();
//                    Log.v(TAG, title);
//
//                    //Then create an event with the data.
//                    Event event = new Event(title, date, description);
//                    mEventList.getValue().add(event);
//                }
//            }


            //Here is where we add the things to the constructor and to the event!
//            for (int i = 0; i < eventsToDisplay; i++) {
//                //first get the data
//                // From the url thing in this case
//                String title = rssFeed.getTitle();
//                String date = rssFeed.getAuthor();
//                String description = rssFeed.getDescription();
//                Log.v(TAG, title);
//
//                //Then create an event with the data.
//                Event event = new Event(title, date, description);
//                mEventList.getValue().add(event);
//            }

//        } catch (FeedException e) {
//            e.printStackTrace();
//            Log.e("ERROR!", "This is an error of the response of the rssURL");
//            Log.e("ERROR!", e.getMessage());
//        }
//        mEventList.setValue(mEventList.getValue());
//    }

//    private void handleResult(final String result) {
//        SyndFeedInput input = new SyndFeedInput();
//        try {
//            SyndFeed rssFeed = input.build(new StringReader(result));
//            // Since the data is large determine how many to display at a time.
////            int eventsToDisplay = 11;
//
//            List<SyndEntry> rssItems  = new ArrayList<>();
//            rssItems.addAll(rssFeed.getEntries());
//
//            //first get the data, in this case from the feed
//            for (SyndEntry item: rssItems) {
//                //The title of the event
//                String title = item.getTitle();
//                // Mixed description
//                String description = item.getDescription().getValue();
//
//                // Using the description get the time along with the date
//                String date = "Ongoing";
//
//                Log.v(TAG, title);
//                Event event = new Event(title, date, description);
//                mEventList.getValue().add(event);
//            }
//        } catch (FeedException e) {
//            e.printStackTrace();
//            Log.e("ERROR!", e.getMessage());
//        }
//        mEventList.setValue(mEventList.getValue());
//    }

    private void handleResult(final String result) {

//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser parser = factory.newPullParser();
//            //We give it the xml result
//            parser.setInput(new StringReader(result));
//
//            int eventType = parser.getEventType();
////            RssItem currentItem = null;
//            String text = "";
//
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                String tagName = parser.getName();
//                switch (eventType) {
//                    case XmlPullParser.START_TAG:
//                        if ("entry".equals(tagName)) {
//                            currentItem = new RssItem("", "", "");
//                        }
//                        break;
//                    case XmlPullParser.TEXT:
//                        text = parser.getText();
//                        break;
//                    case XmlPullParser.END_TAG:
//                        if (currentItem != null) {
//                            if ("title".equals(tagName)) {
//                                currentItem.title = text;
//                            } else if ("link".equals(tagName)) {
//                                currentItem.link = text;
//                            } else if ("content".equals(tagName)) {
//                                currentItem.description = text;
//                            } else if ("item".equals(tagName)) {
//                                items.add(currentItem);
//                            }
//                        }
//                        break;
//                }
//                eventType = parser.next();
//            }
//
//        } catch (XmlPullParserException e) {
//            throw new RuntimeException(e);
//        }

//        SyndFeedInput input = new SyndFeedInput();
//        try {
////            SyndFeed rssFeed = input.build(new XmlReader(new StringReader(result)));
//            SyndFeed rssFeed = input.build(new InputSource(new StringReader(result)));
//
//            // Since the data is large determine how many to display at a time.
//            int eventsToDisplay = 11;
//            int counter = 0;
//
//            List<SyndEntry> rssItems = new ArrayList<>();
//            rssItems.addAll(rssFeed.getEntries());
//
//            //first get the data, in this case from the feed
////            for (SyndEntry item : rssItems) {
//////                Then create an event with the data.
////                Event event = new Event("title", "date", "description");
////                Log.v(TAG, item.getDescription().getValue());
//////                if(item.getContents() != null && counter <= eventsToDisplay){
////////                    Log.v(TAG, item.);
//////                    Event event = EventBuilder.buildEvent(item);
//////                    mEventList.getValue().add(event);
//////                    counter++;
//////                }
////            }
//            for (SyndEntry item : rssItems) {
//                Event event = new Event("title", "date", "description");
//                Log.v(TAG,);
////                if(item != null) {
////                    Log.v(TAG, "Item is not null");
////                    if (item.getDescription() != null) {
////                        Log.v(TAG, "item categories is not null");
////                        if (item.getDescription().getValue() != null) {
////                            Log.v(TAG, "Item description value is not null");
////                            Log.v(TAG, item.getDescription().getValue());
////                        }
////                    }
////                }
//                mEventList.getValue().add(event);
//            }
//        } catch (FeedException e) {
//            e.printStackTrace();
//            Log.e("ERROR!", "This is an error of the response of the rssURL");
//            Log.e("ERROR!", e.getMessage());
//        }

//        String entryPattern = "(<entry>.*?</entry>)";
//        Pattern pattern = Pattern.compile(entryPattern);
//        Matcher matcher = pattern.matcher(result);
//
//        try {
//            XmlPullParser xmlParser = Xml.newPullParser();
//            //We give the input xml to the parser
//            xmlParser.setInput(new StringReader(result));
//            int typeE = xmlParser.getEventType();
//            String title = "no title";
//            String content = "no content";
//            String link = "no link";
//
//            while(typeE != XmlPullParser.END_DOCUMENT){
//                String currTag = "temp tag";
//                if(typeE == XmlPullParser.START_TAG){
//                    currTag = xmlParser.getName();
//                    if ("title".equals(currTag)) {
//                        title = xmlParser.nextText();
//                    } else if ("content".equals(currTag)) {
//                        content = xmlParser.nextText();
//                    } else if ("link".equals(currTag)) {
//                        link = xmlParser.nextText();
//                    }
//                }
//            }
//            Event event = new Event(title, link, content);
//            mEventList.getValue().add(event);
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//            Log.e("ERROR!", "This is an error of the response of the rssURL");
//            Log.e("ERROR!", e.getMessage());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        mEventList.setValue(mEventList.getValue());

        String entryPattern = "(<entry>.*?</entry>)";
        Pattern pattern = Pattern.compile(entryPattern, Pattern.DOTALL);
        Matcher entryMatcher = pattern.matcher(result);

        while(entryMatcher.find()){
            String xmlEntry = entryMatcher.group(1);
            Event event = buildEvent(xmlEntry);
            mEventList.getValue().add(event);
        }
        mEventList.setValue(mEventList.getValue());
    }

    //####################################3 for the rss and xml working
    public void getEvents() {
        String url = "https://www.trumba.com/calendars/tac_campus.xml";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
//                null, //no body for this get request
                this::handleResult,
                this::handleError);

        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }

//    public void getEvents() {
//        try {
//            URL url = new URL("https://www.trumba.com/calendars/tac_campus.xml");
//
//            InputStream inputXML = url.openConnection().getInputStream();
//
//            XmlPullParser XMLparser = Xml.newPullParser();
//            XMLparser.setFeature();
//        } catch (MalformedURLException mu){
//            mu.printStackTrace();
//            Log.e("ERROR", mu.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("ERROR", e.getMessage());
//        }
//    }
}
