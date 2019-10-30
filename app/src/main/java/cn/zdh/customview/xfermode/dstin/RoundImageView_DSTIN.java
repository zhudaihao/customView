package cn.zdh.customview.xfermode.dstin;

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
 * 圆角图片效果
 */
public class RoundImageView_DSTIN extends View {
    private Paint paint;
    private Bitmap bitmapDST, bitmapSRC;

    public RoundImageView_DSTIN(Context context) {
        super(context);
        init();
    }

    public RoundImageView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy6);
        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.shade);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = 50;
        int top = 50;

        //新建图层
        int layerId = canvas.saveLayer(new RectF(left, top, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //目标
        canvas.drawBitmap(bitmapDST, left, top, paint);
        /**
         * 设xfermodel 配置xfermode 模式 DST_IN  保留覆盖像素的目标像素，丢弃其余的源和目标像素 （通过覆盖图实现 目标图多种样式 图片，比如圆角，星星。。）
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        //覆盖
        canvas.drawBitmap(bitmapSRC, left, top, paint);
        paint.setXfermode(null);

        //合成所有画布信息，保留参数id画布移除其他层画布
        canvas.restoreToCount(layerId);

    }
}
