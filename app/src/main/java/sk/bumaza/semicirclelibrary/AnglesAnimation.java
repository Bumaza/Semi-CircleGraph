package sk.bumaza.semicirclelibrary;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class AnglesAnimation extends Animation {

    private static final int OLD = 0;
    private static final int NEW = 1;

    protected SemiCircleGraphView semiCircleGraphView;

    protected float[] positiveAngles = new float[2];
    protected float[] negativeAngles = new float[2];

    protected boolean isPositive;

    public AnglesAnimation(SemiCircleGraphView semiCircleGraphView, float newPositiveAngle,
                           float newNegativeAngle, boolean isPositive) {
        this.semiCircleGraphView = semiCircleGraphView;
        this.isPositive = isPositive;
        this.positiveAngles[OLD] = semiCircleGraphView.getPositiveAngle();
        this.negativeAngles[OLD] = semiCircleGraphView.getNegativeAngle();
        this.positiveAngles[NEW] = newPositiveAngle;
        this.negativeAngles[NEW] = newNegativeAngle;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float pAngle = positiveAngles[OLD] + ((positiveAngles[NEW] - positiveAngles[OLD]) * interpolatedTime);
        float nAngle = negativeAngles[OLD] + ((negativeAngles[NEW] - negativeAngles[OLD]) * interpolatedTime);

        semiCircleGraphView.setPositiveAndNegative(pAngle, nAngle);
        semiCircleGraphView.requestLayout();
    }
}
