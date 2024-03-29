package com.xxx.xxx.widget.page_menu;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;


import android.util.AttributeSet;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.xxx.xxx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 xzb
 * @描述: 圆点指示器
 * @创建时间 :2020/3/30 10:14
 */
public class CircleIndicator extends View {

    private int mIndicatorRadius, mIndicatorMargin, mIndicatorBackground, mIndicatorSelectBackground, mCurItemPosition;
    private float mCurItemPositionOffset;
    private Gravity mIndicatorLayoutGravity;
    private Mode mIndicatorMode;
    private ShapeHolder moveItem;
    private List<ShapeHolder> mTabItems;
    private ViewPager mViewPager;

    //default
    private static final int DEFULT_INDICATOR_RADIUS = 10;
    private static final int DEFAULT_INDICATOR_MARGIN = 40;
    private static final int DEFAULT_INDICATOR_BACKGROUND = Color.BLUE;
    private static final int DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();
    private static final int DEFAULT_INDICATOR_SELECTED_BACKGROUND = Color.RED;
    private static final int DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();

    public enum Gravity {
        LEFT, CENTER, RIGHT;
    }

    public enum Mode {
        INSIDE, OUTSIDE, SOLO;
    }

    public CircleIndicator(Context context) {
        this(context, null);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        mTabItems = new ArrayList<>();
        handleTypeAttrs(context, attrs);
    }

    /**
     * 获取资源属性
     *
     * @param context
     * @param attrs
     */
    private void handleTypeAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
        mIndicatorRadius = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_radius, DEFULT_INDICATOR_RADIUS);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_margin, DEFAULT_INDICATOR_MARGIN);
        mIndicatorBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_background, DEFAULT_INDICATOR_BACKGROUND);
        mIndicatorSelectBackground = typedArray.getColor(R.styleable.CircleIndicator_ci_selected_background, DEFAULT_INDICATOR_SELECTED_BACKGROUND);
        int layoutGravity = typedArray.getInt(R.styleable.CircleIndicator_ci_gravity, DEFAULT_INDICATOR_LAYOUT_GRAVITY);
        mIndicatorLayoutGravity = Gravity.values()[layoutGravity];
        int layoutMode = typedArray.getInt(R.styleable.CircleIndicator_ci_mode, DEFAULT_INDICATOR_MODE);
        mIndicatorMode = Mode.values()[layoutMode];
        typedArray.recycle();
    }

    /**
     * 注入ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        createTabItems();
        createMoveItems();
        setViewPagerListener();
    }

    /**
     * 创建小圆点个数,依赖于ViewPager
     */
    private void createTabItems() {
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            OvalShape circle = new OvalShape();
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder holder = new ShapeHolder(drawable);

            Paint paint = drawable.getPaint();
            paint.setColor(mIndicatorBackground);
            paint.setAntiAlias(true);

            holder.setPaint(paint);
            mTabItems.add(holder);
        }
    }

    /**
     * 创建移动小圆点
     */
    private void createMoveItems() {
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
        moveItem = new ShapeHolder(drawable);

        Paint paint = drawable.getPaint();
        paint.setColor(mIndicatorSelectBackground);
        paint.setAntiAlias(true);

        switch (mIndicatorMode) {
            case INSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                break;
            case OUTSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                break;
            case SOLO:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                break;
            default:
                break;
        }

        moveItem.setPaint(paint);
    }

    /**
     * 监听ViewPager滑动
     */
    private void setViewPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mIndicatorMode != Mode.SOLO) {
                    trigger(position, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mIndicatorMode == Mode.SOLO) {
                    trigger(position, 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void trigger(int position, float positionOffset) {
        this.mCurItemPosition = position;
        this.mCurItemPositionOffset = positionOffset;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = getWidth();
        int height = getHeight();

        layoutItem(width, height);
        layoutMoveItem(mCurItemPosition, mCurItemPositionOffset);
    }

    /**
     * 计算每个小圆点位置
     */
    private void layoutItem(int width, int height) {
        if (null == mTabItems) {
            throw new IllegalArgumentException("forget to create items?");
        }

        float heightY = height * 0.5f;
        int startPosition = startDrawPosition(width);

        for (int i = 0; i < mTabItems.size(); i++) {
            ShapeHolder holder = mTabItems.get(i);
            holder.resizeShape(2 * mIndicatorRadius, 2 * mIndicatorRadius);
            holder.setY(heightY - mIndicatorRadius);

            int x = startPosition + (2 * mIndicatorRadius + mIndicatorMargin) * i;
            holder.setX(x);
        }
    }

    /**
     * 设置移动小圆点位置
     *
     * @param curItemPosition
     * @param curItemPositionOffset
     */
    private void layoutMoveItem(int curItemPosition, float curItemPositionOffset) {
        if (null == moveItem) {
            throw new IllegalArgumentException("forget to create moveitem?");
        }

        if (0 == mTabItems.size()) {
            return;
        }

        ShapeHolder holder = mTabItems.get(curItemPosition);
        moveItem.resizeShape(holder.getWidth(), holder.getHeight());

        float x = holder.getX() + (mIndicatorMargin + mIndicatorRadius * 2) * curItemPositionOffset;
        moveItem.setX(x);
        moveItem.setY(holder.getY());
    }

    /**
     * 设置小圆点起始位置
     *
     * @param width
     */
    private int startDrawPosition(int width) {
        if (mIndicatorLayoutGravity == Gravity.LEFT) {
            return 0;
        }

        int tabItemLength = mTabItems.size() * (2 * mIndicatorRadius + mIndicatorMargin) - mIndicatorMargin;
        if (width < tabItemLength) {
            return 0;
        }

        if (mIndicatorLayoutGravity == Gravity.CENTER) {
            return (width - tabItemLength) / 2;
        }

        return width - tabItemLength;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layer = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null);
        } else {
            layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        }

        for (ShapeHolder holer : mTabItems) {
            drawItem(canvas, holer);
        }

        if (null != moveItem) {
            drawItem(canvas, moveItem);
        }

        canvas.restoreToCount(layer);
    }

    /**
     * 开始绘画圆点
     *
     * @param canvas
     * @param holer
     */
    private void drawItem(Canvas canvas, ShapeHolder holer) {
        canvas.save();
        canvas.translate(holer.getX(), holer.getY());
        holer.getShape().draw(canvas);
        canvas.restore();
    }

    /**
     * 暴露接口，可代码修改参数
     */
    public void setIndicatorMode(Mode indicatorMode) {
        this.mIndicatorMode = indicatorMode;
    }

    public void setIndicatorMargin(int indicatorMargin) {
        this.mIndicatorMargin = indicatorMargin;
    }

    public void setIndicatorRadius(int indicatorRadius) {
        this.mIndicatorRadius = indicatorRadius;
    }

    public void setIndicatorBackground(int indicatorBackground) {
        this.mIndicatorBackground = indicatorBackground;
    }

    public void setIndicatorLayoutGravity(Gravity indicatorLayoutGravity) {
        this.mIndicatorLayoutGravity = indicatorLayoutGravity;
    }

    public void setIndicatorSelectBackground(int indicatorSelectBackground) {
        this.mIndicatorSelectBackground = indicatorSelectBackground;
    }
}

