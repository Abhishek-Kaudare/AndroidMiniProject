package com.abhishekkaudare.womensafety.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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
        CameraUtils temp = CameraUtils.INSTANCE;
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        if (!temp.isDeviceSupportCamera(temp1)) {
            Toast.makeText(this.myActivity, "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
        }

        this.txtDescription = view != null ? (TextView)view.findViewById(R.id.txt_desc) : null;
        this.imgPreview = view != null ? (ImageView)view.findViewById(R.id.imgPreview) : null;
        this.videoPreview = view != null ? (VideoView)view.findViewById(R.id.videoPreview) : null;
        this.btnCapturePicture = view != null ? (ImageButton)view.findViewById(R.id.btnCapturePicture) : null;
        this.btnRecordVideo = view != null ? (ImageButton)view.findViewById(R.id.btnRecordVideo) : null;
        this.btnShare = view != null ? (ImageButton)view.findViewById(R.id.sharevideo) : null;
        ImageButton var5 = this.btnCapturePicture;
        if (var5 == null) {
            SplashActivity.throwJavaNpe();
        }

        var5.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                VideoRecordingFragment.this.captureImage();
            }
        });
        var5 = this.btnRecordVideo;
        if (var5 == null) {
            SplashActivity.throwJavaNpe();
        }

        var5.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                VideoRecordingFragment.this.captureVideo();
            }
        });
        ImageView var6 = this.btnShare;
        if (var6 == null) {
            SplashActivity.throwJavaNpe();
        }

        var6.setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {
                Intent videoshareintent = new Intent("android.intent.action.SEND");
                videoshareintent.setType("video/mp4");
                videoshareintent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(VideoRecordingFragment.this.imageStoragePath)));
                Log.e("Video Share", "" + videoshareintent);
                VideoRecordingFragment.this.startActivity(videoshareintent);
            }
        });
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

                String temp = this.imageStoragePath;
                if (temp == null) {
                    SplashActivity.throwJavaNpe();
                }

                String var2 = temp;
                temp = this.imageStoragePath;
                if (temp == null) {
                    SplashActivity.throwJavaNpe();
                }

                int var3 = this.imageStoragePath.lastIndexOf(".");
                if (var2 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type java.lang.String");
                }

                temp = var2.substring(var3);
                SplashActivity.checkExpressionValueIsNotNull(temp, "(this as java.lang.String).substring(startIndex)");
                if (SplashActivity.areEqual(temp, '.' + this.IMAGE_EXTENSION)) {
                    this.previewCapturedImage();
                } else {
                    temp = this.imageStoragePath;
                    if (temp == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var2 = temp;
                    temp = this.imageStoragePath;
                    if (temp == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var3 = temp.lastIndexOf(".");
                    if (var2 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type java.lang.String");
                    }

                    temp = var2.substring(var3);
                    SplashActivity.checkExpressionValueIsNotNull(temp, "(this as java.lang.String).substring(startIndex)");
                    if (SplashActivity.areEqual(temp, '.' + this.VIDEO_EXTENSION)) {
                        this.previewVideo();
                    }
                }
            }
        }

    }

    private final void requestCameraPermission(final int type) {
        Dexter.withActivity(this.myActivity).withPermissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO").withListener(new MultiplePermissionsListener() {
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
        }).check();
    }

    private final void captureImage() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = CameraUtils.INSTANCE.getOutputMediaFile(this.MEDIA_TYPE_IMAGE);
        if (file != null) {
            this.imageStoragePath = file.getAbsolutePath();
        }

        CameraUtils temp = CameraUtils.INSTANCE;
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        Context var4 = temp1;
        if (file == null) {
            SplashActivity.throwJavaNpe();
        }

        Uri fileUri = temp.getOutputMediaFileUri(var4, file);
        intent.putExtra("output", fileUri);
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
        if (file == null) {
            SplashActivity.throwJavaNpe();
        }
        if (file != null) {
            this.imageStoragePath = file.getAbsolutePath();
        }

        CameraUtils temp = CameraUtils.INSTANCE;
        Activity temp1 = this.myActivity;
        if (temp1 == null) {
            SplashActivity.throwJavaNpe();
        }

        Context var4 = temp1;


        Uri fileUri = temp.getOutputMediaFileUri(var4, file);
        intent.putExtra("android.intent.extra.videoQuality", 1);
        intent.putExtra("output", fileUri);
        this.startActivityForResult(intent, this.CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CameraUtils temp;
        Activity temp1;
        String var10002;
        Context var4;
        if (requestCode == this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == -1) {
                temp = CameraUtils.INSTANCE;
                temp1 = this.myActivity;
                if (temp1 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var4 = temp1;
                var10002 = this.imageStoragePath;
                if (var10002 == null) {
                    SplashActivity.throwJavaNpe();
                }

                temp.refreshGallery(var4, var10002);
                this.previewCapturedImage();
            } else if (resultCode == 0) {
                Toast.makeText(this.myActivity, "User cancelled image capture", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.myActivity, "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == this.CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == -1) {
                temp = CameraUtils.INSTANCE;
                temp1 = this.myActivity;
                if (temp1 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var4 = temp1;
                var10002 = this.imageStoragePath;
                if (var10002 == null) {
                    SplashActivity.throwJavaNpe();
                }

                temp.refreshGallery(var4, var10002);
                this.previewVideo();
            } else if (resultCode == 0) {
                Toast.makeText(this.myActivity, "User cancelled video recording", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.myActivity, "Sorry! Failed to record video", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private final void previewCapturedImage() {
        try {
            TextView temp = this.txtDescription;
            if (temp == null) {
                SplashActivity.throwJavaNpe();
            }

            temp.setVisibility(View.GONE);
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
            int temp1 = this.BITMAP_SAMPLE_SIZE;
            String var10002 = this.imageStoragePath;
            if (var10002 == null) {
                SplashActivity.throwJavaNpe();
            }

            Bitmap bitmap = var5.optimizeBitmap(temp1, var10002);
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
            TextView temp = this.txtDescription;
            if (temp == null) {
                SplashActivity.throwJavaNpe();
            }

            temp.setVisibility(View.GONE);
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
        Builder builder = new Builder(this.myActivity);
        builder.setTitle("Permissions required!").setMessage("Camera needs few permissions to work properly. Grant them in settings.").setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialog, int which) {
                CameraUtils temp = CameraUtils.INSTANCE;
                Activity temp1 = VideoRecordingFragment.this.getMyActivity();
                if (temp1 == null) {
                    SplashActivity.throwJavaNpe();
                }

                temp.openSettings(temp1);
            }
        }).setNegativeButton("CANCEL", null).show();
    }

    // $FF: synthetic method
    public static final void access$setImageStoragePath$p(VideoRecordingFragment $this, String var1) {
        $this.imageStoragePath = var1;
    }

   
}
