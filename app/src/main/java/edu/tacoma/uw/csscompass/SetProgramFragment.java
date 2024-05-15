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

import edu.tacoma.uw.csscompass.databinding.FragmentHomeBinding;
import edu.tacoma.uw.csscompass.databinding.FragmentSetProgramBinding;

public class SetProgramFragment extends Fragment {

    private FragmentSetProgramBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSetProgramBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.bookNoelle.setOnClickListener(card -> {
            Uri webpage = Uri.parse("https://apply.tacoma.uw.edu/portal/webinar");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        mBinding.bookFrancis.setOnClickListener(card -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/SETGraduateAdvisingFrancisNwagbara@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        mBinding.bookBrandy.setOnClickListener(card -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingBrandyFeatherstone@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        mBinding.bookChristian.setOnClickListener(card -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingChristianJames@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        mBinding.bookBeth.setOnClickListener(card -> {
            Uri webpage = Uri.parse("https://outlook.office365.com/book/UWTSETAdvisingBethJeffrey@cloud.washington.edu/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }


}