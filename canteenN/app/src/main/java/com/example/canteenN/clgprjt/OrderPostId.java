package com.example.canteenN.clgprjt;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class OrderPostId {

    @Exclude
    public String OrderPostId;

    public <T extends OrderPostId> T withId(@NonNull final String id1) {
        this.OrderPostId = id1;
        return (T) this;
    }
}
