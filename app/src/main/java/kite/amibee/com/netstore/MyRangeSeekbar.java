package kite.amibee.com.netstore;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

/**
 * Created by owais.ali on 7/12/2016.
 */
public class MyRangeSeekbar extends CrystalRangeSeekbar {

    public MyRangeSeekbar(Context context) {
        super(context);
    }

    public MyRangeSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRangeSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected float getCornerRadius(TypedArray typedArray) {
        return super.getCornerRadius(typedArray);
    }

    @Override
    protected float getMinValue(TypedArray typedArray) {
        return 0f;
    }

    @Override
    protected float getMaxValue(TypedArray typedArray) {
        return 1000f;
    }

    @Override
    protected float getMaxStartValue(TypedArray typedArray) {
        return 100f;
    }

    @Override
    protected float getMinStartValue(TypedArray typedArray) {
        return 20f;
    }



    @Override
    protected float getSteps(TypedArray typedArray) {
        return super.getSteps(typedArray);
    }

    @Override
    protected float getGap(TypedArray typedArray) {
        return super.getGap(typedArray);
    }

    @Override
    protected float getFixedGap(TypedArray typedArray) {
        return super.getFixedGap(typedArray);
    }

    @Override
    protected int getBarColor(TypedArray typedArray) {
        return Color.parseColor("#4285F4");
    }

    @Override
    protected int getBarHighlightColor(TypedArray typedArray) {
        return Color.parseColor("#4285F4");
    }

    @Override
    protected int getLeftThumbColor(TypedArray typedArray) {
        return Color.parseColor("#058EB7");
    }

    @Override
    protected int getRightThumbColor(TypedArray typedArray) {
        return Color.parseColor("#058EB7");
    }

    @Override
    protected int getLeftThumbColorPressed(TypedArray typedArray) {
        return Color.parseColor("#046887");
    }

    @Override
    protected int getRightThumbColorPressed(TypedArray typedArray) {
        return Color.parseColor("#046887");
    }

    @Override
    protected Drawable getLeftDrawable(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.circle_blue);
    }

    @Override
    protected Drawable getRightDrawable(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.circle_blue);
    }

    @Override
    protected Drawable getLeftDrawablePressed(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.circle_blue);
    }

    @Override
    protected Drawable getRightDrawablePressed(TypedArray typedArray) {
        return ContextCompat.getDrawable(getContext(), R.drawable.circle_blue);
    }

    @Override
    protected int getDataType(TypedArray typedArray) {
        return super.getDataType(typedArray);
    }
}
