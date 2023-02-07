package com.example.kotlindemo.motionlayout;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class LocalFile {
    private static Context context;

    public static void setContext(@NonNull Application application) {
        context = application;
    }


    @Nullable
    public static File getDirFile(@FileType.Types String type, boolean _private) {
        try {
            if (!_private && (Environment.isExternalStorageEmulated()
                    || Environment.isExternalStorageRemovable() && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))) {
                // type dir and file
                return context.getExternalFilesDir(type);
            } else {
                // type dir
                File typeDir = new File(context.getFilesDir(), type);
                if (!typeDir.exists()) {
                    boolean mkDirRet = typeDir.mkdirs();
                    if (!mkDirRet) {
                        return null;
                    }
                }
                // file
                return typeDir;
            }
        } catch (Exception e) {
        }

        return null;

    }

    @Nullable
    public static File newFile(@FileType.Types String type, String subType, String name, boolean _private) {
        return newPersistFile(type, subType, name, _private);
    }

    @Nullable
    public static File log(String subType, String name, boolean _private) {
        return newPersistFile(FileType.LOG, subType, name, _private);
    }

    @Nullable
    public static File soyoung(String subType, String name, boolean _private) {
        return newPersistFile(FileType.SOYOUNG, subType, name, _private);
    }

    @Nullable
    public static File logDir(String subType, boolean _private) {
        return getPersistDir(FileType.LOG, subType, _private);
    }

    @Nullable
    public static File crash(String subType, String name) {
        return newPersistFile(FileType.CRASH, subType, name, false);
    }

    @Nullable
    public static File crashDir(String subType) {
        return getPersistDir(FileType.CRASH, subType, false);
    }

    @Nullable
    public static File plugin(String subType, String name) {
        return newPersistFile(FileType.PLUGIN, subType, name, false);
    }

    @Nullable
    public static File pluginDir(String subType) {
        return getPersistDir(FileType.PLUGIN, subType, false);
    }

    @Nullable
    public static File download(String subType, String name, boolean _private) {
        return newPersistFile(FileType.DOWNLOAD, subType, name, _private);
    }

    @Nullable
    public static File downloadDir(String subType, boolean _private) {
        return getPersistDir(FileType.DOWNLOAD, subType, _private);
    }

    @Nullable
    public static File block(String subType, String name, boolean _private) {
        return newPersistFile(FileType.BLOCK, subType, name, _private);
    }

    @Nullable
    public static File blockDir(String subType, boolean _private) {
        return getPersistDir(FileType.BLOCK, subType, _private);
    }

    @Nullable
    public static File image(String subType, @NonNull String name, boolean _private) {
        return newPersistFile(FileType.IMAGE, subType, name, _private);
    }

    @Nullable
    public static File imageDir(@NonNull String subType, boolean _private) {
        return getPersistDir(FileType.IMAGE, subType, _private);
    }

    @Nullable
    public static File imageCache(String subType, @NonNull String name, boolean _private) {
        return newCacheFile(FileType.IMAGE_CACHE, subType, name, _private);
    }

    @Nullable
    public static File imageCacheDir(@NonNull String subType, boolean _private) {
        return getCacheDir(FileType.IMAGE_CACHE, subType, _private);
    }

    @Nullable
    public static File video(String subType, @NonNull String name, boolean _private) {
        return newPersistFile(FileType.VIDEO, subType, name, _private);
    }

    @Nullable
    public static File videoDir(@NonNull String subType, boolean _private) {
        return getPersistDir(FileType.VIDEO, subType, _private);
    }

    @Nullable
    public static File videoCache(String subType, @NonNull String name, boolean _private) {
        return newCacheFile(FileType.VIDEO_CACHE, subType, name, _private);
    }

    @Nullable
    public static File videoCacheDir(@NonNull String subType, boolean _private) {
        return getCacheDir(FileType.VIDEO_CACHE, subType, _private);
    }

    @Nullable
    public static File otherCache(String subType, @NonNull String name, boolean _private) {
        return newCacheFile(FileType.OTHER_CACHE, subType, name, _private);
    }

    @Nullable
    public static File otherCacheDir(@NonNull String subType, boolean _private) {
        return getCacheDir(FileType.OTHER_CACHE, subType, _private);
    }

    @Nullable
    private static File newPersistFile(@FileType.Types String type, @NonNull String subType, String name, boolean _private) {
        try {
            File typeDir = getPersistDir(type, subType, _private);
            if (typeDir == null) {
                return null;
            }
            // file
            File file = new File(typeDir, name);
            if (file.exists() || file.createNewFile()) {
                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    private static File getPersistDir(@FileType.Types String type, @NonNull String subType, boolean _private) {
        try {
            if (!_private && (Environment.isExternalStorageEmulated()
                    || Environment.isExternalStorageRemovable() && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))) {
                // type dir and file
                return context.getExternalFilesDir(type + File.separator + subType);
            } else {
                // type dir
                File typeDir = new File(context.getFilesDir(), type + File.separator + subType);
                if (!typeDir.exists()) {
                    boolean mkDirRet = typeDir.mkdirs();
                    if (!mkDirRet) {
                        return null;
                    }
                }
                // file
                return typeDir;
            }
        } catch (Exception e) {
        }

        return null;
    }

    @Nullable
    private static File newCacheFile(@FileType.Types String type, @NonNull String subType, String name, boolean _private) {
        try {
            // type dir
            File typeDir = getCacheDir(type, subType, _private);
            if (typeDir == null) {
                return null;
            }
            // file
            File file = new File(typeDir, name);
            if (file.exists() || file.createNewFile()) {
                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    private static File getCacheDir(@FileType.Types String type, @NonNull String subType, boolean _private) {
        try {
            // root dir
            File rootDir;
            if (!_private
                    && (Environment.isExternalStorageEmulated()
                    || Environment.isExternalStorageRemovable() && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))) {
                rootDir = context.getExternalCacheDir();
            } else {
                rootDir = context.getCacheDir();
            }
            // type dir
            File typeDir = new File(rootDir, type + File.separator + subType);
            if (!typeDir.exists()) {
                boolean mkDirRet = typeDir.mkdirs();
                if (!mkDirRet) {
                    return null;
                }
            }
            // file
            return typeDir;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static long statistics() {
        return statisticsCache() + statisticsPersist();
    }

    public static long statisticsCache() {
        return dirSize(context.getExternalCacheDir()) + dirSize(context.getCacheDir());
    }

    public static long statisticsPersist() {
        return dirSize(context.getExternalFilesDir(null)) + dirSize(context.getFilesDir());
    }

    public static long statisticsPublic() {
        return dirSize(context.getExternalCacheDir()) + dirSize(context.getExternalFilesDir(null));
    }

    public static long statisticsPrivate() {
        return dirSize(context.getCacheDir()) + dirSize(context.getFilesDir());
    }

    private static long dirSize(@NonNull File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    int sum = 0;
                    for (File item : files) {
                        sum += dirSize(item);
                    }
                    return sum;
                }
            } else {
                return file.length();
            }
        }

        return 0;
    }
}
