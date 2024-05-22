package edu.tacoma.uw.csscompass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tacoma.uw.csscompass.databinding.FragmentCourseBinding;

public class ClassesRecyclerViewAdapter extends
        RecyclerView.Adapter<ClassesRecyclerViewAdapter.ViewHolder>{

    private final List<Classes> mValues;

    public ClassesRecyclerViewAdapter(List<Classes> classesList) {
        mValues = classesList;
    }

    @NonNull
    @Override
    public ClassesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false ));

    }

    @Override
    public void onBindViewHolder(@NonNull ClassesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setItem(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentCourseBinding binding;
        public Classes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentCourseBinding.bind(view);
        }

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