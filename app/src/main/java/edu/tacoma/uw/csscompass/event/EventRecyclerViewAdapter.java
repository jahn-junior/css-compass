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
 * An event recycler view adapter used to display all the UW events.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class EventRecyclerViewAdapter  extends
            RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    /** The list of events. as Event objects. */
    private final List<Event> mValues;

    /**
     * Initializes the list of events to the passed list of events.
     * @param eventList the list of events to assign to the list of events stored in
     *                  this recycler view adapter.
     */
    public EventRecyclerViewAdapter(List<Event> eventList) {
        mValues = eventList;
    }

    /**
     * Inflates the fragment event that will be hold by this recycler view adapter.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a newe ViewHolder with the inflated fragment event.
     */
    @NonNull
    @Override
    public EventRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false ));

    }

    /**
     * Sets the items in the holder to the passed position of the values of the event list.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull EventRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    /**
     * The size of the event list.
     * @return the size of the event list as an integer.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /** A view holder class that will be used by the recycler. */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /** The view used by this view holder. */
        public final View mView;

        /** The binding used by this view holder. */
        public FragmentEventBinding binding;

        /** The event that will be used in this view holder. */
        public Event mItem;

        /**
         * Initializes parent, the view and the binding of this view holder.
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentEventBinding.bind(view);
        }

        /**
         * Sets up the data in the view holder to the data stored in the event.
         * Also attatching a listener to guide them to the event detail fragment.
         *
         * @param item the event as an Event that contains all the data to bind to the view
         *             elements.
         */
        public void setItem(final Event item) {
            mItem = item;
            binding.eventDate.setText(mItem.getDate());
            binding.eventTitle.setText(mItem.getTitle());
            if(!(mItem.getTitle().equals("Event"))){
                binding.eventTitle.setOnClickListener(view -> {
                    EventListFragmentDirections.ActionNavigationEventsToEventDetailFragment directions =
                            EventListFragmentDirections.actionNavigationEventsToEventDetailFragment(item);

                    Navigation.findNavController(mView).navigate(directions);
                });
            }
        }
    }
}
