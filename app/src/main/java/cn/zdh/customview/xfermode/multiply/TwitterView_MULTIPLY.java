package cn.zdh.customview.xfermode.multiply;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 小鸟效果
 */
public class TwitterView_MULTIPLY extends View {
    private Paint paint;
    private Bitmap bitmapDST, bitmapSRC;


    public TwitterView_MULTIPLY(Context context) {
        super(context);
        init();
    }

    public TwitterView_MULTIPLY(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();

        bitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_bg);
        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_light);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //画目标图
        canvas.drawBitmap(bitmapDST, 0, 0, paint);
        /**
         * 原图覆盖目标图 后的效果是---》覆盖图透明的部分 显示白色 ；覆盖图不透明部分 显示目标图的效果；
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));

        //画源图
        canvas.drawBitmap(bitmapSRC, 0, 0, paint);
        paint.setXfermode(null);

        canvas.restoreToCount(i);


    }
}
