package com.abhishekkaudare.womensafety.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.fragment.app.Fragment;
import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.helpers.CameraUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class VideoRecordingFragment extends Fragment {
    private final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    @NotNull
    private final String KEY_IMAGE_STORAGE_PATH = "image_path";
    private final int MEDIA_TYPE_IMAGE = 1;
    private final int MEDIA_TYPE_VIDEO = 2;
    private final int BITMAP_SAMPLE_SIZE = 8;
    @NotNull
    private final String GALLERY_DIRECTORY_NAME = "Record Video";
    @NotNull
    private final String IMAGE_EXTENSION = "jpg";
    @NotNull
    private final String VIDEO_EXTENSION = "mp4";
    private String imageStoragePath;
    private TextView txtDescription;
    private ImageView imgPreview;
    private VideoView videoPreview;
    private ImageButton btnCapturePicture;
    private ImageButton btnRecordVideo;
    private ImageView btnShare;
    @Nullable
    private Activity myActivity;
    private HashMap _$_findViewCache;

    @NotNull
    public final String getKEY_IMAGE_STORAGE_PATH() {
        return this.KEY_IMAGE_STORAGE_PATH;
    }

    public final int getMEDIA_TYPE_IMAGE() {
        return this.MEDIA_TYPE_IMAGE;
    }

    public final int getMEDIA_TYPE_VIDEO() {
        return this.MEDIA_TYPE_VIDEO;
    }

    public final int getBITMAP_SAMPLE_SIZE() {
        return this.BITMAP_SAMPLE_SIZE;
    }

    @NotNull
    public final String getGALLERY_DIRECTORY_NAME() {
        return this.GALLERY_DIRECTORY_NAME;
    }

    @NotNull
    public final String getIMAGE_EXTENSION() {
        return this.IMAGE_EXTENSION;
    }

    @NotNull
    public final String getVIDEO_EXTENSION() {
        return this.VIDEO_EXTENSION;
    }

    @Nullable
    public final Activity getMyActivity() {
        return this.myActivity;
    }

    public final void setMyActivity(@Nullable Activity var1) {
        this.myActivity = var1;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashActivity.checkParameterIsNotNull(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_video_recording, container, false);
        CameraUtils var10000 = CameraUtils.INSTANCE;
        Activity var10001 = this.myActivity;
        if (var10001 == null) {
            SplashActivity.throwJavaNpe();
        }

        if (!var10000.isDeviceSupportCamera((Context)var10001)) {
            Toast.makeText((Context)this.myActivity, (CharSequence)"Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
        }

        this.txtDescription = view != null ? (TextView)view.findViewById(R.id.txt_desc) : null;
        this.imgPreview = view != null ? (ImageView)view.findViewById(R.id.imgPreview) : null;
        this.videoPreview = view != null ? (VideoView)view.findViewById(R.id.videoPreview) : null;
        this.btnCapturePicture = view != null ? (ImageButton)view.findViewById(R.id.btnCapturePicture) : null;
        this.btnRecordVideo = view != null ? (ImageButton)view.findViewById(R.id.btnRecordVideo) : null;
        this.btnShare = (ImageView)(view != null ? (ImageButton)view.findViewById(R.id.sharevideo) : null);
        ImageButton var5 = this.btnCapturePicture;
        if (var5 == null) {
            SplashActivity.throwJavaNpe();
        }

        var5.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                VideoRecordingFragment.this.captureImage();
            }
        }));
        var5 = this.btnRecordVideo;
        if (var5 == null) {
            SplashActivity.throwJavaNpe();
        }

        var5.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                VideoRecordingFragment.this.captureVideo();
            }
        }));
        ImageView var6 = this.btnShare;
        if (var6 == null) {
            SplashActivity.throwJavaNpe();
        }

        var6.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                Intent videoshareintent = new Intent("android.intent.action.SEND");
                videoshareintent.setType("video/mp4");
                videoshareintent.putExtra("android.intent.extra.STREAM", (Parcelable)Uri.fromFile(new File(VideoRecordingFragment.this.imageStoragePath)));
                Log.e("Video Share", "" + videoshareintent);
                VideoRecordingFragment.this.startActivity(videoshareintent);
            }
        }));
        this.restoreFromBundle(savedInstanceState);
        return view;
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

    private final void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(this.KEY_IMAGE_STORAGE_PATH)) {
            this.imageStoragePath = savedInstanceState.getString(this.KEY_IMAGE_STORAGE_PATH);
            if (!TextUtils.isEmpty(this.imageStoragePath)) {

                String var10000 = this.imageStoragePath;
                if (var10000 == null) {
                    SplashActivity.throwJavaNpe();
                }

                String var2 = var10000;
                var10000 = this.imageStoragePath;
                if (var10000 == null) {
                    SplashActivity.throwJavaNpe();
                }

                int var3 = this.imageStoragePath.lastIndexOf(".");
                if (var2 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type java.lang.String");
                }

                var10000 = var2.substring(var3);
                SplashActivity.checkExpressionValueIsNotNull(var10000, "(this as java.lang.String).substring(startIndex)");
                if (SplashActivity.areEqual(var10000, '.' + this.IMAGE_EXTENSION)) {
                    this.previewCapturedImage();
                } else {
                    var10000 = this.imageStoragePath;
                    if (var10000 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var2 = var10000;
                    var10000 = this.imageStoragePath;
                    if (var10000 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var3 = var10000.lastIndexOf(".");
                    if (var2 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type java.lang.String");
                    }

                    var10000 = var2.substring(var3);
                    SplashActivity.checkExpressionValueIsNotNull(var10000, "(this as java.lang.String).substring(startIndex)");
                    if (SplashActivity.areEqual(var10000, '.' + this.VIDEO_EXTENSION)) {
                        this.previewVideo();
                    }
                }
            }
        }

    }

    private final void requestCameraPermission(final int type) {
        Dexter.withActivity(this.myActivity).withPermissions(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}).withListener((MultiplePermissionsListener)(new MultiplePermissionsListener() {
            public void onPermissionsChecked(@NotNull MultiplePermissionsReport report) {
                SplashActivity.checkParameterIsNotNull(report, "report");
                if (report.areAllPermissionsGranted()) {
                    if (type == VideoRecordingFragment.this.getMEDIA_TYPE_IMAGE()) {
                        VideoRecordingFragment.this.captureImage();
                    } else {
                        VideoRecordingFragment.this.captureVideo();
                    }
                } else if (report.isAnyPermissionPermanentlyDenied()) {
                    VideoRecordingFragment.this.showPermissionsAlert();
                }

            }

            public void onPermissionRationaleShouldBeShown(@NotNull List permissions, @NotNull PermissionToken token) {
                SplashActivity.checkParameterIsNotNull(permissions, "permissions");
                SplashActivity.checkParameterIsNotNull(token, "token");
                token.continuePermissionRequest();
            }
        })).check();
    }

    private final void captureImage() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = CameraUtils.INSTANCE.getOutputMediaFile(this.MEDIA_TYPE_IMAGE);
        if (file != null) {
            this.imageStoragePath = file.getAbsolutePath();
        }

        CameraUtils var10000 = CameraUtils.INSTANCE;
        Activity var10001 = this.myActivity;
        if (var10001 == null) {
            SplashActivity.throwJavaNpe();
        }

        Context var4 = (Context)var10001;
        if (file == null) {
            SplashActivity.throwJavaNpe();
        }

        Uri fileUri = var10000.getOutputMediaFileUri(var4, file);
        intent.putExtra("output", (Parcelable)fileUri);
        this.startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public void onSaveInstanceState(@NotNull Bundle outState) {
        SplashActivity.checkParameterIsNotNull(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString(this.KEY_IMAGE_STORAGE_PATH, this.imageStoragePath);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.imageStoragePath = savedInstanceState.getString(this.KEY_IMAGE_STORAGE_PATH);
            Log.e("Image Path", "" + this.imageStoragePath);
        }

    }

    private final void captureVideo() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        File file = CameraUtils.INSTANCE.getOutputMediaFile(this.MEDIA_TYPE_VIDEO);
        if (file != null) {
            this.imageStoragePath = file.getAbsolutePath();
        }

        CameraUtils var10000 = CameraUtils.INSTANCE;
        Activity var10001 = this.myActivity;
        if (var10001 == null) {
            SplashActivity.throwJavaNpe();
        }

        Context var4 = (Context)var10001;
        if (file == null) {
            SplashActivity.throwJavaNpe();
        }

        Uri fileUri = var10000.getOutputMediaFileUri(var4, file);
        intent.putExtra("android.intent.extra.videoQuality", 1);
        intent.putExtra("output", (Parcelable)fileUri);
        this.startActivityForResult(intent, this.CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CameraUtils var10000;
        Activity var10001;
        String var10002;
        Context var4;
        if (requestCode == this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == -1) {
                var10000 = CameraUtils.INSTANCE;
                var10001 = this.myActivity;
                if (var10001 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var4 = (Context)var10001;
                var10002 = this.imageStoragePath;
                if (var10002 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var10000.refreshGallery(var4, var10002);
                this.previewCapturedImage();
            } else if (resultCode == 0) {
                Toast.makeText((Context)this.myActivity, (CharSequence)"User cancelled image capture", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText((Context)this.myActivity, (CharSequence)"Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == this.CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == -1) {
                var10000 = CameraUtils.INSTANCE;
                var10001 = this.myActivity;
                if (var10001 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var4 = (Context)var10001;
                var10002 = this.imageStoragePath;
                if (var10002 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var10000.refreshGallery(var4, var10002);
                this.previewVideo();
            } else if (resultCode == 0) {
                Toast.makeText((Context)this.myActivity, (CharSequence)"User cancelled video recording", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText((Context)this.myActivity, (CharSequence)"Sorry! Failed to record video", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private final void previewCapturedImage() {
        try {
            TextView var10000 = this.txtDescription;
            if (var10000 == null) {
                SplashActivity.throwJavaNpe();
            }

            var10000.setVisibility(View.GONE);
            VideoView var3 = this.videoPreview;
            if (var3 == null) {
                SplashActivity.throwJavaNpe();
            }

            var3.setVisibility(View.GONE);
            ImageView var4 = this.imgPreview;
            if (var4 == null) {
                SplashActivity.throwJavaNpe();
            }

            var4.setVisibility(View.VISIBLE);
            CameraUtils var5 = CameraUtils.INSTANCE;
            int var10001 = this.BITMAP_SAMPLE_SIZE;
            String var10002 = this.imageStoragePath;
            if (var10002 == null) {
                SplashActivity.throwJavaNpe();
            }

            Bitmap bitmap = var5.optimizeBitmap(var10001, var10002);
            var4 = this.imgPreview;
            if (var4 == null) {
                SplashActivity.throwJavaNpe();
            }

            var4.setImageBitmap(bitmap);
            Log.e("Image Preview", "" + this.imgPreview);
        } catch (NullPointerException var2) {
            var2.printStackTrace();
        }

    }

    private final void previewVideo() {
        try {
            TextView var10000 = this.txtDescription;
            if (var10000 == null) {
                SplashActivity.throwJavaNpe();
            }

            var10000.setVisibility(View.GONE);
            ImageView var3 = this.imgPreview;
            if (var3 == null) {
                SplashActivity.throwJavaNpe();
            }

            var3.setVisibility(View.GONE);
            VideoView var4 = this.videoPreview;
            if (var4 == null) {
                SplashActivity.throwJavaNpe();
            }

            var4.setVisibility(View.VISIBLE);
            var4 = this.videoPreview;
            if (var4 == null) {
                SplashActivity.throwJavaNpe();
            }

            var4.setVideoPath(this.imageStoragePath);
            var4 = this.videoPreview;
            if (var4 == null) {
                SplashActivity.throwJavaNpe();
            }

            var4.start();
            Log.e("Recording Video Path", "" + this.videoPreview);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private final void showPermissionsAlert() {
        Builder builder = new Builder((Context)this.myActivity);
        builder.setTitle((CharSequence)"Permissions required!").setMessage((CharSequence)"Camera needs few permissions to work properly. Grant them in settings.").setPositiveButton((CharSequence)"GOTO SETTINGS", (android.content.DialogInterface.OnClickListener)(new android.content.DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialog, int which) {
                CameraUtils var10000 = CameraUtils.INSTANCE;
                Activity var10001 = VideoRecordingFragment.this.getMyActivity();
                if (var10001 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var10000.openSettings((Context)var10001);
            }
        })).setNegativeButton((CharSequence)"CANCEL", (android.content.DialogInterface.OnClickListener)null).show();
    }

    // $FF: synthetic method
    public static final void access$setImageStoragePath$p(VideoRecordingFragment $this, String var1) {
        $this.imageStoragePath = var1;
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            View var10000 = this.getView();
            if (var10000 == null) {
                return null;
            }

            var2 = var10000.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }

    // $FF: synthetic method
    public void onDestroyView() {
        super.onDestroyView();
        this._$_clearFindViewByIdCache();
    }
}
