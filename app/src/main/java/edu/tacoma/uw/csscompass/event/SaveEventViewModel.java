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

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Objects;

import edu.tacoma.uw.csscompass.authentication.Account;

/**
 * A save event view model in charge of saving events in the database.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class SaveEventViewModel extends AndroidViewModel {

    /** The response that will contain the outcome of trying to add an event to the database. */
    private MutableLiveData<JSONObject> mResponse;

    /**
     * Sets up the response, initializing the view model with the passed application.
     * @param application the application used to initialize this View Model.
     */
    public SaveEventViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
    }

    /**
     * This adds the observer that will be looking for a response stored in this ViewModel.
     * @param owner the owner of the response as a LifecycleOwner.
     * @param observer the observer of the response as a Observer<? super JSONObject>.
     */
    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    /**
     * This handles the error response that gets generated when the call to add an event
     * to the database fails.
     *
     * @param error the error response as a VolleyError that specifies what the response was.
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
     * Saves the passed event in the database for the user that has the provided user email.
     *
     * @param userEmail the email of the user that wants to store the event.
     * @param event the event that we want to store in the database.
     */
    public void saveEvent(String userEmail, Event event) {
        String url = "https://students.washington.edu/djruiz49/db_css_compass/add_event.php";

        JSONObject body = new JSONObject();
        try {
            body.put("user_email", userEmail);
            body.put("title", event.getTitle());
            body.put("time", event.getTime());
            body.put("date", event.getDate());
            body.put("description", event.getDescription());
            body.put("link", event.getLink());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body, //no body for this get request
                mResponse::setValue,
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
