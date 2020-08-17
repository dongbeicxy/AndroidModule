package com.xxx.xxx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述:圆角的RelativeLayout
 * @创建时间 :2020/3/28 10:18
 */
public class RoundRectLayout extends RelativeLayout {

    private Path mPath;
    //圆角半径
    private int mRadius;
    //背景颜色
    private int background;

    private int mWidth;
    private int mHeight;
    private int mLastRadius;

    public static final int MODE_NONE = 0;
    public static final int MODE_ALL = 1;
    public static final int MODE_LEFT = 2;
    public static final int MODE_TOP = 3;
    public static final int MODE_RIGHT = 4;
    public static final int MODE_BOTTOM = 5;

    private int mRoundMode = MODE_ALL;

    public RoundRectLayout(Context context) {
        super(context);

        init();
    }

    public RoundRectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundRectLayout);
        mRadius = a.getInt(R.styleable.RoundRectLayout_radiu, 24);
        background = a.getColor(R.styleable.RoundRectLayout_background_color, Color.parseColor("#CCCCCC"));
        init();
    }

    private void init() {
        setBackgroundDrawable(new ColorDrawable(background));

        mPath = new Path();
        mPath.setFillType(Path.FillType.EVEN_ODD);

        //setCornerRadius(30);
    }

    /**
     * 设置是否圆角裁边
     *
     * @param roundMode
     */
    public void setRoundMode(int roundMode) {
        mRoundMode = roundMode;
    }

    /**
     * 设置圆角半径
     *
     * @param radius
     */
    public void setCornerRadius(int radius) {
        mRadius = radius;
    }

    private void checkPathChanged() {

        if (getWidth() == mWidth && getHeight() == mHeight && mLastRadius == mRadius) {
            return;
        }

        mWidth = getWidth();
        mHeight = getHeight();
        mLastRadius = mRadius;

        mPath.reset();

        switch (mRoundMode) {
            case MODE_ALL:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight), mRadius, mRadius, Path.Direction.CW);
                break;
            case MODE_LEFT:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{mRadius, mRadius, 0, 0, 0, 0, mRadius, mRadius},
                        Path.Direction.CW);
                break;
            case MODE_TOP:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{mRadius, mRadius, mRadius, mRadius, 0, 0, 0, 0},
                        Path.Direction.CW);
                break;
            case MODE_RIGHT:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{0, 0, mRadius, mRadius, mRadius, mRadius, 0, 0},
                        Path.Direction.CW);
                break;
            case MODE_BOTTOM:
                mPath.addRoundRect(new RectF(0, 0, mWidth, mHeight),
                        new float[]{0, 0, 0, 0, mRadius, mRadius, mRadius, mRadius},
                        Path.Direction.CW);
                break;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        if (mRoundMode != MODE_NONE) {
            int saveCount = canvas.save();
            checkPathChanged();
            if (Build.VERSION.SDK_INT >= 26) {
                canvas.clipPath(mPath);
            } else {
                canvas.clipPath(mPath, Region.Op.REPLACE);
            }
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
        } else {
            super.draw(canvas);
        }
    }
}

