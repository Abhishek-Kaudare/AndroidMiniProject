package com.abhishekkaudare.womensafety.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.lang.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import android.view.Window;
import android.view.WindowManager;

import com.abhishekkaudare.womensafety.R;


public class SplashActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); // Hide The Title Bar
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = this.getSharedPreferences(SplashActivity.Staticated.INSTANCE.getIS_LOGGEDIN(), Context.MODE_PRIVATE);
        Boolean isloggedin = prefs != null ? prefs.getBoolean("loginstatus", false) : null;
        if (isloggedin == null) {
            throwJavaNpe();
        }

        if (isloggedin) {
            (new Handler()).postDelayed((new Runnable() {
                public final void run() {
                    Intent startAct = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(startAct);
                    SplashActivity.this.finish();
                }
            }), 1000L);
        } else {
            (new Handler()).postDelayed((new Runnable() {
                public final void run() {
                    Intent startAct = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(startAct);
                    SplashActivity.this.finish();
                }
            }), 1000L);
        }

    }



    public static final class Staticated {

        private static String IS_LOGGEDIN;
        public static final SplashActivity.Staticated INSTANCE;

        public final String getIS_LOGGEDIN() {
            return IS_LOGGEDIN;
        }

        public final void setIS_LOGGEDIN(String var1) {
            checkParameterIsNotNull(var1, "<set-?>");
            IS_LOGGEDIN = var1;
        }

        private Staticated() {
        }

        static {
            SplashActivity.Staticated var0 = new SplashActivity.Staticated();
            INSTANCE = var0;
            IS_LOGGEDIN = "Login Check";
        }
    }



    public static void checkExpressionValueIsNotNull(Object value, String expression) {
        if (value == null) {
            throw sanitizeStackTrace(new IllegalStateException(expression + " must not be null"));
        }
    }

    private static void throwParameterIsNullException(String paramName) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTraceElements[3];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();

        IllegalArgumentException exception =
                new IllegalArgumentException("Parameter specified as non-null is null: " +
                        "method " + className + "." + methodName +
                        ", parameter " + paramName);
        throw sanitizeStackTrace(exception);
    }

    public static void checkParameterIsNotNull(Object value, String paramName) {
        if (value == null) {
            throwParameterIsNullException(paramName);
        }
    }

    public static void throwJavaNpe() {
        throw sanitizeStackTrace(new NullPointerException());
    }

    private static <T extends Throwable> T sanitizeStackTrace(T throwable) {
        return sanitizeStackTrace(throwable,"SplashActivity");
    }

    static <T extends Throwable> T sanitizeStackTrace(T throwable, String classNameToDrop) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int size = stackTrace.length;

        int lastIntrinsic = -1;
        for (int i = 0; i < size; i++) {
            if (classNameToDrop.equals(stackTrace[i].getClassName())) {
                lastIntrinsic = i;
            }
        }

        StackTraceElement[] newStackTrace = Arrays.copyOfRange(stackTrace, lastIntrinsic + 1, size);
        throwable.setStackTrace(newStackTrace);
        return throwable;
    }

    public static boolean areEqual(Object first, Object second) {
        return Objects.equals(first, second);
    }

}
