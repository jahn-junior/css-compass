/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tacoma.uw.csscompass.databinding.FragmentCourseBinding;

/**
 * Recycler view for the course list.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class ClassesRecyclerViewAdapter extends
        RecyclerView.Adapter<ClassesRecyclerViewAdapter.ViewHolder>{

    /** The list of classes. */
    private final List<Classes> mValues;

    /**
     * Sets the list of classes stored in this view adapter to the passed list of classes object.
     * @param classesList a list of classes objects to be assigned to the list in this
     *                    recycler view adapter.
     */
    public ClassesRecyclerViewAdapter(List<Classes> classesList) {
        mValues = classesList;
    }

    /**
     * Inflates the fragment course and then creates a view holder with it.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a new view holder containing the inflated fragment course.
     */
    @NonNull
    @Override
    public ClassesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false ));

    }

    /**
     * Sets the item inside the holder using the class at the passed position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ClassesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    /**
     * The size of the list containing the courses.
     * @return the size of the list containing the courses as an int.
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /** A view holder class. */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /** The view that will be used in this view holder. */
        public final View mView;

        /** The binding to be used in this view holder. */
        public FragmentCourseBinding binding;

        /** The item that will be used in this view holder. */
        public Classes mItem;

        /**
         * Initializes the view  and the binding using the passed view.
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentCourseBinding.bind(view);
        }

        /**
         * Sets the items in the course fragment list to the values that are contained
         * inside of the passed item, adding a listener to the course title that will
         * lead the detail fragment that will include more details about the fragment using
         * the passed class argument.
         * @param item the class from which we will get the data to set to the view items.
         */
        public void setItem(final Classes item) {
            mItem = item;
            binding.courseId.setText(Integer.valueOf(item.getId()).toString());
            binding.courseTitle.setText(item.getTitle());
            binding.courseTitle.setOnClickListener(view -> {
                CourseListFragmentDirections.ActionNavigationCourseListToCourseDetailFragment directions =
                        CourseListFragmentDirections.actionNavigationCourseListToCourseDetailFragment(item);

                Navigation.findNavController(mView).navigate(directions);
            });
        }
    }
}