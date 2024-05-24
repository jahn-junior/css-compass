/*
 * TCSS 450 - Mobile Application Development
 * Programming Project Sprint 2
 */

package edu.tacoma.uw.csscompass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import edu.tacoma.uw.csscompass.databinding.FragmentCourseListBinding;

/**
 * Fragment for the course list page.
 *
 * @author JJ Coldiron
 * @author Danie Oum
 * @author Derek Ruiz-Garcia
 * @version 1.0
 */
public class CourseListFragment extends Fragment {

    private ClassesViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(ClassesViewModel.class);
        mModel.getClasses();
    }

    /**
     * @inheritDoc
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        @NonNull FragmentCourseListBinding binding = FragmentCourseListBinding.bind(getView());

        mModel.addAnimalListObserver(getViewLifecycleOwner(), animalList -> {
            if (!animalList.isEmpty()) {
                binding.layoutRoot.setAdapter(
                        new ClassesRecyclerViewAdapter(animalList)
                );
            }
        });
    }
}