package com.abhishekkaudare.womensafety.helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CameraUtils {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    @NotNull
    public static final String GALLERY_DIRECTORY_NAME = "Record Video";
    @NotNull
    public static final String IMAGE_EXTENSION = "jpg";
    @NotNull
    public static final String VIDEO_EXTENSION = "mp4";
    public static final CameraUtils INSTANCE;

    public final void refreshGallery(@NotNull Context context, @NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        MediaScannerConnection.scanFile(context, new String[]{filePath}, null, null);
    }

    public final boolean checkPermissions(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return ActivityCompat.checkSelfPermission(context, "android.permission.CAMERA") == 0 && ActivityCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ActivityCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0;
    }

    @NotNull
    public final Bitmap optimizeBitmap(int sampleSize, @NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Options options = new Options();
        options.inSampleSize = sampleSize;
        Bitmap var10000 = BitmapFactory.decodeFile(filePath, options);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "BitmapFactory.decodeFile(filePath, options)");
        return var10000;
    }

    public final boolean isDeviceSupportCamera(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public final void openSettings(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", "com.abhishekkaudare.womensafety", null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @NotNull
    public final Uri getOutputMediaFileUri(@NotNull Context context, @NotNull File file) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(file, "file");
        Uri var10000 = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "FileProvider.getUriForFi…Name + \".provider\", file)");
        return var10000;
    }

    @Nullable
    public final File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Record Video");
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.e("Record Video", "Oops! Failed create Record Video directory");
            return null;
        } else {
            String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())).format(new Date());
            File mediaFile = null;
            if (type == 1) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "." + "jpg");
            } else {
                if (type != 2) {
                    return null;
                }

                mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + "." + "mp4");
            }

            return mediaFile;
        }
    }

    private CameraUtils() {
    }

    static {
        CameraUtils var0 = new CameraUtils();
        INSTANCE = var0;
    }
}
