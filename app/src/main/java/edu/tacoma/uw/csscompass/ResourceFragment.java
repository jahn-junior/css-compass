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

import edu.tacoma.uw.csscompass.databinding.FragmentResourceBinding;
import edu.tacoma.uw.csscompass.databinding.FragmentSetProgramBinding;

public class ResourceFragment extends Fragment {

    private FragmentResourceBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentResourceBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.pantryLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/equity-center/pantry");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.dubnetLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/involvement/dubnet");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
        mBinding.parkingLink.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/fa/facilities/transportation");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }
}