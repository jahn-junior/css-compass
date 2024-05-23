/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.authentication.LoginActivity;
import edu.tacoma.uw.csscompass.databinding.FragmentProfileBinding;

/**
 * Profile tab fragment to display relevant user information.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class ProfileFragment extends Fragment {

    /** View-binding object. */
    private FragmentProfileBinding mBinding;

    /**
     * @inheritDoc
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Sets text fields to display the user information provided during account registration.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);

        //Binding the user data.
        mBinding.userEmail.setText(sharedPreferences.getString("email", "No email found"));
        mBinding.userFname.setText(sharedPreferences.getString("first_name", "No first name found"));
        mBinding.userLname.setText(sharedPreferences.getString("last_name", "No last name found"));
        mBinding.userStudentNum.setText(sharedPreferences.getString("student_number", "No student number found"));
        mBinding.userEnrollYear.setText(sharedPreferences.getString("enrollment_year", "No enrollment year found"));
        mBinding.userGradYear.setText(sharedPreferences.getString("graduation_year", "No graduation year found"));

        mBinding.logoutButton.setOnClickListener(v -> {
//            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false).commit();

            //No need to remove the preferences from the last user since when a user logs in again they will be simply overwritten.
            Intent i = new Intent(this.getActivity(), LoginActivity.class);
            startActivity(i);
        });
    }
}