package com.ualberta.rallyup.progressBar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ualberta.rallyup.R;

public class ManageMilestoneDialog extends DialogFragment {
    // Simple dialog that will have 4 buttons,
    // each will take the user to another dialog/activity, whichever may be
    // that will allow them to edit their milestones
    // We will only have a close button at the bottom


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_managemilestones, container, false);
        // Sets the background to transparent so our layout background can be applicable and
        // not have awkward white square corners poking out of the rounded corners
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RadioButton manageMilestoneOne = view.findViewById(R.id.ManageMilestonesRadioButtonOne);
        RadioButton manageMilestoneTwo = view.findViewById(R.id.ManageMilestonesRadioButtonTwo);
        RadioButton manageMilestoneThree = view.findViewById(R.id.ManageMilestonesRadioButtonThree);
        RadioButton manageMilestoneFour = view.findViewById(R.id.ManageMilestonesRadioButtonFour);
        Button closeButton = view.findViewById(R.id.ManageMilestonesCloseButton);

        manageMilestoneOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something something happening here for your new activity
            }
        });

        manageMilestoneTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something something happening here for your new activity
            }
        });

        manageMilestoneThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something something happening here for your new activity
            }
        });

        manageMilestoneFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something something happening here for your new activity
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
