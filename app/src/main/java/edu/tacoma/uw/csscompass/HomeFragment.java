/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.databinding.FragmentHomeBinding;

/**
 * Fragment for the home tab. Serves as a navigation hub to access other sprint 1 fragments.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class HomeFragment extends Fragment {

    /** View binding object. */
    private FragmentHomeBinding mBinding;

    /**
     * @inheritDoc
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //for the separated theme
        requireActivity().setTheme(R.style.Theme_CSSCompass);
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Binds fragment views to their appropriate navigation functions.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.resourceCard.setOnClickListener(v -> navigateToResources());
        mBinding.studySkillsCard.setOnClickListener(v -> navigateToStudySkills());
        mBinding.setProgramCard.setOnClickListener(v -> navigateToSetProgram());
        mBinding.aboutButton.setOnClickListener(v -> navigateToAbout());
    }

    /**
     * Navigation function for the resources card.
     */
    public void navigateToResources() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_resourceFragment);
    }

    /**
     * Navigation function for the study skills card.
     */
    public void navigateToStudySkills() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_studySkillsFragment);
    }

    /**
     * Navigation function for the SET program card.
     */
    public void navigateToSetProgram() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_setProgramFragment);
    }

    /**
     * Navigation function for the about button.
     */
    public void navigateToAbout() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_aboutFragment);
    }
}