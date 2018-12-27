package kite.amibee.com.netstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

public class LayoutWidthHeightActivity extends AppCompatActivity {
    private static final String TAG = "LayoutWidthHeightActivi";
    RelativeLayout lay_bot_1,lay_bot_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_width_height);

        final RelativeLayout lay_bot_1 = (RelativeLayout) findViewById(R.id.lay_bot_1);
        final ViewTreeObserver observer= lay_bot_1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Log.e(TAG, "init: lay_bot_1 Measuredwidth "+lay_bot_1.getMeasuredWidth() +" height "+lay_bot_1.getMeasuredHeight() );
                        Log.e(TAG, "init: lay_bot_1 width "+lay_bot_1.getWidth() +" height "+lay_bot_1.getHeight() );
                    }
                });





    }


}
