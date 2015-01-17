package androidcourse.com.myPersonalAccountant.graphics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;

import static java.util.Collection.*;

/**
 * TODO: document your custom view class.
 */
public class GraphicsBarView extends View {
//    private String mExampleString; // TODO: use a default from R.string...
//    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
//    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
//    private Drawable mExampleDrawable;
//
////    private TextPaint mTextPaint;
//    private float mTextWidth;

    private Paint _xLabelsPaint;
    private Paint _yLabelsPaint;
    private  Paint _chartPaint;

    private List<Date> _cachedXValues;
    private List<String> _cachedXTexts;
    private List<Float> _cachedXTestPositions;
    private List<Float> _cachedYValues;
    private  float maxY;

    //TODO: No need for these to be private
    private float mTextHeight;
    public  float textPadding = 150;
    private float yAxisOffset;
    private  float axisLabelMargin = 30;
    private float barsMargin = 10;
    private float barWidth;
    private float axisWidth = 8;

    public GraphicsViewAdapter _adapter;

    public GraphicsBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GraphicsBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.GraphicsBarView, defStyle, 0);

        _xLabelsPaint = new Paint();
        _xLabelsPaint.setColor(Color.BLACK);
        //TODO: should be a constant in R
        _xLabelsPaint.setTextSize(30.f);
        _xLabelsPaint.setTextAlign(Paint.Align.LEFT);

        _yLabelsPaint = new Paint();
        _yLabelsPaint.setColor(Color.BLACK);
        _yLabelsPaint.setTextSize(30.f);
        _yLabelsPaint.setTextAlign(Paint.Align.LEFT);

        _chartPaint = new Paint();
        _chartPaint.setColor(Color.BLACK);
        _chartPaint.setStyle(Paint.Style.FILL);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
//        mTextPaint.setTextSize(mExampleDimension);
//        mTextPaint.setColor(mExampleColor);
//        mTextWidth = mTextPaint.measureText(mExampleString);
//
        Paint.FontMetrics fontMetrics = _xLabelsPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        refreshCachedValues(w,h);
    }

    protected void refreshCachedValues(int w, int h){
        _cachedXTexts = new ArrayList<String>();
        _cachedXTestPositions = new ArrayList<Float>();
        _cachedXTexts = new ArrayList<String>();
        _cachedYValues = _adapter.getYValues();
        if(_adapter == null) return;
        _cachedXValues = _adapter.getXValues();
        if(_cachedYValues.size() != _cachedXValues.size()){
            return;
        }
        SimpleDateFormat ft =
                new SimpleDateFormat("dd.MM");
        if(!_cachedXValues.isEmpty()) {
            barWidth = (w - _cachedXValues.size()*barsMargin)/_cachedXValues.size();
            _cachedXTexts.add(ft.format(_cachedXValues.get(0)));
            _cachedXTestPositions.add(0.0f);
            float mTextWidth = _xLabelsPaint.measureText(_cachedXTexts.get(0));
            int numOfLabels =(int)Math.floor(w/(mTextWidth + textPadding));
            int space = _cachedXValues.size() / numOfLabels;
            if (space == 0) space = 1;

            for (int i = space; i<_cachedXValues.size(); i+= space) {
                String date = ft.format(_cachedXValues.get(i));
                _cachedXTexts.add(date);
                _cachedXTestPositions.add(getBarOriginForPosition(i) + barWidth/2);
            }
            maxY = Collections.max(_cachedYValues);
            yAxisOffset = _yLabelsPaint.measureText(Double.toString(maxY)) + axisLabelMargin + _yLabelsPaint.descent() * 2;
        }
    }

    protected Float getBarOriginForPosition(int position){
        return (barWidth + barsMargin) * position + yAxisOffset + axisWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (_adapter == null)
            return;

        //canvas.drawPath(_fillPath, _chartPaint);

        // draw x labels
        float xLabelsY = getHeight() - getPaddingBottom() - _xLabelsPaint.descent();
        float maxHeight = getHeight() - getPaddingTop() - _yLabelsPaint.descent();
        float yLabelsOffset = _yLabelsPaint.descent() * 2;

        for (int i = 0; i < _cachedXTestPositions.size(); ++i) {
            canvas.drawText(_cachedXTexts.get(i),
                    _cachedXTestPositions.get(i), xLabelsY, _xLabelsPaint);

        }

        //draw x axis
        canvas.drawLine(yAxisOffset, xLabelsY - mTextHeight - axisLabelMargin, getWidth(), xLabelsY - mTextHeight -axisLabelMargin, _xLabelsPaint);

        //draw tha max value at the top
        canvas.drawText(Double.toString(maxY),
                        getPaddingTop() + _yLabelsPaint.descent()*2,
                        yLabelsOffset,
                        _yLabelsPaint);

        //draw y axis
        float yAxisOrigin = xLabelsY - axisLabelMargin - axisWidth;
        canvas.drawLine(yAxisOffset, yAxisOrigin, yAxisOffset, 0, _yLabelsPaint);

        float barXPosition = yAxisOffset + axisWidth;
        for (int i = 0; i < _cachedYValues.size(); ++i) {
            float t =yAxisOrigin - _cachedYValues.get(i)*(yAxisOrigin)/maxY;
            canvas.drawRect(barXPosition, yAxisOrigin - _cachedYValues.get(i)*(yAxisOrigin)/maxY, barXPosition+barWidth, yAxisOrigin, _chartPaint);
            barXPosition += barWidth + barsMargin;

        }
    }

    public float getTextPadding() {
        return textPadding;
    }

    public void setTextPadding(float textPadding) {
        this.textPadding = textPadding;
    }

    public GraphicsViewAdapter get_adapter() {
        return _adapter;
    }

    public void set_adapter(GraphicsViewAdapter _adapter) {
        this._adapter = _adapter;
    }

    public GraphicsBarView(Context context) {
        super(context);
        init(null, 0);
    }

}
