package edu.tacoma.uw.csscompass.event;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentEventDetailBinding;
import edu.tacoma.uw.csscompass.event.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {

    private FragmentEventDetailBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentEventDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get a reference to the SafeArgs object
        EventDetailFragmentArgs args = EventDetailFragmentArgs.fromBundle(getArguments());
        Event event = (Event) args.getEvent();
        mBinding.tittleTextView.setText(event.getTitle());
        String time = "";
        if(!(event.getTime().isEmpty())){
            time = ", " + event.getTime();
        }
        String dateAndTime = event.getDate() + time;
        mBinding.dateTextView.setText(dateAndTime);
        mBinding.descriptionTextView.setText(event.getDescription());
//        this.findViewById(R.id.learn_more_button)
        mBinding.learnMoreButton.setOnClickListener(v -> {
            Uri webpage = Uri.parse(event.getLink());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }

}