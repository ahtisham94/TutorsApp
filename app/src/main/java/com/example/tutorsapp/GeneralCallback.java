package com.example.tutorsapp;

public interface GeneralCallback<T, V> {

    void successCall(T t);
    void errorCall(V v);
}
