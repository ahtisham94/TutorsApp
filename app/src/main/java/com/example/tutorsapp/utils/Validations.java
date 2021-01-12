package com.example.tutorsapp.utils;


import java.util.Collection;

public class Validations {

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isEmpty(Collection collection) {
        return  collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
