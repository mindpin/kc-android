package com.mindpin.kc_android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.Canvas;import android.graphics.Color;import android.graphics.LinearGradient;import android.graphics.Paint;import android.graphics.Path;import android.graphics.RectF;import android.graphics.Shader;import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.mindpin.kc_android.R;

/**
 * Created by dd on 14-9-10.
 */


public class ArrowBubbleLayout extends LinearLayout
{
    private static final float DEFAULT_POINTER_HEIGHT = 25f;
    private static final float DEFAULT_POINTER_WIDTH = 50f;
    private static final float DEFAULT_CORNER_RADIUS = 15f;
    private static final int DEFAULT_BUBBLE_COLOR = Color.GRAY;
    private static final int DEFAULT_BUBBLE_OUTLINE_COLOR = 0xffb7bcbc;
    private static final int DEFAULT_SHADOW_COLOR = Color.GRAY;
    private static final float DEFAULT_SHADOW_RADIUS = 5f;
    private static final float DEFAULT_SHADOW_OFFSET_X = 5f;
    private static final float DEFAULT_SHADOW_OFFSET_Y = 5f;
    private static final int DEFAULT_BUBBLE_GRADIENT_TOP_COLOR = Color.WHITE;
    private static final int DEFAULT_BUBBLE_GRADIENT_BOTTOM_COLOR = 0xffb7bcbc;

    private Path bubblePath;
    private float pointerHeight = DEFAULT_POINTER_HEIGHT;
    private float pointerWidth = DEFAULT_POINTER_WIDTH;
    private float cornerRadius = DEFAULT_CORNER_RADIUS;
    private int bubbleColor = DEFAULT_BUBBLE_COLOR;
    private int bubbleOutlineColor = DEFAULT_BUBBLE_OUTLINE_COLOR;
    private int shadowColor = DEFAULT_SHADOW_COLOR;
    private Paint bubblePathPaint;
    private Paint bubblePathOutlinePaint;
    private int bubbleGradientTopColor = DEFAULT_BUBBLE_GRADIENT_TOP_COLOR;
    private int bubbleGradientBottomColor = DEFAULT_BUBBLE_GRADIENT_BOTTOM_COLOR;
    private float shadowRadius = DEFAULT_SHADOW_RADIUS;
    private float shadowOffsetX = DEFAULT_SHADOW_OFFSET_X;
    private float shadowOffsetY = DEFAULT_SHADOW_OFFSET_Y;

    public ArrowBubbleLayout(Context context)
    {
        super(context);
        initPaint();
    }

    public ArrowBubbleLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initAttributes(context, attrs);
        initPaint();
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        this.bubblePath = calculateBubblePath(l, t, r, b);
        // The following was for a gradient inside the bubble that currently isn't necessary.
        // However, it was causing the background color not to be drawn at all when padding and/or margin
        // was specified.
//        bubblePathPaint.setShader(calculateGradient(l, t, r, b));
    }

    @Override
    protected void onDraw (Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawPath(bubblePath, bubblePathPaint);
        canvas.drawPath(bubblePath, bubblePathOutlinePaint);
    }

    private void initAttributes(Context context, AttributeSet attrs)
    {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ArrowBubbleLayout);
        this.pointerHeight = a.getDimension(R.styleable.ArrowBubbleLayout_bubble_pointerHeight, DEFAULT_POINTER_HEIGHT);
        this.pointerWidth = a.getDimension(R.styleable.ArrowBubbleLayout_bubble_pointerWidth, DEFAULT_POINTER_WIDTH);
        this.cornerRadius = a.getDimension(R.styleable.ArrowBubbleLayout_bubble_cornerRadius, DEFAULT_CORNER_RADIUS);
        this.bubbleColor = a.getInt(R.styleable.ArrowBubbleLayout_bubble_color, DEFAULT_BUBBLE_COLOR);
        this.bubbleOutlineColor = a.getInt(R.styleable.ArrowBubbleLayout_bubble_outlineColor, DEFAULT_BUBBLE_OUTLINE_COLOR);
        this.shadowColor = a.getInt(R.styleable.ArrowBubbleLayout_bubble_shadowColor, DEFAULT_SHADOW_COLOR);
        this.shadowRadius = a.getFloat(R.styleable.ArrowBubbleLayout_bubble_shadowRadius, DEFAULT_SHADOW_RADIUS);
        this.shadowOffsetX = a.getFloat(R.styleable.ArrowBubbleLayout_bubble_shadowOffsetX, DEFAULT_SHADOW_OFFSET_X);
        this.shadowOffsetY = a.getFloat(R.styleable.ArrowBubbleLayout_bubble_shadowOffsetY, DEFAULT_SHADOW_OFFSET_Y);
        this.bubbleGradientTopColor = a.getInt(R.styleable.ArrowBubbleLayout_bubble_gradientTopColor, DEFAULT_BUBBLE_GRADIENT_TOP_COLOR);
        this.bubbleGradientBottomColor = a.getInt(R.styleable.ArrowBubbleLayout_bubble_gradientBottomColor, DEFAULT_BUBBLE_GRADIENT_BOTTOM_COLOR);
        a.recycle();
    }

    private void initPaint()
    {
        // Setup Paint for filling the path.
        this.bubblePathPaint = new Paint();
        bubblePathPaint.setStyle(Paint.Style.FILL);
        bubblePathPaint.setColor(bubbleColor);
//        bubblePathPaint.setShadowLayer(shadowRadius, shadowOffsetX, shadowOffsetY, shadowColor);

        // Setup Paint for stroking the path.
        this.bubblePathOutlinePaint = new Paint();
        bubblePathOutlinePaint.setStyle(Paint.Style.STROKE);
        bubblePathOutlinePaint.setColor(bubbleOutlineColor);
        bubblePathOutlinePaint.setStrokeWidth(2.0f);
    }

    private Path calculateBubblePath(int l, int t, int r, int b)
    {
        Path path = new Path();


//        float viewBottom = b - t;
//        float bubbleBottom = viewBottom - pointerHeight;
//        float viewWidth = r - l;
//        float viewCenterX = viewWidth / 2.0f;
//        float halfPointerWidth = pointerWidth / 2.0f;
//        RectF cornerRect = new RectF(0, 0, cornerRadius, cornerRadius);

        float viewBottom = b - t;
//        float bubbleBottom = viewBottom;
        float viewWidth = r - l;// - pointerHeight;
        float viewLeft = 0;
        float viewCenterY = viewBottom / 2.0f;
        float halfPointerWidth = pointerWidth / 2.0f;
        System.out.println(cornerRadius);
        RectF cornerRect = new RectF(0, 0, cornerRadius, cornerRadius);

        // If there is a practical pointer, then draw the bubble and pointer.
        if ((pointerHeight > 0f) && (pointerWidth > 0f))
        {
//              bottom position
//            // Start at the point of the triangle at the bottom and draw the line up to the bottom of the round rect.
//            path.moveTo(viewCenterX, viewBottom);
//            path.lineTo(viewCenterX + halfPointerWidth, bubbleBottom);
//
//            // Move to the bottom right corner of the bubble rect and draw the corner.
//            path.lineTo(viewWidth - cornerRadius, bubbleBottom);
//            cornerRect.offsetTo(viewWidth - cornerRadius, bubbleBottom - cornerRadius);
//            path.arcTo(cornerRect, 90f, -90f);
//
//            // Move up to the top right corner and draw the corner.
//            path.lineTo(viewWidth, cornerRadius);
//            cornerRect.offsetTo(viewWidth - cornerRadius, 0);
//            path.arcTo(cornerRect, 0f, -90f);
//
//            // Move up to the top left corner and draw the corner.
//            path.lineTo(cornerRadius, 0);
//            cornerRect.offsetTo(0, 0);
//            path.arcTo(cornerRect, 270f, -90f);
//
//            // Move up to the bottom left corner and draw the corner.
//            path.lineTo(0, bubbleBottom - cornerRadius);
//            cornerRect.offsetTo(0, bubbleBottom - cornerRadius);
//            path.arcTo(cornerRect, 180f, -90f);
//
//            // Finish the bottom and the left hand side of the pointer.
//            path.lineTo(viewCenterX - halfPointerWidth, bubbleBottom);
//            path.lineTo(viewCenterX, viewBottom);
//            path.close();

//            left position

            //左箭头顶端向箭头根部
            path.addRoundRect(new RectF(pointerHeight, 0, viewWidth - pointerHeight, viewBottom), cornerRadius, cornerRadius, Path.Direction.CW);
            path.moveTo(viewLeft, viewCenterY);
            path.lineTo(viewLeft + pointerHeight, viewCenterY + halfPointerWidth);
            path.lineTo(viewLeft + pointerHeight, viewCenterY - halfPointerWidth);
            path.lineTo(viewLeft, viewCenterY);
            path.close();

//            //左箭头顶端向箭头根部
//            path.moveTo(viewLeft, viewCenterY);
//            path.lineTo(viewLeft + pointerHeight, viewCenterY + halfPointerWidth);
//
//            //左箭头根部向左下 锯齿
//            path.lineTo(viewLeft + pointerHeight, viewBottom - cornerRadius);
//            cornerRect.offsetTo(viewLeft + pointerHeight + cornerRadius, viewBottom);
//            path.arcTo(cornerRect, 0f, -90f);
//
//            //左下向右下 没有圆角
//            path.lineTo(viewWidth - cornerRadius, viewBottom);
//            cornerRect.offsetTo(viewWidth, viewBottom - cornerRadius);
//            path.arcTo(cornerRect, 270f, -90f);
//
//            //右下向右上 正常
//            path.lineTo(viewWidth, 0 + cornerRadius);
//            cornerRect.offsetTo(viewWidth - cornerRadius, 0);
//            path.arcTo(cornerRect, 0f, -90f);
//
//            //右上向左上
//            path.lineTo(viewLeft + pointerHeight + cornerRadius, 0);
//            cornerRect.offsetTo(viewLeft + pointerHeight, 0 + cornerRadius);
//            path.arcTo(cornerRect, 90f, -90f);
//
//            // Finish the bottom and the left hand side of the pointer.
//            path.lineTo(viewLeft + pointerHeight, viewCenterY - halfPointerWidth);
//            path.lineTo(viewLeft, viewCenterY);
//            path.close();
        }

        // No pointer.  Just make a round rect for the bubble.
        else
        {
            path.addRoundRect(new RectF(0, 0, viewWidth, viewBottom), cornerRadius, cornerRadius, Path.Direction.CW);
        }

        return path;
    }

    private LinearGradient calculateGradient(int l, int t, int r, int b)
    {
        return new LinearGradient(0, 0, 0, b - t, bubbleGradientTopColor, bubbleGradientBottomColor, Shader.TileMode.CLAMP);
    }

}