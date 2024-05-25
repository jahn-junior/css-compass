/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

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
 * View model for the course list.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class ClassesViewModel extends AndroidViewModel {

    /** The response in this view model used to manage the retrieval of classes in the database. */
    private MutableLiveData<JSONObject> mResponse;

    /** The list of classes. */
    private MutableLiveData<List<Classes>> mClassesList;

    /**
     * Initializes the response and list of classes in this view model
     * @param application the application context to pass to the parent.
     */
    public ClassesViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mClassesList = new MutableLiveData<>();
        mClassesList.setValue(new ArrayList<>());
    }

    /**
     * Adds an observer to the response using the passed owner and observer.
     * @param owner the life cycle owner.
     * @param observer the observer of the response.
     */
    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    /**
     * Handles the error generated when failing to retrieve the classes from the database.
     * @param error the error generated when failing to get the classes from the database as
     *              a VolleyError.
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
     * Adds an observer for the list or classes stored in this view model.
     * @param owner the lifecycle owner.
     * @param observer the observer of the list of classes.
     */
    public void addClassListObserver(@NonNull LifecycleOwner owner,
                                      @NonNull Observer<? super List<Classes>> observer) {
        mClassesList.observe(owner, observer);
    }

    /**
     * Handles the result obtained when trying to get the classes from the database.
     * @param result the result containing the classes from the database as a JSONObject.
     */
    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("classes");
            JSONArray arr = new JSONArray(data);
            mClassesList.setValue(new ArrayList<>());
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Classes classes = new Classes(Integer.parseInt(obj.getString(Classes.ID)),
                        obj.getString(Classes.COURSE),
                        obj.getString(Classes.TITLE));
                mClassesList.getValue().add(classes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR!", e.getMessage());
        }
        mClassesList.setValue(mClassesList.getValue());
    }

    /** Retrieves the classes from the database. */
    public void getClasses() {
        String url =
                "https://students.washington.edu/danieoum/db_css_compass/get_classes.php";

        Request request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, //no body for this get request
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
