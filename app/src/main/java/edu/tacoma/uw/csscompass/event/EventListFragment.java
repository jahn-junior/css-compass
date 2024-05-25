package edu.tacoma.uw.csscompass.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentEventListBinding;

/**
 * A simple {@link Fragment} subclass.
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class EventListFragment extends Fragment {

    /** The view model used in this fragment. */
    private EventViewModel mModel;

    /**
     * Initializes the view model in this fragment and retrieves the events to be displayed.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(EventViewModel.class);
        mModel.getEvents();
    }

    /**
     * Inflates the layout of the event list fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    /**
     * Obtains the views of the fragment event list binding, and if there are events in the
     * view model stored in this fragment then call then pass the list of events to the recycler.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        @NonNull FragmentEventListBinding binding = FragmentEventListBinding.bind(getView());

        mModel.addEventListObserver(getViewLifecycleOwner(), eventList -> {
            if (!eventList.isEmpty()) {
                binding.listOfFragmentEvents.setAdapter(
                        new EventRecyclerViewAdapter(eventList)
                );
            }
        });
    }

}