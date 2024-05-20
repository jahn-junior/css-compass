/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass.authentication;

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
import androidx.navigation.Navigation;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.csscompass.databinding.FragmentRegisterBinding;

/**
 * Description //FIXME
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RegisterFragment extends Fragment {

    /** Description //FIXME */
    private FragmentRegisterBinding mBinding;

    /** Description //FIXME */
    private RegisterViewModel mRegisterViewModel;

    /**
     * Description //FIXME
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
    // Binding is assigned to the View Bindings (converts the xml into objects that we can interact with programmatically).
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mRegisterViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Description //FIXME
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    // We add a listener to the register button once the view is created
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRegisterViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);
        });
        //Use a Lamda expression to add the OnClickListener
        mBinding.registerButton.setOnClickListener(button -> register());
    }

    /**
     * Description //FIXME
     */
    // Remove the reference to binding when the Fragment View is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    /**
     * Description //FIXME
     */
    // This will obtain the values from emailRegisterBox and pwdRegisterBox and will add the user.
    public void register() {
        String email = String.valueOf(mBinding.emailRegisterBox.getText());
        String pwd = String.valueOf(mBinding.pwdRegisterBox.getText());
        Account account;

        try {
            account = new Account(email, pwd);
        } catch(IllegalArgumentException ie) {
            Log.e(TAG, ie.getMessage());
            Toast.makeText(getContext(), ie.getMessage(), Toast.LENGTH_LONG).show();
            mBinding.textError.setText(ie.getMessage());
            return;
        }
        Log.i(TAG, email);
        mRegisterViewModel.addUser(account);
    }

    /**
     * Description //FIXME
     *
     * @param response
     */
    // Checks if we got a response, if the response we got is an error, and if there is
    // response and no error then the user is added to the database.
    private void observeResponse(final JSONObject response) {
        if (response.length() > 0) {
            if (response.has("error")) {
                try {
                    String errorMess = "Error Adding User: " + response.get("error");
                    Toast.makeText(this.getContext(), errorMess, Toast.LENGTH_LONG).show();
                    mBinding.textError.setText(errorMess);
                } catch (JSONException e) {
                    String errorMess = "Error Adding User: " + e.getMessage();
                    Log.e("JSON Parse Error", errorMess);
                    //Toast.makeText(this.getContext(), errorMess, Toast.LENGTH_LONG).show();
                    mBinding.textError.setText(errorMess);
                }
            } else {
                Toast.makeText(this.getContext(),"User added", Toast.LENGTH_LONG).show();
                Navigation.findNavController(getView()).popBackStack();
            }
        } else {
            Log.d("JSON Response", "No Response");
        }
    }
}