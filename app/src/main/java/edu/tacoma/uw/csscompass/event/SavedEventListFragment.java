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
 */
public class SavedEventListFragment extends Fragment {

    private RetrieveEventsViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(RetrieveEventsViewModel.class);

        //Get the current user
        SharedPreferences sP = this.getActivity().getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        String userEmail = sP.getString("email", "No email found");
        mModel.getSavedEvents(userEmail);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_event_list, container, false);
    }

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