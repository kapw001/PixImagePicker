package com.fxn.utility;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.fxn.modals.MediaData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by akshay on 06/04/18.
 */

public class ImageFetcher extends AsyncTask<Cursor, Void, ArrayList<MediaData>> {
    private ArrayList<MediaData> LIST = new ArrayList<>();
    private Context context;

    public ImageFetcher(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList<MediaData> doInBackground(Cursor... cursors) {
        Cursor cursor = cursors[0];
        if (cursor != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
            int date = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED);
            int data = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int contentUrl = cursor.getColumnIndex(MediaStore.Files.FileColumns._ID);
            int type = cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE);

            String header = "";
            int limit = 100;
            if (cursor.getCount() < 100) {
                limit = cursor.getCount();
            }
            cursor.move(limit - 1);
            for (int i = limit; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                Uri curl = Uri.withAppendedPath(Constants.URI, "" + cursor.getInt(contentUrl));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(cursor.getLong(date) * 1000L);
                String dateDifference = Utility.getDateDifference(context, calendar);
                // Log.e("date -", ")-->  " + cursor.getLong(date) + "   " + dateFormat2.format(cursor.getLong(date)*1000L));
                if (!header.equalsIgnoreCase(dateDifference)) {
                    header = dateDifference;
                    LIST.add(new MediaData(dateDifference, "", "", dateFormat.format(calendar.getTime()), cursor.getInt(type)));
                }
                LIST.add(new MediaData(header, curl.toString(), cursor.getString(data), dateFormat.format(calendar.getTime()), cursor.getInt(type)));
            }
            cursor.close();
        }
        return LIST;
    }

}
