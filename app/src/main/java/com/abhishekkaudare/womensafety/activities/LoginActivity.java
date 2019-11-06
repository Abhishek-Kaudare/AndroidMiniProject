package com.abhishekkaudare.womensafety.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishekkaudare.womensafety.R;
import com.abhishekkaudare.womensafety.helpers.DatabaseHandler;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    private DatabaseHandler helper = new DatabaseHandler(this);
    @Nullable
    private EditText et1;
    @Nullable
    private EditText et2;
    @Nullable
    private CheckBox cb;
    @Nullable
    private CheckBox cb2;
    @Nullable
    private Button login;
    @Nullable
    private Button nregister;

    @NotNull
    public final DatabaseHandler getHelperApp() {
        return this.helper;
    }

    public final void setHelperApp(@NotNull DatabaseHandler var1) {
        SplashActivity.checkParameterIsNotNull(var1, "<set-?>");
        this.helper = var1;
    }

    @Nullable
    public final EditText getEt1() {
        return this.et1;
    }

    public final void setEt1(@Nullable EditText var1) {
        this.et1 = var1;
    }

    @Nullable
    public final EditText getEt2() {
        return this.et2;
    }

    public final void setEt2(@Nullable EditText var1) {
        this.et2 = var1;
    }

    @Nullable
    public final CheckBox getCb() {
        return this.cb;
    }

    public final void setCb(@Nullable CheckBox var1) {
        this.cb = var1;
    }

    @Nullable
    public final CheckBox getCb2() {
        return this.cb2;
    }

    public final void setCb2(@Nullable CheckBox var1) {
        this.cb2 = var1;
    }

    @Nullable
    public final Button getLogin() {
        return this.login;
    }

    public final void setLogin(@Nullable Button var1) {
        this.login = var1;
    }

    @Nullable
    public final Button getNregister() {
        return this.nregister;
    }

    public final void setNregister(@Nullable Button var1) {
        this.nregister = var1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide(); // Hide The Title Bar
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_login);
        et1 = findViewById(R.id.login_username);
        et2 = findViewById(R.id.login_password);
        cb2 = findViewById(R.id.cb2);

        SharedPreferences prefs = this.getSharedPreferences(SplashActivity.Staticated.INSTANCE.getIS_LOGGEDIN(), Context.MODE_PRIVATE);
        Boolean remember = prefs != null ? prefs.getBoolean("remember", false) : null;
        if (remember == null) {
            SplashActivity.throwJavaNpe();
        }

        if (remember) {
            EditText temp = this.et1;
            if (temp != null) {
                temp.setText(prefs != null ? prefs.getString("mail", "") : null);
            }

            temp = this.et2;
            if (temp != null) {
                temp.setText(prefs != null ? prefs.getString("password", "") : null);
            }

            Toast.makeText(this, "Remember False", Toast.LENGTH_SHORT).show();
        }

        final SharedPreferences.Editor editor = this.getSharedPreferences(SplashActivity.Staticated.INSTANCE.getIS_LOGGEDIN(), Context.MODE_PRIVATE).edit();
        cb2 = this.findViewById(R.id.cb2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton btn, boolean isChecked) {
                SharedPreferences.Editor temp1;
                if (isChecked) {
                    temp1 = editor;
                    if (temp1 != null) {
                        temp1.putBoolean("remember", true);
                    }

                    Toast.makeText(LoginActivity.this, "True", Toast.LENGTH_LONG).show();
                    editor.apply();
                } else {
                    temp1 = editor;
                    if (temp1 != null) {
                        temp1.putBoolean("remember", false);
                    }

                    editor.apply();
                    Toast.makeText(LoginActivity.this, "False", Toast.LENGTH_LONG).show();
                }

            }
        });


        cb = this.findViewById(R.id.cb1);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton btn, boolean isChecked) {
                EditText temp2;
                if (!isChecked) {
                    temp2 = LoginActivity.this.getEt2();
                    if (temp2 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    temp2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    temp2 = LoginActivity.this.getEt2();
                    if (temp2 == null) {
                        SplashActivity.throwJavaNpe();
                    }

                    temp2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        login = this.findViewById(R.id.login_button);
        nregister = this.findViewById(R.id.register_button);



        if (login != null) {
            login.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View it) {
                    EditText temp = LoginActivity.this.getEt1();
                    String uname = String.valueOf(temp != null ? temp.getText() : null);
                    temp = LoginActivity.this.getEt2();
                    String pass = String.valueOf(temp != null ? temp.getText() : null);


                    CharSequence temp2 = uname;
                    boolean bol = false;
                    if (temp2.length() == 0) {
                        temp = LoginActivity.this.getEt1();
                        if (temp != null) {
                            temp.requestFocus();
                        }

                        temp = LoginActivity.this.getEt1();
                        if (temp == null) {
                            SplashActivity.throwJavaNpe();
                        }

                        temp.setError("Empty User");
                    } else {
                        temp2 = pass;
                        bol = false;
                        if (temp2.length() == 0) {
                            temp = LoginActivity.this.getEt2();
                            if (temp == null) {
                                SplashActivity.throwJavaNpe();
                            }

                            temp.requestFocus();
                            temp = LoginActivity.this.getEt2();
                            if (temp == null) {
                                SplashActivity.throwJavaNpe();
                            }

                            temp.setError("Empty Password");
                        } else {
                            SQLiteDatabase dab = LoginActivity.this.getHelperApp().getWritableDatabase();
                            String qery = "select password from my_table where email = '" + uname + "';";
                            @SuppressLint("Recycle") Cursor ce = dab.rawQuery(qery, null);
                            boolean rese = ce.moveToFirst();
                            if (rese) {
                                String db_pass = ce.getString(0);
                                if (areEqual(db_pass, pass)) {
                                    LoginActivity.this.finish();
                                    SQLiteDatabase db = LoginActivity.this.getHelperApp().getWritableDatabase();
                                    String qry = "select * from my_table where email = '" + uname + "';";
                                    @SuppressLint("Recycle") Cursor c = db.rawQuery(qry, null);
                                    boolean res = c.moveToFirst();
                                    if (res) {
                                        SharedPreferences.Editor temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putBoolean("loginstatus", true);
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putInt("userId", c.getInt(0));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("name", c.getString(1));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putInt("age", c.getInt(2));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putLong("phone", c.getLong(3));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("mail", c.getString(4));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putLong("phone1", c.getLong(5));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("mail1", c.getString(6));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putLong("phone2", c.getLong(7));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("mail2", c.getString(8));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putLong("phone3", c.getLong(9));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("mail3", c.getString(10));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.putString("password", c.getString(11));
                                        }

                                        temp3 = editor;
                                        if (temp3 != null) {
                                            temp3.apply();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Storing Unsuccessful", Toast.LENGTH_SHORT).show();
                                    }

                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    LoginActivity.this.startActivity(i);
                                } else {
                                    temp = LoginActivity.this.getEt2();
                                    if (temp != null) {
                                        temp.requestFocus();
                                    }

                                    temp = LoginActivity.this.getEt2();
                                    if (temp == null) {
                                        SplashActivity.throwJavaNpe();
                                    }

                                    temp.setError("Invalid Password");
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
            });
        }


        if (nregister != null) {
            nregister.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(i);
                }
            });
        }
    }

    public static boolean areEqual(Object first, Object second) {
        return Objects.equals(first, second);
    }
}
