package kite.amibee.com.netstore;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by manigandan on 5/10/17.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space,lr,tb,top,bottom;//lr - left/right ,tb top/bottom
    private static final String TAG = "SpacesItemDecoration";
    public SpacesItemDecoration(int space, int lr, int top, int bottom) {
        this.space = space;
        this.lr = lr;
        this.top = top;
        this.bottom=bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = lr;
        outRect.right = lr;
        outRect.bottom = bottom;
        outRect.top = top;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = top;
//            Log.e(TAG, "getItemOffsets: if "+tb );
        } else {
            outRect.top = top;
//            Log.e(TAG, "getItemOffsets: else "+tb );
        }
    }
}
