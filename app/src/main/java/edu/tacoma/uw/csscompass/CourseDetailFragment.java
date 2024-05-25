/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

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
 * Fragment for the course list details.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class CourseDetailFragment extends Fragment {

    /** The fragment course detail binding to be used in this fragment to bind data
     * to the view elements.
     */
    private FragmentCourseDetailBinding mBinding;

    /**
     * Initializes the binding using the data using the passed inflater and container and returns
     * the inflated fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the inflated fragment view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCourseDetailBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    /**
     * Using the arguments passed, it sets the elements of the inflated view to the class id,
     * course and class title stored inside the class.
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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