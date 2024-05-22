/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass.authentication;

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

/**
 * A register view model that handles registering a user into the app.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RegisterViewModel extends AndroidViewModel {

    /**
     * The response that will contain the outcome of trying to add a user account to the database
     */
    private MutableLiveData<JSONObject> mResponse;

    /**
     * Sets up the response, initializing the view model with the passed application.
     *
     * @param application the application used to initialize this View Model.
     */
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());

    }

    /**
     * This adds the observer that will be looking for a response stored in this ViewModel.
     *
     * @param owner the owner of the response as a LifecycleOwner.
     * @param observer the observer of the response as a Observer<? super JSONObject>.
     */
    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    /**
     * This handles the error response that gets generated when the call to adda user
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
     * Adds a user to the database using the data of the passed Account object.
     *
     * @param account the account as an Account object with the information that is going
     *                to be stored in the app's database.
     */
    public void addUser(Account account) {
        String url = "https://students.washington.edu/djruiz49/db_css_compass/register_user.php";
        JSONObject body = new JSONObject();
        try {
            body.put("email", account.getEmail());
            body.put("password", account.getPassword());
            body.put("first_name", account.getFirstName());
            body.put("last_name", account.getLastName());
            body.put("student_number", account.getStudentNumber());
            body.put("enrollment_year", account.getEnrollmentYear());
            body.put("graduation_year", account.getGraduationYear());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body, //no body for this get request
                mResponse::setValue,
                this::handleError);

        Log.i("RegisterViewModel", request.getUrl().toString());
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }
}
