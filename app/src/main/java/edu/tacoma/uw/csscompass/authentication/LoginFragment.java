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
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FragmentLoginBinding mBinding;
    private LoginViewModel mLoginViewModel;

    private static final String TAG = "LoginFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        mBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

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

    public void navigateToRegister() {
        Navigation.findNavController(getView())
                .navigate(R.id.action_loginFragment_to_registerFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    public void login() {
        String email = String.valueOf(mBinding.emailEdit.getText());
        String pwd = String.valueOf(mBinding.pwdEdit.getText());
        Account account;
        try {
            account = new Account(email, pwd);
        } catch(IllegalArgumentException ie) {
            Log.e(TAG, ie.getMessage());
            Toast.makeText(getContext(), ie.getMessage(), Toast.LENGTH_LONG).show();
            mBinding.errorLoginTextview.setText(ie.getMessage());
            return;
        }
        Log.i(TAG, email);
        mLoginViewModel.authenticateUser(account);
    }

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