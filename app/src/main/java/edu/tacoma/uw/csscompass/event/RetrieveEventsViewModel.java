package edu.tacoma.uw.csscompass.event;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A view model used for retrieving events saved by the user.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RetrieveEventsViewModel extends AndroidViewModel {

    /** The response used to deal with the outcome of trying to retrieve the saved events. */
    private MutableLiveData<JSONObject> mResponse;

    /** The list of events that were stored by the user. */
    private MutableLiveData<List<Event>> mEventList;

    /**
     * Initializes the response and list of saved events inside of this view model.
     * @param application the application context to be passed to the parent.
     */
    public RetrieveEventsViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mEventList = new MutableLiveData<>();
        mEventList.setValue(new ArrayList<>());
    }

    /**
     * Adds an observer to the list of saved events using the passed owner and observer.
     * @param owner the life cycle owner.
     * @param observer the observer that will be looking at the list of user saved events.
     */
    public void addEventListObserver(@NonNull LifecycleOwner owner,
                                      @NonNull Observer<? super List<Event>> observer) {
        mEventList.observe(owner, observer);
    }

    /**
     * Handles the error generated when trying to retrieve the events saved by the user
     * on the database.
     *
     * @param error the error as a VolleyError containing information about the error.
     */
    private void handleError(final VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            try {
                mResponse.setValue(new JSONObject("{" +
                        "error:\"" + error.getMessage() +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        } else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset())
                    .replace('\"', '\'');
            try {
                mResponse.setValue(new JSONObject("{" +
                        "code:" + error.networkResponse.statusCode +
                        ", data:\"" + data +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        }
    }

    /**
     * Handles the result generated when successfully retrieving the events saved by the
     * user on the database.
     * Parsing through it and storing each event on even objects and then storing them in
     * the list of user events.
     *
     * @param result the result containing the user saved events as a JSONObject.
     */
    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("events");
            JSONArray arr = new JSONArray(data);
            if(arr.length() > 0){
                mEventList.setValue(new ArrayList<>());
                Event titleEvent = new Event("Event", "", "Date", "", "");
                mEventList.getValue().add(titleEvent);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    Event event = new Event(obj.getString(Event.TITLE),
                            obj.getString(Event.TIME),
                            obj.getString(Event.DATE),
                            obj.getString(Event.DESCRIPTION),
                            obj.getString(Event.LINK));
                    mEventList.getValue().add(event);
                }
            } else {
                Event titleEvent = new Event("Events found!", "", "No", "", "");
                mEventList.getValue().add(titleEvent);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR!", e.getMessage());
        }
        mEventList.setValue(mEventList.getValue());
    }

    /**
     * Retrieves the user saved events from the database by the user with the passed email.
     * @param userEmail the email as a String of the user from whom we want to obtain their events
     *                  that have already been stored in the database.
     */
    public void getSavedEvents(String userEmail) {
        String url = "https://students.washington.edu/djruiz49/db_css_compass/get_events.php";

        JSONObject body = new JSONObject();
        try {
            body.put("email", userEmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
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
