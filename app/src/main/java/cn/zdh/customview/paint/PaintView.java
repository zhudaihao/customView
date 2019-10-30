package cn.zdh.customview.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * paint 的基本使用篇
 */
public class PaintView extends View {
    private Paint paint;

    public PaintView(Context context) {
        super(context);
        initPaint();
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    /**
     * 初始化 画笔paint
     */

    private void initPaint() {
        paint = new Paint();
        //抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);//或者调用  paint.setAntiAlias(true);
        //防抖动
        paint.setFlags(Paint.DITHER_FLAG);//或者调用  paint.setDither(true);
        //设置透明度【0...255】0是完全透明 255是完全不透明；注意先设置颜色再设置才生效，不然会被覆盖
        paint.setAlpha(50);
        //设置画笔颜色
        paint.setColor(Color.RED);//或者  paint.setColor(Color.parseColor("#000000"));
        //画笔的宽度 float值
        paint.setStrokeWidth(20);

        //设置填充样式（一般画圆用的多）
        paint.setStyle(Paint.Style.FILL);//填充满
//        paint.setStyle(Paint.Style.STROKE);//描边（空心）
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);//填充 和空心（和填充类似，当画笔宽度很宽 效果有区别）

        //设置连接点样式
//        paint.setStrokeJoin(Paint.Join.BEVEL);//连接点的外缘与一条直线相接
//        paint.setStrokeJoin(Paint.Join.MITER);//连接处的外缘以锐角相接
        paint.setStrokeJoin(Paint.Join.ROUND);//连接点的外缘以圆弧相接。

        //画笔样式 （落笔 ，收笔 时）
//        paint.setStrokeCap(Paint.Cap.BUTT);//笔划以路径结束，不投射到路径之外。
//        paint.setStrokeCap(Paint.Cap.ROUND);//笔划以半圆的形式向外突出，圆心在上路的尽头。
        paint.setStrokeCap(Paint.Cap.SQUARE);//笔划呈方形，中心在末端

        //设置阴影 (模糊半径（越大越模糊）， x偏移量（阴影离开view的X横向距离），y偏移量（阴影离开view的Y横向距离），阴影的颜色)
        /*测试文字有效果 画线没有*/
        paint.setShadowLayer(10, 13, 13, Color.DKGRAY);

        //设置字体大小
        paint.setTextSize(100);
        //设置字体对齐方式
        paint.setTextAlign(Paint.Align.CENTER);//居中


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取父布局宽高
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        //获取父布局宽高 没有上面精准 第一次测量为0,0
        int width = getWidth();
        int height = getHeight();

        Log.e("zdh", "----------width" + width + "--------------height" + height);
//
//        Log.e("zdh","----------w "+w+"--------------h "+h);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //测量父 布局容器  坐标信息
        Log.e("zdh", "----------changed " + changed +
                "--------------left " + left +
                "--------------top " + top
                + "--------------right " + right +
                "--------------bottom " + bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //画线 （起始点X ，起始点Y，结束点X，结束点X，画笔）
        canvas.drawLine(300, 300, 100, 100, paint);
//        canvas.drawLine(100,100,180,30,paint);

        //参数（文字，起始X坐标，起始Y坐标，画笔）
        canvas.drawText("测试",500,500,paint);

        //参数（圆心X ，圆心Y ，半径，画笔）
        canvas.drawCircle(200,200,100,paint);




    }
}
