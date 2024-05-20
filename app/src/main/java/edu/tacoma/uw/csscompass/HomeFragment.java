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
 * Description //FIXME
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //for the separated theme
        requireActivity().setTheme(R.style.Theme_CSSCompass);
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.resourceCard.setOnClickListener(card -> navigateToResources());
        mBinding.studySkillsCard.setOnClickListener(card -> navigateToStudySkills());
        mBinding.setProgramCard.setOnClickListener(card -> navigateToSetProgram());
    }

    public void navigateToResources() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_resourceFragment);
    }

    public void navigateToStudySkills() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_studySkillsFragment);
    }

    public void navigateToSetProgram() {
        Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_setProgramFragment);
    }
}