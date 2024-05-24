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

    //Here we make sure to parse the string, create the events and add them the list of events.
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

    //We request the data from the events database.
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
