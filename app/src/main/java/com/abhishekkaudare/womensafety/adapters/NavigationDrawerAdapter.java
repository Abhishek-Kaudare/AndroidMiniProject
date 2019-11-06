package com.abhishekkaudare.womensafety.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.abhishekkaudare.womensafety.activities.MainActivity;
import com.abhishekkaudare.womensafety.activities.MainActivity.Statified;
import com.abhishekkaudare.womensafety.fragments.MyProfileFragment;
import com.abhishekkaudare.womensafety.fragments.SafetyTipsFragment;
import com.abhishekkaudare.womensafety.fragments.SettingsFragment;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class NavigationDrawerAdapter extends Adapter {
    @Nullable
    private ArrayList contentList;
    @Nullable
    private int[] getImages;
    @Nullable
    private Context mContext;

    @Nullable
    public final ArrayList getContentList() {
        return this.contentList;
    }

    public final void setContentList(@Nullable ArrayList var1) {
        this.contentList = var1;
    }

    @Nullable
    public final int[] getGetImages() {
        return this.getImages;
    }

    public final void setGetImages(@Nullable int[] var1) {
        this.getImages = var1;
    }

    @Nullable
    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(@Nullable Context var1) {
        this.mContext = var1;
    }

    @NotNull
    public NavigationDrawerAdapter.NavViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        SplashActivity.checkParameterIsNotNull(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_custom_navigationdrawer, parent, false);
        SplashActivity.checkExpressionValueIsNotNull(itemView, "itemView");
        return new NavViewHolder(itemView);
    }

    // $FF: synthetic method
    // $FF: bridge method
//    public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
//        return (ViewHolder)this.onCreateViewHolder(var1, var2);
//    }

    public int getItemCount() {
        ArrayList var10000 = this.contentList;
        if (var10000 == null) {
            throw new ClassCastException("null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
        } else {
            return var10000.size();
        }
    }

    public void onBindViewHolder(@NotNull NavigationDrawerAdapter.NavViewHolder holder, final int position) {
        SplashActivity.checkParameterIsNotNull(holder, "holder");
        ImageView var10000 = holder.getIcon_GET();
        if (var10000 != null) {
            int[] var10001 = this.getImages;
            Integer var5 = var10001 != null ? var10001[position] : null;
            if (var5 == null) {
                throw new ClassCastException("null cannot be cast to non-null type kotlin.Int");
            }

            var10000.setBackgroundResource(var5);
        }

        TextView var3 = holder.getText_GET();
        if (var3 != null) {
            ArrayList var6 = this.contentList;
            var3.setText(var6 != null ? (String)var6.get(position) : null);
        }

        RelativeLayout var4 = holder.getContentHolder();
        if (var4 != null) {
            var4.setOnClickListener(new OnClickListener() {
                public final void onClick(View it) {
                    Context var10000;
                    if (position == 0) {
                        MyProfileFragment myProfileFragment = new MyProfileFragment();
                        var10000 = NavigationDrawerAdapter.this.getMContext();
                        if (var10000 == null) {
                            throw new ClassCastException("null cannot be cast to non-null type com.abhishekkaudare.womensafety.activities.MainActivity");
                        }

                        ((MainActivity)var10000).getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment, myProfileFragment).addToBackStack("mainScreenFragement").commit();
                    } else if (position == 1) {
                        SettingsFragment settingsFragment = new SettingsFragment();
                        var10000 = NavigationDrawerAdapter.this.getMContext();
                        if (var10000 == null) {
                            throw new ClassCastException("null cannot be cast to non-null type com.abhishekkaudare.womensafety.activities.MainActivity");
                        }

                        ((MainActivity)var10000).getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment, settingsFragment).addToBackStack("mainScreenFragement").commit();
                    } else if (position == 2) {
                        SafetyTipsFragment safetyTipsFragment = new SafetyTipsFragment();
                        var10000 = NavigationDrawerAdapter.this.getMContext();
                        if (var10000 == null) {
                            throw new ClassCastException("null cannot be cast to non-null type com.abhishekkaudare.womensafety.activities.MainActivity");
                        }

                        ((MainActivity)var10000).getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment, safetyTipsFragment).addToBackStack("mainScreenFragement").commit();
                    }

                    DrawerLayout var5 = Statified.INSTANCE.getDrawerLayout();
                    if (var5 != null) {
                        var5.closeDrawers();
                    }

                }
            });
        }

    }

    public void onBindViewHolder(ViewHolder var1, int var2) {
        this.onBindViewHolder((NavigationDrawerAdapter.NavViewHolder)var1, var2);
    }

    public NavigationDrawerAdapter(@NotNull ArrayList _contentList, @NotNull int[] _getImages, @NotNull Context _context) {
        super();
        SplashActivity.checkParameterIsNotNull(_contentList, "_contentList");
        SplashActivity.checkParameterIsNotNull(_getImages, "_getImages");
        SplashActivity.checkParameterIsNotNull(_context, "_context");

        this.contentList = _contentList;
        this.getImages = _getImages;
        this.mContext = _context;
    }


    public static final class NavViewHolder extends ViewHolder {
        @Nullable
        private ImageView icon_GET;
        @Nullable
        private TextView text_GET;
        @Nullable
        private RelativeLayout contentHolder;

        @Nullable
        public final ImageView getIcon_GET() {
            return this.icon_GET;
        }

        public final void setIcon_GET(@Nullable ImageView var1) {
            this.icon_GET = var1;
        }

        @Nullable
        public final TextView getText_GET() {
            return this.text_GET;
        }

        public final void setText_GET(@Nullable TextView var1) {
            this.text_GET = var1;
        }

        @Nullable
        public final RelativeLayout getContentHolder() {
            return this.contentHolder;
        }

        public final void setContentHolder(@Nullable RelativeLayout var1) {
            this.contentHolder = var1;
        }

        public NavViewHolder(@NotNull View itemView) {
            super(itemView);
            SplashActivity.checkParameterIsNotNull(itemView, "itemView");

            this.icon_GET = itemView.findViewById(R.id.icon_navdrawer);
            this.text_GET = itemView.findViewById(R.id.text_navdrawer);
            this.contentHolder = itemView.findViewById(R.id.navdrawer_item_content_holder);
        }
    }
}
