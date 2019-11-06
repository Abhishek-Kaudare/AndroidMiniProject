package com.abhishekkaudare.womensafety.activities;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;

import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.helpers.DatabaseHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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



    @Nullable
    public final EditText getC_pass() {
        return this.c_pass;
    }



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar var10000 = this.getSupportActionBar();
        if (var10000 == null) {
            SplashActivity.throwJavaNpe();
        }

        var10000.hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_register);
        int i = 0;

        for(int var3 = this.et.length; i < var3; ++i) {
            EditText[] var4 = this.et;
            View var10002 = this.findViewById(this.ids[i]);
            if (var10002 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            }

            var4[i] = (EditText)var10002;
        }

        View var10001 = this.findViewById(R.id.reg_pass);
        if (var10001 == null) {
            throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
        } else {
            this.pass = (EditText)var10001;
            var10001 = this.findViewById(R.id.reg_re_pass);
            if (var10001 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.widget.EditText");
            } else {
                this.c_pass = (EditText)var10001;
                var10001 = this.findViewById(R.id.cb1);
                if (var10001 == null) {
                    throw new ClassCastException("null cannot be cast to non-null type android.widget.CheckBox");
                } else {
                    CheckBox var5 = (CheckBox) var10001;

                    var5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                        public final void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            EditText var10000;
                            if (!isChecked) {
                                var10000 = RegisterActivity.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                var10000 = RegisterActivity.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            } else {
                                var10000 = RegisterActivity.this.getPass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                var10000 = RegisterActivity.this.getC_pass();
                                if (var10000 == null) {
                                    SplashActivity.throwJavaNpe();
                                }

                                var10000.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            }

                        }
                    });
                    this.id = this.getIntent().getIntExtra("ID", 0);
                }
            }
        }
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

    private boolean isValidPassword(@NonNull String password) {
        Pattern pattern;
        Matcher matcher;
        String specialCharacters = "-@%\\[}+'!/#$^?:;,\\(\"\\)~`.*=&\\{>]<_";
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_REGEX);

        matcher = pattern.matcher(password);
        return true;
    }

    public final void registerUser(@NotNull View v) {
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
        boolean match$iv$iv;
        while(startIndex$iv$iv <= endIndex$iv$iv) {
            index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            it = $this$trim$iv$iv.charAt(index$iv$iv);

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
                        Toast.makeText(this, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Emergency Mail same as your Mail", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Emergency Mail same as your Mail", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Emergency Contact same as your Contact Number", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Contacts Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Emergency Mail same as your Mail", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Two Emergency Mails Cannot be same", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "Passwords Matched", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Data Insert Success", Toast.LENGTH_LONG).show();
        DatabaseHandler var10000 = this.helper;
        EditText var10001 = this.findViewById(R.id.reg_name);
        SplashActivity.checkExpressionValueIsNotNull(var10001, "reg_name");
        String var20 = var10001.getText().toString();
        EditText var10002 = this.findViewById(R.id.reg_age);
        SplashActivity.checkExpressionValueIsNotNull(var10002, "reg_age");
        String var1 = var10002.getText().toString();
        int var5 = Integer.parseInt(var1);
        EditText var10003 = this.findViewById(R.id.reg_cno);
        SplashActivity.checkExpressionValueIsNotNull(var10003, "reg_cno");
        var1 = var10003.getText().toString();
        long var6 = Long.parseLong(var1);
        EditText var10004 = this.findViewById(R.id.reg_email);
        SplashActivity.checkExpressionValueIsNotNull(var10004, "reg_email");
        String var21 = var10004.getText().toString();
        EditText var10005 = this.findViewById(R.id.number_1);
        SplashActivity.checkExpressionValueIsNotNull(var10005, "number_1");
        var1 = var10005.getText().toString();
        long var9 = Long.parseLong(var1);
        EditText var10006 = this.findViewById(R.id.emergency_gmail_1);
        SplashActivity.checkExpressionValueIsNotNull(var10006, "emergency_gmail_1");
        String var17 = var10006.getText().toString();
        EditText var10007 = this.findViewById(R.id.number_2);
        SplashActivity.checkExpressionValueIsNotNull(var10007, "number_2");
        var1 = var10007.getText().toString();
        long var12 = Long.parseLong(var1);
        EditText var10008 = this.findViewById(R.id.emergency_gmail_2);
        SplashActivity.checkExpressionValueIsNotNull(var10008, "emergency_gmail_2");
        String var18 = var10008.getText().toString();
        EditText var10009 = this.findViewById(R.id.number_3);
        SplashActivity.checkExpressionValueIsNotNull(var10009, "number_3");
        var1 = var10009.getText().toString();
        long var15 = Long.parseLong(var1);
        EditText var10010 = this.findViewById(R.id.emergency_gmail_3);
        SplashActivity.checkExpressionValueIsNotNull(var10010, "emergency_gmail_3");
        String var19 = var10010.getText().toString();
        EditText var10011 = this.findViewById(R.id.reg_re_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10011, "reg_re_pass");
        var10000.insertData(var20, var5, var6, var21, var9, var17, var12, var18, var15, var19, var10011.getText().toString());
        this.clearAllFields();
        this.finish();
    }

    public final void clearAllFields() {
        EditText var10000 = this.findViewById(R.id.reg_name);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_name");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.reg_age);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_age");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.reg_cno);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_cno");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.reg_email);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_email");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.reg_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_pass");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.reg_re_pass);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "reg_re_pass");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.number_3);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_3");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.number_2);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_2");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.number_1);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "number_1");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.emergency_gmail_2);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_2");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.emergency_gmail_1);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_1");
        var10000.getText().clear();
        var10000 = this.findViewById(R.id.emergency_gmail_3);
        SplashActivity.checkExpressionValueIsNotNull(var10000, "emergency_gmail_3");
        var10000.getText().clear();
    }

    public final void updateUser(@NotNull View view) {
        SplashActivity.checkParameterIsNotNull(view, "view");
    }

    public RegisterActivity() {
        this.values = new String[this.et.length];
        this.helper = new DatabaseHandler(this);
    }


}
