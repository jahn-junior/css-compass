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

import java.util.Objects;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentEventDetailBinding;
import edu.tacoma.uw.csscompass.event.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {

    private FragmentEventDetailBinding mBinding;

    private SaveEventViewModel mSaveEventViewModel;

    private Event mEvent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSaveEventViewModel = new ViewModelProvider(getActivity()).get(SaveEventViewModel.class);

        mBinding = FragmentEventDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get a reference to the SafeArgs object
        EventDetailFragmentArgs args = EventDetailFragmentArgs.fromBundle(getArguments());
        mEvent = (Event) args.getEvent();
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

        //For the save events
        mSaveEventViewModel.addResponseObserver(getViewLifecycleOwner(), response -> {
            observeResponse(response);

        });
        //Use a Lamda expression to add the OnClickListener
        mBinding.saveEventButton.setOnClickListener(button -> saveEvent());
    }

    public void saveEvent(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        String user_email = sharedPreferences.getString("email", "No email found");
        Event currEvent = mEvent;

        mSaveEventViewModel.saveEvent(user_email, currEvent);
    }

    private void observeResponse(final JSONObject response) {
        if (response.length() > 0) {
            if (response.has("error")) {
                try {
                    Toast.makeText(this.getContext(),
                            "Error Saving Event: " +
                                    response.get("error"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }

            } else {
                Toast.makeText(this.getContext(),"Event Saved", Toast.LENGTH_LONG).show();
            }

        } else {
            Log.d("JSON Response", "No Response");
        }

    }

}