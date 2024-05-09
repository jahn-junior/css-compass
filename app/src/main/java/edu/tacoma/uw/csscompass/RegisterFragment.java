package edu.tacoma.uw.csscompass;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.csscompass.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding mBinding;

    private UserViewModel mUserViewModel;

    // Binding is assigned to the View Bindings (converts the xml into objects that we can interact with programmatically).
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mUserViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    // We add a listener to the register button once the view is created
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);
        });
        //Use a Lamda expression to add the OnClickListener
        mBinding.registerButton.setOnClickListener(button -> register());
    }

    // Remove the reference to binding when the Fragment View is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    // This will obtain the values from emailRegisterBox and pwdRegisterBox and will add the user.
    public void register() {
        String email = String.valueOf(mBinding.emailRegisterBox.getText());
        String pwd = String.valueOf(mBinding.pwdRegisterBox.getText());
        Log.i(TAG, email);
        mUserViewModel.addUser(email,pwd);
    }

    // Checks if we got a response, if the response we got is an error, and if there is
    // response and no error then the user is added to the database.
    private void observeResponse(final JSONObject response) {
        if (response.length() > 0) {
            if (response.has("error")) {
                try {
                    Toast.makeText(this.getContext(),
                            "Error Adding User: " +
                                    response.get("error"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            } else {
                Toast.makeText(this.getContext(),"User added", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d("JSON Response", "No Response");
        }
    }
}