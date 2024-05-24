package edu.tacoma.uw.csscompass.event;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentEventDetailBinding;
import edu.tacoma.uw.csscompass.databinding.FragmentRetrievedEventDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrievedEventDetailFragment extends Fragment {

    private FragmentRetrievedEventDetailBinding mBinding;

    private RetrieveEventsViewModel mRetrieveEventsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRetrieveEventsViewModel = new ViewModelProvider(getActivity()).get(RetrieveEventsViewModel.class);

        mBinding = FragmentRetrievedEventDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get a reference to the SafeArgs object
        EventDetailFragmentArgs args = EventDetailFragmentArgs.fromBundle(getArguments());
        Event mEvent = (Event) args.getEvent();
            mBinding.titleTextView.setText(mEvent.getTitle());
            String time = "";
            if(!(mEvent.getTime().isEmpty())){
                time = ", " + mEvent.getTime();
            }
            String dateAndTime = mEvent.getDate() + time;
            mBinding.dateTextView.setText(dateAndTime);
            mBinding.descriptionTextView.setText(mEvent.getDescription());
            mBinding.eventLink.setOnClickListener(v -> {
                Uri webpage = Uri.parse(mEvent.getLink());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            });

            mBinding.shareButton.setOnClickListener(v -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this upcoming event!\n" + mEvent.getLink());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}