package com.abhishekkaudare.womensafety.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.abhishekkaudare.womensafety.R.id;
import com.abhishekkaudare.womensafety.R;

import com.abhishekkaudare.womensafety.activities.SplashActivity;
import com.abhishekkaudare.womensafety.helpers.DatabaseHandler;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class RegisterActivity extends AppCompatActivity {
    @NotNull
    private EditText[] et = new EditText[12];
    @NotNull
    private int[] ids = new int[]{
            R.id.reg_name,
            R.id.reg_age,
            R.id.reg_cno,
            R.id.reg_email,
            R.id.number_1,
            R.id.emergency_gmail_1,
            R.id.number_2,
            R.id.emergency_gmail_2,
            R.id.number_3,
            R.id.emergency_gmail_3,
            R.id.reg_pass,
            R.id.reg_re_pass
    };
    @NotNull
    private String[] values;
    @NotNull
    private DatabaseHandler helper;
    private int id;
    @Nullable
    private EditText pass;
    @Nullable
    private EditText c_pass;
    @Nullable
    private CheckBox cb;
    private HashMap _$_findViewCache;

    @NotNull
    public final EditText[] getEt$app_debug() {
        return this.et;
    }

    public final void setEt$app_debug(@NotNull EditText[] var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.et = var1;
    }

    @NotNull
    public final int[] getIds$app_debug() {
        return this.ids;
    }

    public final void setIds$app_debug(@NotNull int[] var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.ids = var1;
    }

    @NotNull
    public final String[] getValues$app_debug() {
        return this.values;
    }

    public final void setValues$app_debug(@NotNull String[] var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.values = var1;
    }

    @NotNull
    public final DatabaseHandler getHelper$app_debug() {
        return this.helper;
    }

    public final void setHelper$app_debug(@NotNull DatabaseHandler var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.helper = var1;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int var1) {
        this.id = var1;
    }

    @Nullable
    public final EditText getPass() {
        return this.pass;
    }

    public final void setPass(@Nullable EditText var1) {
        this.pass = var1;
    }

    @Nullable
    public final EditText getC_pass() {
        return this.c_pass;
    }

    public final void setC_pass(@Nullable EditText var1) {
        this.c_pass = var1;
    }

    @Nullable
    public final CheckBox getCb() {
        return this.cb;
    }

    public final void setCb(@Nullable CheckBox var1) {
        this.cb = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(1);
        ActionBar var10000 = this.getSupportActionBar();
        if (var10000 == null) {
            SplashActivity.throwJavaNpe();
        }

        var10000.hide();
        this.getWindow().setFlags(1024, 1024);
        this.setContentView(-1300074);
        int i = 0;

        for(int var3 = this.et.length; i < var3; ++i) {
            EditText[] var4 = this.et;
            View var10002 = this.findViewById(this.ids[i]);
            if (var10002 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            var4[i] = (EditText)var10002;
        }

        View var10001 = this.findViewById(-1000107);
        if (var10001 == null) {
            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
        } else {
            this.pass = (EditText)var10001;
            var10001 = this.findViewById(-1000138);
            if (var10001 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            } else {
                this.c_pass = (EditText)var10001;
                var10001 = this.findViewById(-1000297);
                if (var10001 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.CheckBox");
                } else {
                    this.cb = (CheckBox)var10001;
                    CheckBox var5 = this.cb;
                    if (var5 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    var5.setOnCheckedChangeListener((OnCheckedChangeListener)(new OnCheckedChangeListener() {
                        public final void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            EditText var10000;
                            if (!isChecked) {
                                var10000 = RegisterActivity.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
                                var10000 = RegisterActivity.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod((TransformationMethod)PasswordTransformationMethod.getInstance());
                            } else {
                                var10000 = RegisterActivity.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
                                var10000 = RegisterActivity.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
                            }

                        }
                    }));
                    this.id = this.getIntent().getIntExtra("ID", 0);
                }
            }
        }
    }

    public final boolean isValidPhone(@Nullable String phone) {
        if (phone != null) {
            boolean var3 = false;
            boolean var4 = false;
            int var6 = false;
            String passwordPattern = "^[2-9]{2}[0-9]{8}$";
            Regex passwordMatcher = new Regex(passwordPattern);
            return Regex.find$default(passwordMatcher, (CharSequence)phone, 0, 2, (Object)null) != null;
        } else {
            return false;
        }
    }

    public final boolean isEmailValid(@NotNull String $this$isEmailValid) {
        SplashActivity.checkParameterIsNotNull($this$isEmailValid, "$this$isEmailValid");
        return Patterns.EMAIL_ADDRESS.matcher((CharSequence)$this$isEmailValid).matches();
    }

    public final boolean isValidName(@Nullable String name) {
        if (name != null) {
            boolean var3 = false;
            boolean var4 = false;
            int var6 = false;
            String namePattern = "^[\\p{L}\\p{M}]+([\\p{L}\\p{Pd}\\p{Zs}'.]*[\\p{L}\\p{M}])+$|^[\\p{L}\\p{M}]+$";
            Regex nameMatcher = new Regex(namePattern);
            return Regex.find$default(nameMatcher, (CharSequence)name, 0, 2, (Object)null) != null;
        } else {
            return false;
        }
    }

    private final boolean isValidPassword(String password) {
        Pattern pattern = null;
        Matcher matcher = null;
        String specialCharacters = "-@%\\[\\}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>\\]<_";
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[" + specialCharacters + "])(?=\\S+$).{8,20}$";
        Pattern var10000 = Pattern.compile(PASSWORD_REGEX);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "Pattern.compile(PASSWORD_REGEX)");
        pattern = var10000;
        Matcher var6 = pattern.matcher((CharSequence)password);
        SplashActivity.checkExpressionValueIsNotNull(var6, "pattern.matcher(password)");
        matcher = var6;
        return matcher.matches();
    }

    public final void registerUser(@NotNull View v) {
        SplashActivity.checkParameterIsNotNull(v, "v");
        int i = false;
        int i = 0;
        String[] var10000 = this.values;
        EditText var10002 = this.et[i];
        if (var10002 == null) {
            SplashActivity.throwJavaNpe();
        }

        String $this$trim$iv = var10002.getText().toString();
        String[] var13 = var10000;
        int $i$f$trim = false;
        CharSequence $this$trim$iv$iv = (CharSequence)$this$trim$iv;
        int $i$f$trim = false;
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

            var21.setError((CharSequence)"Invalid Name");
        } else {
            int i = i + 1;
            var10000 = this.values;
            var10002 = this.et[i];
            if (var10002 == null) {
                SplashActivity.throwJavaNpe();
            }

            $this$trim$iv = var10002.getText().toString();
            var13 = var10000;
            $i$f$trim = false;
            $this$trim$iv$iv = (CharSequence)$this$trim$iv;
            $i$f$trim = false;
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

            CharSequence var18 = (CharSequence)var20;
            $i$f$trim = false;
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

                var21.setError((CharSequence)"Invalid Age");
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
                    $i$f$trim = false;
                    $this$trim$iv$iv = (CharSequence)$this$trim$iv;
                    $i$f$trim = false;
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

                    var18 = (CharSequence)var20;
                    $i$f$trim = false;
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

                        var21.setError((CharSequence)"Phone Number Cannot be Empty");
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

                        var21.setError((CharSequence)"Invalid Phone Number");
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
                    $i$f$trim = false;
                    $this$trim$iv$iv = (CharSequence)$this$trim$iv;
                    $i$f$trim = false;
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

                    var18 = (CharSequence)var20;
                    $i$f$trim = false;
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

                        var21.setError((CharSequence)"E-mail Id cannot be Empty");
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

                        var21.setError((CharSequence)"E-mail Id Invalid");
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
                    $i$f$trim = false;
                    $this$trim$iv$iv = (CharSequence)$this$trim$iv;
                    $i$f$trim = false;
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

                    var18 = (CharSequence)var20;
                    $i$f$trim = false;
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

                        var21.setError((CharSequence)"Password cannot be Empty");
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

                        var21.setError((CharSequence)"Invalid Password");
                        return;
                    }

                    ++i;
                }

                if (i == this.et.length) {
                    if (SplashActivity.areEqual(this.values[2], this.values[4])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Contact same as your Contact Number", 0).show();
                        var21 = this.et[4];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[4];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[5])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Mail same as your Mail", 0).show();
                        var21 = this.et[5];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[5];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[2], this.values[6])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Contact same as your Contact Number", 0).show();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[4], this.values[6])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Contacts Cannot be same", 0).show();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[6];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Contact matches Emergency Contact 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[7])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Mail same as your Mail", 0).show();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[5], this.values[7])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Mails Cannot be same", 0).show();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[7];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Mail matches Emergency Mail 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[2], this.values[8])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Contact same as your Contact Number", 0).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Contact matches your Contact");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[4], this.values[8])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Contacts Cannot be same", 0).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Contact matches Emergency Contact 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[6], this.values[8])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Contacts Cannot be same", 0).show();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[8];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Contact matches Emergency Contact 2");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[3], this.values[9])) {
                        Toast.makeText((Context)this, (CharSequence)"Emergency Mail same as your Mail", 0).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Emergency Mail matches your Mail");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[5], this.values[9])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Mails Cannot be same", 0).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Mail matches Emergency Mail 1");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[7], this.values[9])) {
                        Toast.makeText((Context)this, (CharSequence)"Two Emergency Mails Cannot be same", 0).show();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.requestFocus();
                        var21 = this.et[9];
                        if (var21 == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        var21.setError((CharSequence)"Mail matches Emergency Mail 2");
                        return;
                    }

                    if (SplashActivity.areEqual(this.values[10], this.values[11])) {
                        Toast.makeText((Context)this, (CharSequence)"Passwords Matched", 0).show();
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

                        var21.setError((CharSequence)"Passwords Do Not Match");
                    }
                }

            }
        }
    }

    public final void addData() {
        Toast.makeText((Context)this, (CharSequence)"Data Insert Success", 1).show();
        DatabaseHandler var10000 = this.helper;
        EditText var10001 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_name);
        SplashActivity.checkExpressionValueIsNotNull(var10001, "reg_name");
        String var20 = var10001.getText().toString();
        EditText var10002 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_age);
        SplashActivity.checkExpressionValueIsNotNull(var10002, "reg_age");
        String var1 = var10002.getText().toString();
        String var4 = var20;
        DatabaseHandler var3 = var10000;
        boolean var2 = false;
        int var5 = Integer.parseInt(var1);
        EditText var10003 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_cno);
        SplashActivity.checkExpressionValueIsNotNull(var10003, "reg_cno");
        var1 = var10003.getText().toString();
        var2 = false;
        long var6 = Long.parseLong(var1);
        EditText var10004 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_email);
        SplashActivity.checkExpressionValueIsNotNull(var10004, "reg_email");
        String var21 = var10004.getText().toString();
        EditText var10005 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_1);
        SplashActivity.checkExpressionValueIsNotNull(var10005, "number_1");
        var1 = var10005.getText().toString();
        String var8 = var21;
        var2 = false;
        long var9 = Long.parseLong(var1);
        EditText var10006 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_1);
        SplashActivity.checkExpressionValueIsNotNull(var10006, "emergency_gmail_1");
        String var17 = var10006.getText().toString();
        EditText var10007 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_2);
        SplashActivity.checkExpressionValueIsNotNull(var10007, "number_2");
        var1 = var10007.getText().toString();
        String var11 = var17;
        var2 = false;
        long var12 = Long.parseLong(var1);
        EditText var10008 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_2);
        SplashActivity.checkExpressionValueIsNotNull(var10008, "emergency_gmail_2");
        String var18 = var10008.getText().toString();
        EditText var10009 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_3);
        SplashActivity.checkExpressionValueIsNotNull(var10009, "number_3");
        var1 = var10009.getText().toString();
        String var14 = var18;
        var2 = false;
        long var15 = Long.parseLong(var1);
        EditText var10010 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_3);
        SplashActivity.checkExpressionValueIsNotNull(var10010, "emergency_gmail_3");
        String var19 = var10010.getText().toString();
        EditText var10011 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_re_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10011, "reg_re_pass");
        var3.insertData(var4, var5, var6, var8, var9, var11, var12, var14, var15, var19, var10011.getText().toString());
        this.clearAllFields();
        this.finish();
    }

    public final void clearAllFields() {
        EditText var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_name);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_name");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_age);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_age");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_cno);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_cno");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_email);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_email");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_pass");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.reg_re_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_re_pass");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_3);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_3");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_2);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_2");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.number_1);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_1");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_2);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_2");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_1);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_1");
        var10000.getText().clear();
        var10000 = (EditText)this._$_findCachedViewById(com.example.me.R.id.emergency_gmail_3);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_3");
        var10000.getText().clear();
    }

    public final void updateUser(@NotNull View view) {
        SplashActivity.checkParameterIsNotNull(view, "view");
    }

    public RegisterActivity() {
        this.values = new String[this.et.length];
        this.helper = new DatabaseHandler((Context)this);
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
}
