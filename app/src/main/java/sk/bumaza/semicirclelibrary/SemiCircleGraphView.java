package sk.bumaza.semicirclelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import sk.bumaza.semicirclegraph.R;

public class SemiCircleGraphView extends View {

    public static final float CIRCLE_DEGREE = 360.00f;
    public static final float STRAIGHT_ANGLE = 180.00f;

    protected Paint mPaintPositive, mPaintNegative, mPaintPrimary;
    protected RectF mRect;
    protected float graphDegree = 270;
    protected float radius, size = 15;
    protected float indicatorStartAngle, indicatorEndAngle;

    private float positiveAngle, negativeAngle;
    private float startPositiveAngle = 135, startNegativeAngle = 45;


    private int positiveColor = 0xFF74BD4C;
    private int negativeColor = 0xFFFF5050;
    private int backgroundColor = 0xFF343333;

    public SemiCircleGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mRect = new RectF(convertToPx(5), convertToPx(5), convertToPx(165), convertToPx(165));

        mPaintPositive = new Paint();
        mPaintPositive.setAntiAlias(true);
        mPaintPositive.setStyle(Paint.Style.STROKE);
        mPaintPositive.setStrokeWidth(5);
        mPaintPositive.setColor(positiveColor);
        mPaintPositive.setStrokeCap(Paint.Cap.ROUND);

        mPaintNegative = new Paint();
        mPaintNegative.setAntiAlias(true);
        mPaintNegative.setStyle(Paint.Style.STROKE);
        mPaintNegative.setStrokeWidth(5);
        mPaintNegative.setColor(negativeColor);
        mPaintNegative.setStrokeCap(Paint.Cap.ROUND);

        mPaintPrimary = new Paint();
        mPaintPrimary.setAntiAlias(true);
        mPaintPrimary.setStyle(Paint.Style.STROKE);
        mPaintPrimary.setStrokeWidth(3);
        DashPathEffect dashPath = new DashPathEffect(new float[]{5,5}, (float)1.0);
        mPaintPrimary.setPathEffect(dashPath);
        mPaintPrimary.setColor(backgroundColor);


        radius = mRect.width() / 2;
        float degreeOffset= (CIRCLE_DEGREE-graphDegree) / 2;
        indicatorStartAngle = graphDegree - degreeOffset;
        indicatorEndAngle = graphDegree + degreeOffset;
    }

    public SemiCircleGraphView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getVisibility() == GONE) return;
        super.onDraw(canvas);

        canvas.drawArc(mRect, startPositiveAngle, graphDegree, false, mPaintPrimary);
        canvas.drawArc(mRect, startPositiveAngle, positiveAngle, false, mPaintPositive);
        canvas.drawArc(mRect, startNegativeAngle, negativeAngle, false, mPaintNegative);

        drawLineIndicator(canvas, positiveAngle, indicatorStartAngle);
        drawLineIndicator(canvas, negativeAngle-.5f, indicatorEndAngle);

    }

    public void drawLineIndicator(Canvas canvas, float angle, float startIndicatorAngle){
        float x = (float) ((radius-size/2) * Math.cos((angle-startIndicatorAngle) * Math.PI / STRAIGHT_ANGLE) + mRect.centerX());
        float y = (float) ((radius-size/2) * Math.sin((angle-startIndicatorAngle) * Math.PI / STRAIGHT_ANGLE) + mRect.centerY());

        float x2 = (float) ((radius+size/2) * Math.cos((angle-startIndicatorAngle) * Math.PI / STRAIGHT_ANGLE) + mRect.centerX());
        float y2 = (float) ((radius+size/2) * Math.sin((angle-startIndicatorAngle) * Math.PI / STRAIGHT_ANGLE) + mRect.centerY());

        canvas.drawLine(x, y, x2, y2, mPaintPrimary);
    }

    public void setPositiveAndNegative(float positiveAngle, float negativeAngle){
        setPositiveAngle(positiveAngle);
        setNegativeAngle(negativeAngle);
    }

    public void setStartAngles(float startPositiveAngle, float startNegativeAngle){
        setStartPositiveAngle(startPositiveAngle);
        setStartNegativeAngle(startNegativeAngle);
    }

    public float getPositiveAngle() {
        return positiveAngle;
    }

    public void setPositiveAngle(float positiveAngle) {
        this.positiveAngle = positiveAngle;
    }

    public float getNegativeAngle() {
        return negativeAngle;
    }

    public void setNegativeAngle(float negativeAngle) {
        this.negativeAngle = negativeAngle;
    }

    public float getStartPositiveAngle() {
        return startPositiveAngle;
    }

    public void setStartPositiveAngle(float startPositiveAngle) {
        this.startPositiveAngle = startPositiveAngle;
    }

    public float getStartNegativeAngle() {
        return startNegativeAngle;
    }

    public void setStartNegativeAngle(float startNegativeAngle) {
        this.startNegativeAngle = startNegativeAngle;
    }

    public int convertToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
