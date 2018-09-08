package com.fxn.utility;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by akshay on 06/04/18.
 */

public class Constants {
    public static final int sScrollbarAnimDuration = 300;
    public static String[] PROJECTION = new String[]{
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.DATE_MODIFIED,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Images.Media.DATE_TAKEN

    };
    public static String SELECTION = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
            + " OR "
            + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
    public static Uri URI = MediaStore.Files.getContentUri("external");
    public static String ORDERBY = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

}
