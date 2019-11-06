package com.abhishekkaudare.womensafety.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.abhishekkaudare.womensafety.activities.SplashActivity;

import java.util.Arrays;

import org.jetbrains.annotations.NotNull;

public final class DatabaseHandler extends SQLiteOpenHelper {
    @NotNull
    private static final String DATABASE_NAME = "MYDatabase.db";
    @NotNull
    private static final String TABLE_NAME = "my_table";
    @NotNull
    private static final String ID = "ID";
    @NotNull
    private static final String NAME = "NAME";
    @NotNull
    private static final String AGE = "AGE";
    @NotNull
    private static final String PHONE = "PHONE";
    @NotNull
    private static final String EMAIL = "EMAIL";
    @NotNull
    private static final String EPN1 = "EPN1";
    @NotNull
    private static final String EM1 = "EM1";
    @NotNull
    private static final String EPN2 = "EPN2";
    @NotNull
    private static final String EM2 = "EM2";
    @NotNull
    private static final String EPN3 = "EPN3";
    @NotNull
    private static final String EM3 = "EM3";
    @NotNull
    private static final String PASSWORD = "PASSWORD";
    public static final DatabaseHandler.Companion Companion = new DatabaseHandler.Companion(null);

    public void onCreate(@NotNull SQLiteDatabase db) {
        checkParameterIsNotNull(db, "db");
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER,PHONE LONG, EMAIL TEXT,EPN1 LONG, EM1 TEXT,EPN2 LONG, EM2 TEXT,EPN3 LONG, EM3 TEXT, PASSWORD TEXT)");
    }

    public void onUpgrade(@NotNull SQLiteDatabase db, int oldVersion, int newVersion) {
        checkParameterIsNotNull(db, "db");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public final void insertData(@NotNull String name, int age, long phone, @NotNull String email, long epn1, @NotNull String em1, long epn2, @NotNull String em2, long epn3, @NotNull String em3, @NotNull String password) {
        checkParameterIsNotNull(name, "name");
        checkParameterIsNotNull(email, "email");
        checkParameterIsNotNull(em1, "em1");
        checkParameterIsNotNull(em2, "em2");
        checkParameterIsNotNull(em3, "em3");
        checkParameterIsNotNull(password, "password");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PHONE, phone);
        contentValues.put(EMAIL, email);
        contentValues.put(EPN1, epn1);
        contentValues.put(EM1, em1);
        contentValues.put(EPN2, epn2);
        contentValues.put(EM2, em2);
        contentValues.put(EPN3, epn3);
        contentValues.put(EM3, em3);
        contentValues.put(PASSWORD, password);
        db.insert(TABLE_NAME, null, contentValues);
    }

    @NotNull
    public final UserInfo getParticularUserData(@NotNull String id) {
        checkParameterIsNotNull(id, "id");
        UserInfo userInfo = new UserInfo();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    do {
                        userInfo.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        userInfo.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                        userInfo.setAge(cursor.getInt(cursor.getColumnIndex(AGE)));
                        userInfo.setPhone(cursor.getLong(cursor.getColumnIndex(PHONE)));
                        userInfo.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                        userInfo.setEpn1(cursor.getLong(cursor.getColumnIndex(EPN1)));
                        userInfo.setEm1(cursor.getString(cursor.getColumnIndex(EM1)));
                        userInfo.setEpn2(cursor.getLong(cursor.getColumnIndex(EPN2)));
                        userInfo.setEm2(cursor.getString(cursor.getColumnIndex(EM2)));
                        userInfo.setEpn3(cursor.getLong(cursor.getColumnIndex(EPN3)));
                        userInfo.setEm3(cursor.getString(cursor.getColumnIndex(EM3)));
                        userInfo.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                    } while(cursor.moveToNext());
                }
            }
        } finally {
            cursor.close();
        }

        return userInfo;
    }

    public final boolean updateData(@NotNull String id, @NotNull String name, int age, long phone, @NotNull String email, long epn1, @NotNull String em1, long epn2, @NotNull String em2, long epn3, @NotNull String em3, @NotNull String password) {
        checkParameterIsNotNull(id, "id");
        checkParameterIsNotNull(name, "name");
        checkParameterIsNotNull(email, "email");
        checkParameterIsNotNull(em1, "em1");
        checkParameterIsNotNull(em2, "em2");
        checkParameterIsNotNull(em3, "em3");
        checkParameterIsNotNull(password, "password");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PHONE, phone);
        contentValues.put(EMAIL, email);
        contentValues.put(EPN1, epn1);
        contentValues.put(EM1, em1);
        contentValues.put(EPN2, epn2);
        contentValues.put(EM2, em2);
        contentValues.put(EPN3, epn3);
        contentValues.put(EM3, em3);
        contentValues.put(PASSWORD, password);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public final int deleteData(@NotNull String id) {
        checkParameterIsNotNull(id, "id");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    public DatabaseHandler(@NotNull Context context) {

        super(context, DATABASE_NAME, null, 1);
        checkParameterIsNotNull(context, "context");
    }

    public static final class Companion {
        @NotNull
        public final String getDATABASE_NAME() {
            return DatabaseHandler.DATABASE_NAME;
        }

        @NotNull
        public final String getTABLE_NAME() {
            return DatabaseHandler.TABLE_NAME;
        }

        @NotNull
        public final String getID() {
            return DatabaseHandler.ID;
        }

        @NotNull
        public final String getNAME() {
            return DatabaseHandler.NAME;
        }

        @NotNull
        public final String getAGE() {
            return DatabaseHandler.AGE;
        }

        @NotNull
        public final String getPHONE() {
            return DatabaseHandler.PHONE;
        }

        @NotNull
        public final String getEMAIL() {
            return DatabaseHandler.EMAIL;
        }

        @NotNull
        public final String getEPN1() {
            return DatabaseHandler.EPN1;
        }

        @NotNull
        public final String getEM1() {
            return DatabaseHandler.EM1;
        }

        @NotNull
        public final String getEPN2() {
            return DatabaseHandler.EPN2;
        }

        @NotNull
        public final String getEM2() {
            return DatabaseHandler.EM2;
        }

        @NotNull
        public final String getEPN3() {
            return DatabaseHandler.EPN3;
        }

        @NotNull
        public final String getEM3() {
            return DatabaseHandler.EM3;
        }

        @NotNull
        public final String getPASSWORD() {
            return DatabaseHandler.PASSWORD;
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    public static final class Staticated {

        private static String IS_LOGGEDIN;
        public static final DatabaseHandler.Staticated INSTANCE;

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
            DatabaseHandler.Staticated var0 = new DatabaseHandler.Staticated();
            INSTANCE = var0;
            IS_LOGGEDIN = "Login Check";
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
}