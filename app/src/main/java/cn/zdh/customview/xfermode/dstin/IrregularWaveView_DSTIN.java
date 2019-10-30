package cn.zdh.customview.xfermode.dstin;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 水波纹
 */
public class IrregularWaveView_DSTIN extends View {
    private Paint paint;
    private Matrix matrix;
    private int tra = 15;
    private int tag;
    private int wide = 0;
    private Bitmap bitmapFigure, bitmapCover;
    private int dx = 0;


    public IrregularWaveView_DSTIN(Context context) {
        super(context);
        init();

    }

    public IrregularWaveView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        matrix = new Matrix();
        bitmapFigure = BitmapFactory.decodeResource(getResources(), R.mipmap.wav);
        bitmapCover = BitmapFactory.decodeResource(getResources(), R.mipmap.circle_shape);
        wide = bitmapFigure.getWidth();

        startAnim();
    }

    /**
     * 设置动画
     */
    private void startAnim() {
        //动画的启点 和终点（距离）
        ValueAnimator valueAnimator=ValueAnimator.ofInt(0,getWidth());
        //设置时间
        valueAnimator.setDuration(4000);
        //设置动画次数
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        //监听动画变化
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                //获取每次变化值
                dx= (int) animation.getAnimatedValue();

                //不断刷新UI
                postInvalidate();

            }
        });

        //启动动画
        valueAnimator.start();



    }

    /**
     * 方案二实现
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
        canvas.drawBitmap(bitmapCover,0,0,paint);


        //新建个透明层画布
        int layerId = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //画目标图 (bitmap对象，需要绘制的区域，绘制的矩形区域，画笔)，如果需要绘制的区域为null就是绘制整个bitmap区域
        canvas.drawBitmap(bitmapFigure,new Rect(dx,0,dx+bitmapCover.getWidth(),bitmapCover.getHeight())
                ,new Rect(0,0,bitmapCover.getWidth(),bitmapCover.getHeight()),paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //画覆盖图片
        canvas.drawBitmap(bitmapCover,0,0,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(layerId);





    }


    /**
     * 方案一实现
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        tag += tra;
//
//
//        if (tag > (wide - bitmapCover.getWidth()) || tag < 1) {
//            //数据置反
//            tag=0;
//        }
//
//        Log.e("zdh", "-------------" + tag);
//
//        //画个底层圆 显示波浪上部分圆
//        canvas.drawBitmap(bitmapCover, 0, 0, paint);
//
//        //创建一个新的图层
//        int layerId = canvas.saveLayer(0, 0, bitmapCover.getWidth(), bitmapCover.getHeight(), null, Canvas.ALL_SAVE_FLAG);
//
//        matrix.setTranslate(-tag, 0);
//        //目标图片
//        canvas.drawBitmap(bitmapFigure, matrix, paint);
//        //使用xfermode
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//
//
//        //覆盖图片
//        canvas.drawBitmap(bitmapCover, 0, 0, paint);
//        paint.setXfermode(null);
//
//        //合成
//        canvas.restoreToCount(layerId);
//
//
//        postInvalidateDelayed(30);
//
//
//    }
}
