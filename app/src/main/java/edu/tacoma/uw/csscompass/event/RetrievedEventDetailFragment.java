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
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RetrievedEventDetailFragment extends Fragment {

    /** The binding used in this fragment. */
    private FragmentRetrievedEventDetailBinding mBinding;

    /** The view model used to retrieve the events saved by the user. */
    private RetrieveEventsViewModel mRetrieveEventsViewModel;

    /**
     * Initializes the view model and the binding stored in this database and returns
     * the root of the binding stored in this fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the root view of the inflated layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRetrieveEventsViewModel = new ViewModelProvider(getActivity()).get(RetrieveEventsViewModel.class);

        mBinding = FragmentRetrievedEventDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Binds the values inside of the passed arguments to the elements in the the view,
     * and adds a share button listener including the link of the saved event.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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

    /** Removes the reference to binding when the Fragment View is destroyed */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}