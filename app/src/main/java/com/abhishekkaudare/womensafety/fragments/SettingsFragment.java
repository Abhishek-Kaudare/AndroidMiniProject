package com.abhishekkaudare.womensafety.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.R.id;
import com.abhishekkaudare.womensafety.activities.RegisterActivity;
import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.abhishekkaudare.womensafety.activities.SplashActivity.Staticated;
import com.abhishekkaudare.womensafety.helpers.DatabaseHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class SettingsFragment extends Fragment {
    @Nullable
    private Activity myActivity;
    @NotNull
    private EditText[] et = new EditText[12];
    @NotNull
    private int[] ids = new int[]{
            R.id.rreg_name,
            R.id.rreg_age,
            R.id.rreg_cno,
            R.id.rreg_email,
            R.id.rnumber_1,
            R.id.remergency_gmail_1,
            R.id.rnumber_2,
            R.id.remergency_gmail_2,
            R.id.rnumber_3,
            R.id.remergency_gmail_3,
            R.id.rreg_pass,
            R.id.rreg_re_pass
    };
    @NotNull
    private String[] values;
    @Nullable
    private EditText pass;
    @Nullable
    private EditText c_pass;
    @Nullable
    private CheckBox cb;
    @Nullable
    private Button update;
    @Nullable
    private Button delete;
//    private HashMap _$_findViewCache;
//
//    @Nullable
//    public final Activity getMyActivity() {
//        return this.myActivity;
//    }
//
//    public final void setMyActivity(@Nullable Activity var1) {
//        this.myActivity = var1;
//    }
//
//    @NotNull
//    public final EditText[] getEt$app_debug() {
//        return this.et;
//    }
//
//    public final void setEt$app_debug(@NotNull EditText[] var1) {
//        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
//        this.et = var1;
//    }
//
//    @NotNull
//    public final int[] getIds$app_debug() {
//        return this.ids;
//    }
//
//    public final void setIds$app_debug(@NotNull int[] var1) {
//        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
//        this.ids = var1;
//    }
//
//    @NotNull
//    public final String[] getValues$app_debug() {
//        return this.values;
//    }
//
//    public final void setValues$app_debug(@NotNull String[] var1) {
//        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
//        this.values = var1;
//    }

    @Nullable
    public final EditText getPass() {
        return this.pass;
    }

//    public final void setPass(@Nullable EditText var1) {
//        this.pass = var1;
//    }

    @Nullable
    public final EditText getC_pass() {
        return this.c_pass;
    }

//    public final void setC_pass(@Nullable EditText var1) {
//        this.c_pass = var1;
//    }
//
//    @Nullable
//    public final CheckBox getCb() {
//        return this.cb;
//    }
//
//    public final void setCb(@Nullable CheckBox var1) {
//        this.cb = var1;
//    }
//
//    @Nullable
//    public final Button getUpdate() {
//        return this.update;
//    }
//
//    public final void setUpdate(@Nullable Button var1) {
//        this.update = var1;
//    }
//
//    @Nullable
//    public final Button getDelete() {
//        return this.delete;
//    }
//
//    public final void setDelete(@Nullable Button var1) {
//        this.delete = var1;
//    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashActivity.checkParameterIsNotNull(inflater, "inflater");
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        int i = 0;

        for(int var6 = this.et.length; i < var6; ++i) {
            EditText[] var10000 = this.et;
            EditText var10002 = view != null ? (EditText)view.findViewById(this.ids[i]) : null;
            if (var10002 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            var10000[i] = var10002;
        }

        EditText var10001 = view != null ? (EditText)view.findViewById(id.rreg_pass) : null;
        if (var10001 == null) {
            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
        } else {
            this.pass = var10001;
            var10001 = view != null ? (EditText)view.findViewById(id.rreg_re_pass) : null;
            if (var10001 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            } else {
                this.c_pass = var10001;
                CheckBox var32 = view != null ? (CheckBox)view.findViewById(id.rcb1) : null;
                if (var32 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.CheckBox");
                } else {
                    this.cb = var32;
                    CheckBox var30 = this.cb;
                    if (var30 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var30.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                        public final void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            EditText var10000;
                            if (!isChecked) {
                                var10000 = SettingsFragment.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                var10000 = SettingsFragment.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            } else {
                                var10000 = SettingsFragment.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                var10000 = SettingsFragment.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            }

                        }
                    });
                    EditText var31 = view != null ? (EditText)view.findViewById(id.rreg_name) : null;
                    if (var31 == null) {
                        throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                    } else {
                        EditText box1 = var31;
                        var31 = view != null ? (EditText)view.findViewById(id.rreg_age) : null;
                        if (var31 == null) {
                            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                        } else {
                            EditText box2 = var31;
                            var31 = view != null ? (EditText)view.findViewById(id.rreg_cno) : null;
                            if (var31 == null) {
                                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                            } else {
                                EditText box3 = var31;
                                var31 = view != null ? (EditText)view.findViewById(id.rreg_email) : null;
                                if (var31 == null) {
                                    throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                } else {
                                    EditText box4 = var31;
                                    var31 = view != null ? (EditText)view.findViewById(id.rnumber_1) : null;
                                    if (var31 == null) {
                                        throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                    } else {
                                        EditText box5 = var31;
                                        var31 = view != null ? (EditText)view.findViewById(id.remergency_gmail_1) : null;
                                        if (var31 == null) {
                                            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                        } else {
                                            EditText box6 = var31;
                                            var31 = view != null ? (EditText)view.findViewById(id.rnumber_2) : null;
                                            if (var31 == null) {
                                                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                            } else {
                                                EditText box7 = var31;
                                                var31 = view != null ? (EditText)view.findViewById(id.remergency_gmail_2) : null;
                                                if (var31 == null) {
                                                    throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                                } else {
                                                    EditText box8 = var31;
                                                    var31 = view != null ? (EditText)view.findViewById(id.rnumber_3) : null;
                                                    if (var31 == null) {
                                                        throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                                    } else {
                                                        EditText box9 = var31;
                                                        var31 = view != null ? (EditText)view.findViewById(id.remergency_gmail_3) : null;
                                                        if (var31 == null) {
                                                            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                                        } else {
                                                            EditText box10 = var31;
                                                            var31 = view != null ? (EditText)view.findViewById(id.rreg_pass) : null;
                                                            if (var31 == null) {
                                                                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                                            } else {
                                                                EditText box11 = var31;
                                                                var31 = view != null ? (EditText)view.findViewById(id.rreg_re_pass) : null;
                                                                if (var31 == null) {
                                                                    throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
                                                                } else {
                                                                    EditText box12 = var31;
                                                                    Activity var34 = this.myActivity;
                                                                    if (var34 == null) {
                                                                        SplashActivity.throwJavaNpe();
                                                                    }

                                                                    SharedPreferences prefs = var34.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
                                                                    if (box1 != null) {
                                                                        box1.setText(prefs != null ? prefs.getString("name", "Failed to fetch Data") : null);
                                                                    }

                                                                    int ide = prefs.getInt("userId", 0);
                                                                    int one = prefs.getInt("age", 0);
                                                                    if (box2 != null) {
                                                                        box2.setText(String.valueOf(one));
                                                                    }

                                                                    long two = prefs.getLong("phone", 0L);
                                                                    if (box3 != null) {
                                                                        box3.setText(String.valueOf(two));
                                                                    }

                                                                    if (box4 != null) {
                                                                        box4.setText(prefs != null ? prefs.getString("mail", "Failed to fetch Data") : null);
                                                                    }

                                                                    long three = prefs.getLong("phone1", 0L);
                                                                    if (box5 != null) {
                                                                        box5.setText(String.valueOf(three));
                                                                    }

                                                                    long four = prefs.getLong("phone2", 0L);
                                                                    if (box9 != null) {
                                                                        box9.setText(String.valueOf(four));
                                                                    }

                                                                    long five = prefs.getLong("phone3", 0L);
                                                                    if (box7 != null) {
                                                                        box7.setText(String.valueOf(five));
                                                                    }

                                                                    if (box8 != null) {
                                                                        box8.setText(prefs != null ? prefs.getString("mail2", "Failed to fetch Data") : null);
                                                                    }

                                                                    if (box6 != null) {
                                                                        box6.setText(prefs != null ? prefs.getString("mail1", "Failed to fetch Data") : null);
                                                                    }

                                                                    if (box10 != null) {
                                                                        box10.setText(prefs != null ? prefs.getString("mail3", "Failed to fetch Data") : null);
                                                                    }

                                                                    if (box11 != null) {
                                                                        box11.setText(prefs != null ? prefs.getString("password", "Failed to fetch Data") : null);
                                                                    }

                                                                    if (box12 != null) {
                                                                        box12.setText(prefs != null ? prefs.getString("password", "Failed to fetch Data") : null);
                                                                    }

                                                                    FragmentActivity var35 = this.getActivity();
                                                                    if (var35 != null) {
                                                                        var35.setTitle("Settings");
                                                                    }

                                                                    Button var33 = view != null ? (Button)view.findViewById(id.rreg_update) : null;
                                                                    if (var33 == null) {
                                                                        throw new ClassCastException("null cannot be cast to non-null type android.widget.Button");
                                                                    } else {
                                                                        this.update = var33;
                                                                        var33 = view != null ? (Button)view.findViewById(id.rreg_delete) : null;
                                                                        if (var33 == null) {
                                                                            throw new ClassCastException("null cannot be cast to non-null type android.widget.Button");
                                                                        } else {
                                                                            this.delete = var33;
                                                                            Button var36 = this.update;
                                                                            if (var36 == null) {
                                                                                SplashActivity.throwJavaNpe();
                                                                            }

                                                                            var36.setOnClickListener(new OnClickListener() {
                                                                                public final void onClick(View it) {
                                                                                    SettingsFragment.this.updateUser(view);
                                                                                }
                                                                            });
                                                                            var36 = this.delete;
                                                                            if (var36 == null) {
                                                                                SplashActivity.throwJavaNpe();
                                                                            }

                                                                            var36.setOnClickListener(new OnClickListener() {
                                                                                public final void onClick(View it) {
                                                                                    SettingsFragment.this.deleteUser(view);
                                                                                }
                                                                            });
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

    public final boolean isValidPhone(@Nullable final String phone) {
        Pattern pattern;
        Matcher matcher;
        if (phone != null) {
            final String phonePattern = "^[2-9]{2}[0-9]{8}$";
            pattern = Pattern.compile(phonePattern);
            matcher = pattern.matcher(phone);
            return matcher.matches();
        } else {
            return false;
        }
    }

    public final boolean isEmailValid(@NotNull String email) {
        SplashActivity.checkParameterIsNotNull(email, "email");
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public final boolean isValidName(@Nullable final String name) {
        Pattern pattern;
        Matcher matcher;
        if (name != null) {
            final String namePattern = "^[\\p{L}\\p{M}]+([\\p{L}\\p{Pd}\\p{Zs}'.]*[\\p{L}\\p{M}])+$|^[\\p{L}\\p{M}]+$";
            pattern = Pattern.compile(namePattern);
            matcher = pattern.matcher(name);
            return matcher.matches();
        } else {
            return false;
        }
    }

    private final boolean isValidPassword(@NonNull String password) {
        Pattern pattern = null;
        Matcher matcher = null;
        String specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_";
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[" + specialCharacters + "])(?=\\S+$).{8,20}$";
        Pattern var10000 = Pattern.compile(PASSWORD_REGEX);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "Pattern.compile(PASSWORD_REGEX)");
        pattern = var10000;
        Matcher var6 = pattern.matcher(password);
        SplashActivity.checkExpressionValueIsNotNull(var6, "pattern.matcher(password)");
        matcher = var6;
        return matcher.matches();
    }

    public final void updateUser(@NotNull View v) {
        SplashActivity.checkParameterIsNotNull(v, "v");
        int i = 0;
        String[] var10000 = this.values;
        EditText var10002 = this.et[i];
        if (var10002 == null) {
            SplashActivity.throwJavaNpe();
        }

        String $this$trim$iv = var10002.getText().toString();
        String[] var13 = var10000;
        CharSequence $this$trim$iv$iv = $this$trim$iv;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;

        int index$iv$iv;
        char it;
        boolean var12;
        boolean match$iv$iv;
        while(startIndex$iv$iv <= endIndex$iv$iv) {
            index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            it = $this$trim$iv$iv.charAt(index$iv$iv);
            var12 = false;
            match$iv$iv = it <= ' ';
            if (!startFound$iv$iv) {
                if (!match$iv$iv) {
                    startFound$iv$iv = true;
                } else {
                    ++startIndex$iv$iv;
                }
            } else {
                if (!match$iv$iv) {
                    break;
                }

                --endIndex$iv$iv;
            }
        }

        String var15 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
        var13[i] = var15;
        EditText var21;
        if (!this.isValidName(this.values[i])) {
            var21 = this.et[i];
            if (var21 == null) {
                SplashActivity.throwJavaNpe();
            }

            var21.requestFocus();
            var21 = this.et[i];
            if (var21 == null) {
                SplashActivity.throwJavaNpe();
            }

            var21.setError("Invalid Name");
        } else {
            i = i + 1;
            var10000 = this.values;
            var10002 = this.et[i];
            if (var10002 == null) {
                SplashActivity.throwJavaNpe();
            }

            $this$trim$iv = var10002.getText().toString();
            var13 = var10000;

            $this$trim$iv$iv = $this$trim$iv;

            startIndex$iv$iv = 0;
            endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
            startFound$iv$iv = false;

            while(startIndex$iv$iv <= endIndex$iv$iv) {
                index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                it = $this$trim$iv$iv.charAt(index$iv$iv);
                var12 = false;
                match$iv$iv = it <= ' ';
                if (!startFound$iv$iv) {
                    if (!match$iv$iv) {
                        startFound$iv$iv = true;
                    } else {
                        ++startIndex$iv$iv;
                    }
                } else {
                    if (!match$iv$iv) {
                        break;
                    }

                    --endIndex$iv$iv;
                }
            }

            var15 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
            var13[i] = var15;
            String var20 = this.values[i];
            if (var20 == null) {
                SplashActivity.throwJavaNpe();
            }

            CharSequence var18 = var20;

            if (var18.length() == 0) {
                var21 = this.et[i];
                if (var21 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var21.requestFocus();
                var21 = this.et[i];
                if (var21 == null) {
                    SplashActivity.throwJavaNpe();
                }

                var21.setError("Invalid Age");
            } else {
                ++i;

                String var10001;
                while(i < 10) {
                    var10000 = this.values;
                    var10002 = this.et[i];
                    if (var10002 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    $this$trim$iv = var10002.getText().toString();
                    var13 = var10000;
                    $this$trim$iv$iv = $this$trim$iv;
                    startIndex$iv$iv = 0;
                    endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                    startFound$iv$iv = false;

                    while(startIndex$iv$iv <= endIndex$iv$iv) {
                        index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                        it = $this$trim$iv$iv.charAt(index$iv$iv);
                        var12 = false;
                        match$iv$iv = it <= ' ';
                        if (!startFound$iv$iv) {
                            if (!match$iv$iv) {
                                startFound$iv$iv = true;
                            } else {
                                ++startIndex$iv$iv;
                            }
                        } else {
                            if (!match$iv$iv) {
                                break;
                            }

                            --endIndex$iv$iv;
                        }
                    }

                    var15 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                    var13[i] = var15;
                    var20 = this.values[i];
                    if (var20 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var18 = var20;
                    if (var18.length() == 0) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Phone Number Cannot be Empty");
                        return;
                    }

                    var10001 = this.values[i];
                    if (var10001 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    if (!this.isValidPhone(var10001)) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Invalid Phone Number");
                        return;
                    }

                    ++i;
                    var10000 = this.values;
                    var10002 = this.et[i];
                    if (var10002 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    $this$trim$iv = var10002.getText().toString();
                    var13 = var10000;
                    $this$trim$iv$iv = $this$trim$iv;
                    startIndex$iv$iv = 0;
                    endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                    startFound$iv$iv = false;

                    while(startIndex$iv$iv <= endIndex$iv$iv) {
                        index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                        it = $this$trim$iv$iv.charAt(index$iv$iv);
                        var12 = false;
                        match$iv$iv = it <= ' ';
                        if (!startFound$iv$iv) {
                            if (!match$iv$iv) {
                                startFound$iv$iv = true;
                            } else {
                                ++startIndex$iv$iv;
                            }
                        } else {
                            if (!match$iv$iv) {
                                break;
                            }

                            --endIndex$iv$iv;
                        }
                    }

                    var15 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                    var13[i] = var15;
                    var20 = this.values[i];
                    if (var20 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var18 = var20;
                    if (var18.length() == 0) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("E-mail Id cannot be Empty");
                        return;
                    }

                    var10001 = this.values[i];
                    if (var10001 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    if (!this.isEmailValid(var10001)) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("E-mail Id Invalid");
                        return;
                    }

                    ++i;
                }

                while(i < this.et.length) {
                    var10000 = this.values;
                    var10002 = this.et[i];
                    if (var10002 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    $this$trim$iv = var10002.getText().toString();
                    var13 = var10000;
                    $this$trim$iv$iv = $this$trim$iv;
                    startIndex$iv$iv = 0;
                    endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                    startFound$iv$iv = false;

                    while(startIndex$iv$iv <= endIndex$iv$iv) {
                        index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                        it = $this$trim$iv$iv.charAt(index$iv$iv);
                        var12 = false;
                        match$iv$iv = it <= ' ';
                        if (!startFound$iv$iv) {
                            if (!match$iv$iv) {
                                startFound$iv$iv = true;
                            } else {
                                ++startIndex$iv$iv;
                            }
                        } else {
                            if (!match$iv$iv) {
                                break;
                            }

                            --endIndex$iv$iv;
                        }
                    }

                    var15 = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                    var13[i] = var15;
                    var20 = this.values[i];
                    if (var20 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var18 = var20;
                    if (var18.length() == 0) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Password cannot be Empty");
                        return;
                    }

                    var10001 = this.values[i];
                    if (var10001 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    if (!this.isValidPassword(var10001)) {
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[i];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Invalid Password");
                        return;
                    }

                    ++i;
                }

                if (i == this.et.length) {
                    if (SplashActivity.areEqual(this.values[2], this.values[4])) {
                        Toast.makeText(this.myActivity, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
                        var21 = this.et[4];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[4];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[5])) {
                        Toast.makeText(this.myActivity, "Emergency Mail same as your Mail",Toast.LENGTH_SHORT ).show();
                        var21 = this.et[5];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[5];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[2], this.values[6])) {
                        Toast.makeText(this.myActivity, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[4], this.values[6])) {
                        Toast.makeText(this.myActivity, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Contact matches Emergency Contact 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[7])) {
                        Toast.makeText(this.myActivity, "Emergency Mail same as your Mail", Toast.LENGTH_SHORT).show();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[5], this.values[7])) {
                        Toast.makeText(this.myActivity, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Mail matches Emergency Mail 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[2], this.values[8])) {
                        Toast.makeText(this.myActivity, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[4], this.values[8])) {
                        Toast.makeText(this.myActivity, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Contact matches Emergency Contact 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[6], this.values[8])) {
                        Toast.makeText(this.myActivity, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Contact matches Emergency Contact 2");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[9])) {
                        Toast.makeText(this.myActivity, "Emergency Mail same as your Mail", Toast.LENGTH_SHORT).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[5], this.values[9])) {
                        Toast.makeText(this.myActivity, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Mail matches Emergency Mail 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[7], this.values[9])) {
                        Toast.makeText(this.myActivity, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Mail matches Emergency Mail 2");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[10], this.values[11])) {
                        Toast.makeText(this.myActivity, "Passwords Matched", Toast.LENGTH_SHORT).show();
                        this.addData();
                    } else {
                        var21 = this.et[11];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[11];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError("Passwords Do Not Match");
                    }
                }

            }
        }
    }

    public final void addData() {
        Toast.makeText(this.myActivity, "Data Insert Success", Toast.LENGTH_LONG).show();
        Toast.makeText(this.myActivity, "Data Updated", Toast.LENGTH_LONG).show();

        DatabaseHandler helper = new DatabaseHandler(this.myActivity);
        Activity var21 = this.myActivity;
        if (var21 == null) {
            SplashActivity.throwJavaNpe();
        }

        SharedPreferences prefs = var21.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
        String ide = String.valueOf(prefs.getInt("userId", 0));
        EditText var22 = this.getView().findViewById(id.rreg_name);
        SplashActivity.checkExpressionValueIsNotNull(var22, "rreg_name");
        String var23 = var22.getText().toString();
        EditText var10003 = this.getView().findViewById(id.rreg_age);
        SplashActivity.checkExpressionValueIsNotNull(var10003, "rreg_age");
        String var4 = var10003.getText().toString();
        String var8 = var23;
        boolean var5 = false;
        int var9 = Integer.parseInt(var4);
        EditText var10004 = this.getView().findViewById(id.rreg_cno);
        SplashActivity.checkExpressionValueIsNotNull(var10004, "rreg_cno");
        var4 = var10004.getText().toString();
        var5 = false;
        long var10 = Long.parseLong(var4);
        EditText var10005 = this.getView().findViewById(id.rreg_email);
        SplashActivity.checkExpressionValueIsNotNull(var10005, "rreg_email");
        String var24 = var10005.getText().toString();
        EditText var10006 = this.getView().findViewById(id.rnumber_1);
        SplashActivity.checkExpressionValueIsNotNull(var10006, "rnumber_1");
        var4 = var10006.getText().toString();
        String var12 = var24;
        var5 = false;
        long var13 = Long.parseLong(var4);
        EditText var10007 = this.getView().findViewById(id.remergency_gmail_1);
        SplashActivity.checkExpressionValueIsNotNull(var10007, "remergency_gmail_1");
        String var25 = var10007.getText().toString();
        EditText var10008 = this.getView().findViewById(id.rnumber_2);
        SplashActivity.checkExpressionValueIsNotNull(var10008, "rnumber_2");
        var4 = var10008.getText().toString();
        String var15 = var25;
        var5 = false;
        long var16 = Long.parseLong(var4);
        EditText var10009 = this.getView().findViewById(id.remergency_gmail_2);
        SplashActivity.checkExpressionValueIsNotNull(var10009, "remergency_gmail_2");
        String var26 = var10009.getText().toString();
        EditText var10010 = this.getView().findViewById(id.rnumber_3);
        SplashActivity.checkExpressionValueIsNotNull(var10010, "rnumber_3");
        var4 = var10010.getText().toString();
        String var18 = var26;
        var5 = false;
        long var19 = Long.parseLong(var4);
        EditText var10011 = this.getView().findViewById(id.remergency_gmail_3);
        SplashActivity.checkExpressionValueIsNotNull(var10011, "remergency_gmail_3");
        String var27 = var10011.getText().toString();
        EditText var10012 = this.getView().findViewById(id.rreg_re_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10012, "rreg_re_pass");
        helper.updateData(ide, var8, var9, var10, var12, var13, var15, var16, var18, var19, var27, var10012.getText().toString());
        this.clearAllFields();
    }

    public final void clearAllFields() {

        DatabaseHandler helper = new DatabaseHandler(this.myActivity);
        SQLiteDatabase db = helper.getWritableDatabase();
        Activity var32 = this.myActivity;
        if (var32 == null) {
            SplashActivity.throwJavaNpe();
        }

        SharedPreferences prefs = var32.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
        int ide = prefs.getInt("userId", 0);
        String qry = "select * from my_table where id = '" + ide + "';";
        Cursor c = db.rawQuery(qry, null);
        boolean res = c.moveToFirst();
        if (res) {
            var32 = this.myActivity;
            if (var32 == null) {
                SplashActivity.throwJavaNpe();
            }

            Editor editor = var32.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0).edit();
            if (editor != null) {
                editor.putBoolean("loginstatus", false);
            }

            if (editor != null) {
                editor.putInt("userId", c.getInt(0));
            }

            if (editor != null) {
                editor.putString("name", c.getString(1));
            }

            if (editor != null) {
                editor.putInt("age", c.getInt(2));
            }

            if (editor != null) {
                editor.putLong("phone", c.getLong(3));
            }

            if (editor != null) {
                editor.putString("mail", c.getString(4));
            }

            if (editor != null) {
                editor.putLong("phone1", c.getLong(5));
            }

            if (editor != null) {
                editor.putString("mail1", c.getString(6));
            }

            if (editor != null) {
                editor.putLong("phone2", c.getLong(7));
            }

            if (editor != null) {
                editor.putString("mail2", c.getString(8));
            }

            if (editor != null) {
                editor.putLong("phone3", c.getLong(9));
            }

            if (editor != null) {
                editor.putString("mail3", c.getString(10));
            }

            if (editor != null) {
                editor.putString("password", c.getString(11));
            }

            if (editor != null) {
                editor.apply();
            }

            View var33 = this.getView();
            EditText var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_name) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box1 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_age) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box2 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_cno) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box3 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_email) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box4 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rnumber_1) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box5 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.remergency_gmail_1) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box6 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rnumber_2) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box7 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.remergency_gmail_2) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box8 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rnumber_3) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box9 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.remergency_gmail_3) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box10 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_pass) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box11 = var34;
            var33 = this.getView();
            var34 = var33 != null ? (EditText)var33.findViewById(id.rreg_re_pass) : null;
            if (var34 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            EditText box12 = var34;
            var32 = this.myActivity;
            if (var32 == null) {
                SplashActivity.throwJavaNpe();
            }

            prefs = var32.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
            if (box1 != null) {
                box1.setText(prefs != null ? prefs.getString("name", "Failed to fetch Data") : null);
            }

            ide = prefs.getInt("userId", 0);
            int one = prefs.getInt("age", 0);
            if (box2 != null) {
                box2.setText(String.valueOf(one));
            }

            long two = prefs.getLong("phone", 0L);
            if (box3 != null) {
                box3.setText(String.valueOf(two));
            }

            if (box4 != null) {
                box4.setText(prefs != null ? prefs.getString("mail", "Failed to fetch Data") : null);
            }

            long three = prefs.getLong("phone1", 0L);
            if (box5 != null) {
                box5.setText(String.valueOf(three));
            }

            long four = prefs.getLong("phone2", 0L);
            if (box9 != null) {
                box9.setText(String.valueOf(four));
            }

            long five = prefs.getLong("phone3", 0L);
            if (box7 != null) {
                box7.setText(String.valueOf(five));
            }

            if (box8 != null) {
                box8.setText(prefs != null ? prefs.getString("mail2", "Failed to fetch Data") : null);
            }

            if (box6 != null) {
                box6.setText(prefs != null ? prefs.getString("mail1", "Failed to fetch Data") : null);
            }

            if (box10 != null) {
                box10.setText(prefs != null ? prefs.getString("mail3", "Failed to fetch Data") : null);
            }

            if (box11 != null) {
                box11.setText(prefs != null ? prefs.getString("password", "Failed to fetch Data") : null);
            }

            if (box12 != null) {
                box12.setText(prefs != null ? prefs.getString("password", "Failed to fetch Data") : null);
            }

            Toast.makeText(this.myActivity, "Update Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.myActivity, "Storing Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }

    public final void deleteUser(@NotNull View v) {
        SplashActivity.checkParameterIsNotNull(v, "v");
        Activity var10000 = this.myActivity;
        if (var10000 == null) {
            SplashActivity.throwJavaNpe();
        }

        SharedPreferences prefs = var10000.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0);
        int ide = prefs.getInt("userId", 0);

        DatabaseHandler helper = new DatabaseHandler(this.myActivity);
        helper.deleteData("" + ide);
        var10000 = this.myActivity;
        if (var10000 == null) {
            SplashActivity.throwJavaNpe();
        }

        Editor editor = var10000.getSharedPreferences(Staticated.INSTANCE.getIS_LOGGEDIN(), 0).edit();
        if (editor != null) {
            editor.putBoolean("loginstatus", false);
        }

        if (editor != null) {
            editor.apply();
        }

        Intent i = new Intent(this.myActivity, RegisterActivity.class);
        this.startActivity(i);
    }

    public SettingsFragment() {
        this.values = new String[this.et.length];
    }


}
