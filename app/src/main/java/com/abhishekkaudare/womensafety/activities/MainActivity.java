package com.abhishekkaudare.womensafety.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.abhishekkaudare.womensafety.R.id;
import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.R.mipmap.*;
import com.abhishekkaudare.womensafety.activities.SplashActivity.Staticated;
import com.abhishekkaudare.womensafety.adapters.NavigationDrawerAdapter;
import com.abhishekkaudare.womensafety.fragments.MainScreenFragment;
import com.abhishekkaudare.womensafety.fragments.VideoRecordingFragment;
import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {
    @NotNull
    private String text = "";
    private int id;
    @NotNull
    private ArrayList navigationDrawerIconsList;
    @NotNull
    private int[] images_for_navdrawer;
    private HashMap _$_findViewCache;

    @NotNull
    public final String getText$app() {
        return this.text;
    }

    public final void setText$app(@NotNull String var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.text = var1;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int var1) {
        this.id = var1;
    }

    @NotNull
    public final ArrayList getNavigationDrawerIconsList() {
        return this.navigationDrawerIconsList;
    }

    public final void setNavigationDrawerIconsList(@NotNull ArrayList var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.navigationDrawerIconsList = var1;
    }

    @NotNull
    public final int[] getImages_for_navdrawer() {
        return this.images_for_navdrawer;
    }

    public final void setImages_for_navdrawer(@NotNull int[] var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.images_for_navdrawer = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        Statified.INSTANCE.setDrawerLayout((DrawerLayout)this.findViewById(R.id.drawer_layout));
        this.navigationDrawerIconsList.add("Your Profile");
        this.navigationDrawerIconsList.add("Settings");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, Statified.INSTANCE.getDrawerLayout(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.findViewById(R.id.logout_button).setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                SharedPreferences.Editor editor = MainActivity.this.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0).edit();
                if (editor != null) {
                    editor.putBoolean("loginstatus", false);
                }

                if (editor != null) {
                    editor.apply();
                }

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                MainActivity.this.sendBroadcast(broadcastIntent);
                Toast.makeText(MainActivity.this, "User Logged Out", Toast.LENGTH_LONG).show();
                (new Handler()).postDelayed(new Runnable() {
                    public final void run() {
                        Intent startAct = new Intent(MainActivity.this, LoginActivity.class);
                        MainActivity.this.startActivity(startAct);
                        MainActivity.this.finish();
                    }
                }, 1000L);
            }
        });
        DrawerLayout var10000 = Statified.INSTANCE.getDrawerLayout();
        if (var10000 != null) {
            var10000.addDrawerListener(toggle);
        }

        toggle.syncState();
        MainScreenFragment mainScreenFragment = new MainScreenFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.details_fragment, mainScreenFragment, "MainScreenFragment").commit();
        NavigationDrawerAdapter _navigationAdapter = new NavigationDrawerAdapter(this.navigationDrawerIconsList, this.images_for_navdrawer, this);
        _navigationAdapter.notifyDataSetChanged();
        RecyclerView navigation_recycler_view = this.findViewById(R.id.navigation_recycler_view);
        SplashActivity.checkExpressionValueIsNotNull(navigation_recycler_view, "navigation_recycler_view");
        navigation_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        navigation_recycler_view.setItemAnimator(new DefaultItemAnimator());
        navigation_recycler_view.setAdapter(_navigationAdapter);
        navigation_recycler_view.setHasFixedSize(true);
    }

    public final void videorecord(@NotNull View v) {
        SplashActivity.checkParameterIsNotNull(v, "v");
        VideoRecordingFragment videoRecordingFragment = new VideoRecordingFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment, videoRecordingFragment).addToBackStack("mainScreenFragement").commit();
    }

    public MainActivity() {
        boolean var1 = false;
        ArrayList var3 = new ArrayList();
        this.navigationDrawerIconsList = var3;
        this.images_for_navdrawer = new int[]{R.mipmap.profile, R.mipmap.settings, R.mipmap.safety};
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }


    public static final class Statified {
        @Nullable
        private static DrawerLayout drawerLayout;
        public static final MainActivity.Statified INSTANCE;

        @Nullable
        public final DrawerLayout getDrawerLayout() {
            return drawerLayout;
        }

        public final void setDrawerLayout(@Nullable DrawerLayout var1) {
            drawerLayout = var1;
        }

        private Statified() {
        }

        static {
            MainActivity.Statified var0 = new MainActivity.Statified();
            INSTANCE = var0;
        }
    }
}
