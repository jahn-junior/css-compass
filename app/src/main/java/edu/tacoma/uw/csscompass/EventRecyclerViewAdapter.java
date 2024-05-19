package edu.tacoma.uw.csscompass;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tacoma.uw.csscompass.databinding.FragmentEventBinding;

public class EventRecyclerViewAdapter  extends
            RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;

    public EventRecyclerViewAdapter(List<Event> eventList) {
        mValues = eventList;
    }

    @NonNull
    @Override
    public EventRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false ));

    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerViewAdapter.ViewHolder holder, int position) {
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

//        public void setItem(final Event item) {
//            mItem = item;
////            Log.v(TAG, "ready to start binding the info to the items.");
//            binding.eventDate.setText(item.getTime());
////            Log.v(TAG, "done binding the date");
//            binding.eventTitle.setText(item.getTitle());
////            Log.v(TAG, "done binding the title");
//        }
        public void setItem(final Event item) {
            mItem = item;
            binding.eventTime.setText(mItem.getTime());
            binding.eventTitle.setText(mItem.getTitle());
            binding.eventTitle.setOnClickListener(view -> {
                EventListFragmentDirections.ActionEventListFragmentToEventDetailFragment directions =
                        EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(item);

                Navigation.findNavController(mView).navigate(directions);
            });

        }

    }

}
