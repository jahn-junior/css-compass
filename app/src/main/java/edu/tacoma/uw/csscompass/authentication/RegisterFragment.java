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
 * The register fragment used to register the user into the app.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RegisterFragment extends Fragment {

    /** The binding class used to inflate the layout of the fragment. */
    private FragmentRegisterBinding mBinding;

    /** The view model used by the register class and fragment */
    private RegisterViewModel mRegisterViewModel;

    /**
     * This overrides the onCreateView from the fragment class, initializes the ViewModel
     * associated with the fragment's parent activity, inflates the layout defined in the XML
     * file using data binding, and returns the root View of the inflated layout.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the View of the inflated layout.
     */
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mRegisterViewModel = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * This adds a response to the Register View Model, and binds an action listener to
     * the register button to register the user once it is clicked once the View is created.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRegisterViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);
        });
        //Use a Lamda expression to add the OnClickListener
        mBinding.registerButton.setOnClickListener(button -> register());
    }

    /** Removes the reference to binding when the Fragment View is destroyed */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    /**
     * This obtains the values from all the text boxes in the register fragment, and adds
     * it to the user profile.
     */
    public void register() {
        String email = String.valueOf(mBinding.emailRegisterBox.getText());
        String pwd = String.valueOf(mBinding.pwdRegisterBox.getText());
        String firstName = String.valueOf(mBinding.fnameRegisterBox.getText());
        String lastName = String.valueOf(mBinding.lnameRegisterBox.getText());
        String studentNumber = String.valueOf(mBinding.studentNumRegisterBox.getText());
        String enrollmentYear = String.valueOf(mBinding.enrollYearRegisterBox.getText());
        String graduationYear = String.valueOf(mBinding.gradYearRegisterBox.getText());

        Account account;

        try {
            account = new Account(email, pwd, firstName, lastName, studentNumber, enrollmentYear, graduationYear);
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
     * This is the response that gets called when the User attepmts to register, meaning it
     * catches whether the user was able to register successfully or not, showing a toast
     * with the type of response (fail or success).
     *
     * @param response the response as a JSONObject obtained from the user attempt to
     *                 register to the app, providing errors or success messages
     *                 depending on the value stored inside of the response.
     */
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