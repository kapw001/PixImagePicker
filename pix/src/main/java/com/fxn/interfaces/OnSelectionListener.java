package com.fxn.interfaces;

import android.view.View;

import com.fxn.modals.MediaData;

/**
 * Created by akshay on 07/05/18.
 */


public interface OnSelectionListener {
    void onClick(MediaData data, View view, int position);

    void onLongClick(MediaData data, View view, int position);
}
