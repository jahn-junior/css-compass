package edu.tacoma.uw.csscompass.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentSavedEventListBinding;

/**
 * A simple {@link Fragment} subclass.
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class SavedEventListFragment extends Fragment {

    /** The view model used to retrieve events saved by the user. */
    private RetrieveEventsViewModel mModel;

    /**
     * initializes the view model in this fragment and retrieves the events saved by the user
     * using the user email stored in the shared preferences.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(RetrieveEventsViewModel.class);
        //Get the current user
        SharedPreferences sP = this.getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        String userEmail = sP.getString("email", "No email found");
        mModel.getSavedEvents(userEmail);
    }

    /**
     * Returns the inflated view of the saved events list fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the inflated view of the saved event list fragment as a View.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_event_list, container, false);
    }

    /**
     * Gets the view from the bind of fragment saved event list, and if the user has
     * saved events stored then we set an adapter using the list of the user event that were saved.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @NonNull FragmentSavedEventListBinding binding = FragmentSavedEventListBinding.bind(getView());
        mModel.addEventListObserver(getViewLifecycleOwner(), eventList -> {
            if (!eventList.isEmpty()) {
                binding.listOfSavedFragmentEvents.setAdapter(
                        new RetrieveEventsRecyclerViewAdapter(eventList)
                );
            }
        });
    }
}