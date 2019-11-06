package com.abhishekkaudare.womensafety.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class SafetyTipsFragment extends Fragment {

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashActivity.checkParameterIsNotNull(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_safety_tips, container, false);
        FragmentActivity temp = this.getActivity();
        if (temp != null) {
            temp.setTitle("Safety Tips");
        }

        return view;
    }


}
