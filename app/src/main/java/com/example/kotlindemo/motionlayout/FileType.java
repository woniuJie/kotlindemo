package com.example.kotlindemo.motionlayout;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

final class FileType {
    public static final String VIDEO = "video";
    public static final String IMAGE = "image";
    public static final String LOG = "log";
    public static final String CRASH = "crash";
    public static final String PLUGIN = "plugin";
    public static final String DOWNLOAD = "download";
    public static final String BLOCK = "block";
    public static final String SOYOUNG = "SoYoung";

    static final String VIDEO_CACHE = "video_cache";
    static final String IMAGE_CACHE = "image_cache";
    static final String OTHER_CACHE = "other_cache";


    @Retention(RetentionPolicy.SOURCE)
    @Target({METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
    @StringDef(value = {
            IMAGE_CACHE, VIDEO_CACHE, OTHER_CACHE,
            IMAGE,
            VIDEO,
            LOG,
            CRASH,
            PLUGIN,
            DOWNLOAD,
            BLOCK,
            SOYOUNG
    })
    public @interface Types {
    }
}
