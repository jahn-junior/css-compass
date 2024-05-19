package edu.tacoma.uw.csscompass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.databinding.FragmentStudySkillsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudySkillsFragment extends Fragment {

    private FragmentStudySkillsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStudySkillsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.tlcLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/tlc/hours-schedules");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.leetcodeLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://leetcode.com/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }
}