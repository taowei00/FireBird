package com.crazy.http.annoation;

import androidx.annotation.IntDef;

import static com.crazy.http.annoation.RequestMethod.Get;
import static com.crazy.http.annoation.RequestMethod.Post;

@IntDef({Get, Post})
public @interface RequestMethod {
    int Get = 1;
    int Post = 2;
}
