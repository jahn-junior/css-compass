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

/**
 * A recycler view adapter class.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class RetrieveEventsRecyclerViewAdapter  extends
        RecyclerView.Adapter<RetrieveEventsRecyclerViewAdapter.ViewHolder> {

    /** The list of user saved events. */
    private final List<Event> mValues;

    /**
     * Sets the list of events stored in this recycler view adapter to the passed list of events.
     * @param eventList the list of event objects to assign to the list stored in
     *                  this recycler view adapter.
     */
    public RetrieveEventsRecyclerViewAdapter(List<Event> eventList) {
        mValues = eventList;
    }

    /**
     * Inflates the fragment event and returns a view holder containing that inflated layout.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a new View Holder containing the inflated layout.
     */
    @NonNull
    @Override
    public RetrieveEventsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false ));

    }

    /**
     * Sets the item in the holder the the position of the list of saved events.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RetrieveEventsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    /**
     * Returns the size of the save event list.
     * @return the size of the list containing the saved events.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /** A view holder class. */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /** The view that will be used in this view holder. */
        public final View mView;

        /** The binding that will be used in this view holder. */
        public FragmentEventBinding binding;

        /** The event that will be used in this view holder. */
        public Event mItem;

        /**
         * Initializes the view and the binding of this view holder using the passed view.
         * @param view the view that will be used to initialize the view and the binding stored
         *             in this view holder.
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentEventBinding.bind(view);
        }

        /**
         * Sets the elements in the list of user saved events using the passed event date and
         * title and a listener is attached to the view leading to the detail fragment
         * containing more information about the saved event.
         * @param item the event as an Event object to be used to fill the information of
         *             an item in the recycler view.
         */
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
