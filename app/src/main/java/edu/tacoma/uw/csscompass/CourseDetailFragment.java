package edu.tacoma.uw.csscompass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import edu.tacoma.uw.csscompass.databinding.FragmentCourseDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseDetailFragment extends Fragment {

    private FragmentCourseDetailBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCourseDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get a reference to the SafeArgs object
        CourseDetailFragmentArgs args = CourseDetailFragmentArgs.fromBundle(getArguments());
        Classes classes = (Classes) args.getClasses();
        mBinding.idTextView.setText(Integer.toString(classes.getId()));
        mBinding.courseTextView.setText(classes.getCourse());
        mBinding.titleTextView.setText(classes.getTitle());
    }
}