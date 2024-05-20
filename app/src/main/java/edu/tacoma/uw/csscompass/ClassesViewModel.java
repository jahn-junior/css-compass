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

public class ClassesViewModel extends AndroidViewModel {

    private MutableLiveData<JSONObject> mResponse;

    private MutableLiveData<List<Classes>> mClassesList;

    public ClassesViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mClassesList = new MutableLiveData<>();
        mClassesList.setValue(new ArrayList<>());
    }

    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
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

//    public void addAnimal(String id, String kind, String name) {
//        String url = "https://students.washington.edu/danieoum/add_animal.php";
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

    public void addAnimalListObserver(@NonNull LifecycleOwner owner,
                                      @NonNull Observer<? super List<Classes>> observer) {
        mClassesList.observe(owner, observer);
    }

    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("classes");
            JSONArray arr = new JSONArray(data);
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
