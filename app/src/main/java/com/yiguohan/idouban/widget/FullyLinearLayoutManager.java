package com.yiguohan.idouban.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by yiguohan.
 */

public class FullyLinearLayoutManager extends LinearLayoutManager {
    public FullyLinearLayoutManager(Context context) {
        super(context);
    }

    public FullyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }
}
