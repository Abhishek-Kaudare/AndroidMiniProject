package com.abhishekkaudare.womensafety.helpers;

import org.jetbrains.annotations.Nullable;

public final class UserInfo {
    private int id;
    @Nullable
    private String name;
    private int age;
    private long phone;
    @Nullable
    private String email;
    private long epn1;
    @Nullable
    private String em1;
    private long epn2;
    @Nullable
    private String em2;
    private long epn3;
    @Nullable
    private String em3;
    @Nullable
    private String password;

    public final int getId() {
        return this.id;
    }

    public final void setId(int var1) {
        this.id = var1;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final void setName(@Nullable String var1) {
        this.name = var1;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int var1) {
        this.age = var1;
    }

    public final long getPhone() {
        return this.phone;
    }

    public final void setPhone(long var1) {
        this.phone = var1;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(@Nullable String var1) {
        this.email = var1;
    }

    public final long getEpn1() {
        return this.epn1;
    }

    public final void setEpn1(long var1) {
        this.epn1 = var1;
    }

    @Nullable
    public final String getEm1() {
        return this.em1;
    }

    public final void setEm1(@Nullable String var1) {
        this.em1 = var1;
    }

    public final long getEpn2() {
        return this.epn2;
    }

    public final void setEpn2(long var1) {
        this.epn2 = var1;
    }

    @Nullable
    public final String getEm2() {
        return this.em2;
    }

    public final void setEm2(@Nullable String var1) {
        this.em2 = var1;
    }

    public final long getEpn3() {
        return this.epn3;
    }

    public final void setEpn3(long var1) {
        this.epn3 = var1;
    }

    @Nullable
    public final String getEm3() {
        return this.em3;
    }

    public final void setEm3(@Nullable String var1) {
        this.em3 = var1;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(@Nullable String var1) {
        this.password = var1;
    }
}
