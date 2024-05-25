/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.csscompass.MainActivity;
import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentLoginBinding;

/**
 * The login fragment used to log in a user into the app.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class LoginFragment extends Fragment {

    /** The binding used to bind the login fragment */
    private FragmentLoginBinding mBinding;

    /** The view model used for the login */
    private LoginViewModel mLoginViewModel;

    /** The login fragment tag for debugging */
    private static final String TAG = "LoginFragment";

    /**
     * Initializes the view model for login in, then assigns the binding to the inflated
     * fragment using the passed inflater and container, and then returns the inflated view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Adds an observer to the login view model, and binds the login button and register text view
     * to on click listeners to login and go to register fragment respectively.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLoginViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);

        });
        //Use a Lambda expression to add the OnClickListener
        mBinding.loginButton.setOnClickListener(button -> login());
        mBinding.registerTextview.setOnClickListener(button -> navigateToRegister());
    }

    /**
     * This allows us to navigate from the login fragment to the register fragment using
     * the current view as a nav controller.
     */
    public void navigateToRegister() {
        Navigation.findNavController(getView())
                .navigate(R.id.action_loginFragment_to_registerFragment);
    }

    /**
     * Destroys the binding view, by setting it to null.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    /**
     * This logs the user into the app, verifying if the passed values are valid, and then
     * checking if the account exists in the database.
     */
    public void login() {
        String email = String.valueOf(mBinding.emailEdit.getText());
        String pwd = String.valueOf(mBinding.pwdEdit.getText());
        Account account;
        try {
            account = new Account(email, pwd, "noName", "noName",
                    "1234567", "2020", "2024");
        } catch(IllegalArgumentException ie) {
            Log.e(TAG, ie.getMessage());
            Toast.makeText(getContext(), ie.getMessage(), Toast.LENGTH_LONG).show();
            mBinding.errorLoginTextview.setText(ie.getMessage());
            return;
        }
        Log.i(TAG, email);
        mLoginViewModel.authenticateUser(account);
    }

    /**
     * This reacts to the response obtained from trying to login a user into the app. Saving the
     * user information of the user that logged in, and starting the main activity using an intent.
     *
     * @param response the response obtained from trying to log in the user into the
     *                 app as a JSONObject.
     */
    private void observeResponse(final JSONObject response) {
        if (response.length() > 0) {
            if (response.has("error")) {
                try {
                    Toast.makeText(this.getContext(),
                            "Error Authenticating User: " +
                                    response.get("error"), Toast.LENGTH_LONG).show();
                    mBinding.errorLoginTextview.setText("User failed to authenticate");

                } catch (JSONException e) {
                    Log.e("JSON Parse Error", "JSON Parse Error " + e.getMessage());
                    mBinding.errorLoginTextview.setText("User failed to authenticate");
                }

            } else if (response.has("result")) {
                try {
                    String result = (String) response.get("result");
                    if (result.equals("success")) {
                        Toast.makeText(this.getContext(), "User logged in", Toast.LENGTH_LONG).show();
                        // Set the preferences and set logged in to true
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS)
                                , Context.MODE_PRIVATE);
                        sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), true)
                                .commit();
                        //Go to the mainActivity after logging in.
                        Intent intent = new Intent(getContext(), MainActivity.class); //Create an intent to go to the mainActivity

                        //Save the user data in shared preferences.
                        sharedPreferences.edit().putString("email", (String) response.get("email"))
                                .commit();
                        sharedPreferences.edit().putString("first_name", (String) response.get("first_name"))
                                .commit();
                        sharedPreferences.edit().putString("last_name", (String) response.get("last_name"))
                                .commit();
                        sharedPreferences.edit().putString("student_number", (String) response.get("student_number"))
                                .commit();
                        sharedPreferences.edit().putString("enrollment_year", (String) response.get("enrollment_year"))
                                .commit();
                        sharedPreferences.edit().putString("graduation_year", (String) response.get("graduation_year"))
                                .commit();

                        startActivity(intent);                                        //Start the activity
                        requireActivity().finish();                                   //Remove the old activity linked to this fragment
                    } else {
                        Toast.makeText(this.getContext(), "User failed to authenticate", Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            }

        } else {
            Log.d("JSON Response", "No Response");
        }

    }
}
