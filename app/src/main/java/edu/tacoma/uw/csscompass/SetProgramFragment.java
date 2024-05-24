/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

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

import edu.tacoma.uw.csscompass.databinding.FragmentSetProgramBinding;

/**
 * Fragment for the SET Program page.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class SetProgramFragment extends Fragment {

    /** View binding object. */
    private FragmentSetProgramBinding mBinding;

    /**
     * @inheritDoc
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //for the separated theme
        requireActivity().setTheme(R.style.Theme_CSSCompass);

        mBinding = FragmentSetProgramBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Binds floating action buttons in layout to the booking links for each advisor.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.bookNoelle.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://apply.tacoma.uw.edu/portal/webinar");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.bookFrancis.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/SETGraduateAdvisingFrancisNwagbara@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.bookBrandy.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingBrandyFeatherstone@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.bookChristian.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingChristianJames@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.bookBeth.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingBethJeffrey@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

    }
}