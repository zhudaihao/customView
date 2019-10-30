package cn.zdh.customview.xfermode.dstin;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 心跳图
 */
public class HeartMap_DSTIN extends View {
    private Paint paint;
    private Bitmap bitmapDST, bitmapSRC,bitmap;
    private int dx = 0;


    public HeartMap_DSTIN(Context context) {
        super(context);
        init();
    }

    public HeartMap_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        paint = new Paint();

        //目标图
        bitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.heartmap);
        //创建一张透明的 和目标图一样大小的 覆盖图(返回指定宽度和高度的可变位图)
        bitmapSRC=Bitmap.createBitmap(bitmapDST.getWidth(), bitmapDST.getHeight(),Bitmap.Config.ARGB_8888);


        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tp);
        //启动动画
        startAnim();
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, bitmapDST.getWidth());
        valueAnimator.setDuration(6000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();

                //不断刷新UI
                postInvalidate();
            }
        });
        //启动动画
       valueAnimator.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        通过覆盖图对象bitmapSRC获取他的画布canvas
        Canvas c=new Canvas(bitmapSRC);
        //清空bitmap
        c.drawColor(Color.RED,PorterDuff.Mode.CLEAR);
        paint.setColor(Color.RED);
        //画矩形
        c.drawRect(new RectF(bitmapDST.getWidth()-dx,0,bitmapDST.getWidth(),bitmapDST.getHeight()),paint);


        //创建一个新的画布
        int id = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //画目标图
        canvas.drawBitmap(bitmapDST,0,0,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //画覆盖图
        canvas.drawBitmap(bitmapSRC,0,0,paint);
        paint.setXfermode(null);

        Log.d("zdh","------------"+bitmapSRC.getWidth());
        //合成画布
        canvas.restoreToCount(id);







    }
}
