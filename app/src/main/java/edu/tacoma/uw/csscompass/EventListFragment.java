package edu.tacoma.uw.csscompass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tacoma.uw.csscompass.databinding.FragmentEventListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    private EventViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(EventViewModel.class);
        mModel.getEvents();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

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