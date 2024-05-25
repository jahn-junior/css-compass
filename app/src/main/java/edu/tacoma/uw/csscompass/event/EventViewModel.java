package edu.tacoma.uw.csscompass.event;

import static edu.tacoma.uw.csscompass.event.EventBuilder.buildEvent;

import android.app.Application;

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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An event view model
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class EventViewModel extends AndroidViewModel {

    /** The response that will we used for dealing with getting the events. */
    private MutableLiveData<String> mResponse;

    /** The list of events that will be displayed for the user. */
    private MutableLiveData<List<Event>> mEventList;

    /**
     * Initializes the response and the event list of this view model.
     *
     * @param application the application context to be passed to its parent.
     */
    public EventViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue("");

        mEventList = new MutableLiveData<>();
        mEventList.setValue(new ArrayList<>());

    }

    /**
     * Handles the error response generated when we fail to get the events from the database.
     * @param error the error as a VolleyError that will indicate the type of error generated
     *              when trying to get the events from the online source.
     */
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

    /**
     * Adds an observer to the list of events stored in this view model using the passed owner
     * and the passed observer.
     * @param owner the owner of the lifecycle.
     * @param observer the observer that will be looking at the event list stored in this
     *                event view model.
     */
    public void addEventListObserver(@NonNull LifecycleOwner owner,
                                     @NonNull Observer<? super List<Event>> observer) {
        mEventList.observe(owner, observer);
    }

    /**
     * Handles the result when we successfuly retrieve the elements from the xml file.
     * We parse through it using Patterns, initializing new events using the data in the
     * result and storing the data in the list of events stored in this event view model.
     *
     * @param result the result as a xml String containing the events to be parsed and stored.
     */
    private void handleResult(final String result) {

        String entryPattern = "(<entry>.*?</entry>)";
        Pattern pattern = Pattern.compile(entryPattern, Pattern.DOTALL);
        Matcher entryMatcher = pattern.matcher(result);

        boolean firstEvent = true;
        while(entryMatcher.find()){
            String xmlEntry = entryMatcher.group(1);
            Event event = buildEvent(xmlEntry);
            if(firstEvent){
                mEventList.setValue(new ArrayList<>());
                mEventList.getValue().add(new Event("Event", "Time", "Date", "Description", "link"));
                firstEvent = false;
            }
            mEventList.getValue().add(event);
        }
        mEventList.setValue(mEventList.getValue());
    }


    /** Requests the data from the database. */
    public void getEvents() {
        String url = "https://www.trumba.com/calendars/tac_campus.xml";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
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
}
