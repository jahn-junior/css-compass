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

public class RetrieveEventsViewModel extends AndroidViewModel {

    private MutableLiveData<JSONObject> mResponse;

    private MutableLiveData<List<Event>> mEventList;

    public RetrieveEventsViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mEventList = new MutableLiveData<>();
        mEventList.setValue(new ArrayList<>());
    }

    public void addEventListObserver(@NonNull LifecycleOwner owner,
                                      @NonNull Observer<? super List<Event>> observer) {
        mEventList.observe(owner, observer);
    }

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
