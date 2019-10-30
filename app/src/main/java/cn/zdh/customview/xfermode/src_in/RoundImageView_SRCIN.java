package cn.zdh.customview.xfermode.src_in;

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
 * 圆角图片
 */
public class RoundImageView_SRCIN extends View {
    private Paint paint;
    private Bitmap bitmapTarget, bitmapSRC;

    public RoundImageView_SRCIN(Context context) {
        super(context);
        init();
    }

    public RoundImageView_SRCIN(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        paint = new Paint();

        bitmapTarget = BitmapFactory.decodeResource(getResources(), R.mipmap.shade);
        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy6);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);


        //目标图
        canvas.drawBitmap(bitmapTarget, 0, 0, paint);
        /**
         *  目标图透明部分，显示目标图片透明的；   目标图标不透明的显示 覆盖图片部分；
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        //覆盖图
        canvas.drawBitmap(bitmapSRC, 0, 0, paint);
        paint.setXfermode(null);

        canvas.restoreToCount(i);

    }
}
