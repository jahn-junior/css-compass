package edu.tacoma.uw.csscompass.event;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tacoma.uw.csscompass.R;
import edu.tacoma.uw.csscompass.databinding.FragmentEventBinding;

public class RetrieveEventsRecyclerViewAdapter  extends
        RecyclerView.Adapter<RetrieveEventsRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;

    public RetrieveEventsRecyclerViewAdapter(List<Event> eventList) {
        mValues = eventList;
    }

    @NonNull
    @Override
    public RetrieveEventsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false ));

    }

    @Override
    public void onBindViewHolder(@NonNull RetrieveEventsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentEventBinding binding;
        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentEventBinding.bind(view);
        }

        public void setItem(final Event item) {
            mItem = item;
            binding.eventDate.setText(mItem.getDate());
            binding.eventTitle.setText(mItem.getTitle());
            if(!(mItem.getTitle().equals("Event"))){
                binding.eventTitle.setOnClickListener(view -> {
                    SavedEventListFragmentDirections.ActionSavedEventListFragmentToRetrievedEventDetailFragment directions =
                            SavedEventListFragmentDirections.actionSavedEventListFragmentToRetrievedEventDetailFragment(item);

                    Navigation.findNavController(mView).navigate(directions);
                });
            }
        }
    }
}
