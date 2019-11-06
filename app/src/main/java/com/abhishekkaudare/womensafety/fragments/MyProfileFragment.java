package com.abhishekkaudare.womensafety.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.abhishekkaudare.womensafety.activities.SplashActivity.Staticated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class MyProfileFragment extends Fragment {
    @Nullable
    private Activity myActivity;

    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashActivity.checkParameterIsNotNull(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        TextView temp0 = view != null ? (TextView)view.findViewById(R.id.box1) : null;
        if (temp0 == null) {
            throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
        } else {
            TextView box1 = temp0;
            temp0 = view.findViewById(R.id.box2);
            if (temp0 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
            } else {
                TextView box2 = temp0;
                temp0 = view.findViewById(R.id.box3);
                if (temp0 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                } else {
                    TextView box3 = temp0;
                    temp0 = view.findViewById(R.id.box4);
                    if (temp0 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                    } else {
                        TextView box4 = temp0;
                        temp0 = view.findViewById(R.id.box5);
                        if (temp0 == null) {
                            throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                        } else {
                            TextView box5 = temp0;
                            temp0 = view.findViewById(R.id.box6);
                            if (temp0 == null) {
                                throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                            } else {
                                TextView box6 = temp0;
                                temp0 = view.findViewById(R.id.box7);
                                if (temp0 == null) {
                                    throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                                } else {
                                    TextView box7 = temp0;
                                    temp0 = view.findViewById(R.id.box8);
                                    if (temp0 == null) {
                                        throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                                    } else {
                                        TextView box8 = temp0;
                                        temp0 = view.findViewById(R.id.box9);
                                        if (temp0 == null) {
                                            throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                                        } else {
                                            TextView box9 = temp0;
                                            temp0 = view.findViewById(R.id.box10);
                                            if (temp0 == null) {
                                                throw new ClassCastException("null cannot be cast to non-null type android.widget.TextView");
                                            } else {
                                                Activity temp1 = this.myActivity;
                                                if (temp1 == null) {
                                                    SplashActivity.throwJavaNpe();
                                                }

                                                SharedPreferences prefs = temp1.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
                                                box1.setText(prefs != null ? prefs.getString("name", "Failed to fetch Data") : null);

                                                assert prefs != null;
                                                int one = prefs.getInt("age", 0);
                                                box2.setText(String.valueOf(one));

                                                long two = prefs.getLong("phone", 0L);
                                                box3.setText(String.valueOf(two));

                                                box4.setText(prefs.getString("mail", "Failed to fetch Data"));

                                                long three = prefs.getLong("phone1", 0L);
                                                box5.setText(String.valueOf(three));

                                                box8.setText(prefs.getString("mail1", "Failed to fetch Data"));

                                                long four = prefs.getLong("phone2", 0L);
                                                box6.setText(String.valueOf(four));

                                                box9.setText(prefs.getString("mail2", "Failed to fetch Data"));

                                                long five = prefs.getLong("phone3", 0L);
                                                box7.setText(String.valueOf(five));

                                                temp0.setText(prefs.getString("mail3", "Failed to fetch Data"));

                                                FragmentActivity temp = this.getActivity();
                                                if (temp != null) {
                                                    temp.setTitle("My Profile");
                                                }

                                                return view;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void onAttach(@NotNull Context context) {
        SplashActivity.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        this.myActivity = (Activity)context;
    }

    public void onAttach(@NotNull Activity activity) {
        SplashActivity.checkParameterIsNotNull(activity, "activity");
        super.onAttach(activity);
        this.myActivity = activity;
    }




}
