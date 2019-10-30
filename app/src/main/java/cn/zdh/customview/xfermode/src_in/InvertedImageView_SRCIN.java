package cn.zdh.customview.xfermode.src_in;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

public class InvertedImageView_SRCIN extends View {

    private Paint paint;
    private Bitmap bitmapTarget,bitmapSRC , bitmapInverse;

    public InvertedImageView_SRCIN(Context context) {
        super(context);
        init();
    }

    public InvertedImageView_SRCIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint=new Paint();

        bitmapTarget =BitmapFactory.decodeResource(getResources(),R.mipmap.invert_shade);
        bitmapSRC=BitmapFactory.decodeResource(getResources(),R.mipmap.xyjy6);

        //倒影图
        Matrix matrix=new Matrix();
        matrix.setScale(1f,-1f);

        bitmapInverse =Bitmap.createBitmap(bitmapSRC,0,0,bitmapSRC.getWidth(),bitmapSRC.getHeight(),matrix,false);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmapSRC,0,0,paint);


        int i = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), null, Canvas.ALL_SAVE_FLAG);

        //改变下笔点----通过平移画布坐标
        canvas.translate(0,bitmapSRC.getHeight());



        //画阴影图
        canvas.drawBitmap(bitmapTarget,0,0,paint);
        //目标图透明部分，显示目标图片透明的；   目标图标不透明的显示 覆盖图片部分；
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        //画覆盖图 --》倒图
        canvas.drawBitmap(bitmapInverse,0,0,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(i);

    }
}
