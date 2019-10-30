package cn.zdh.customview.xfermode.dstin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.zdh.customview.R;

/**
 * 倒影效果
 */
public class InvertedImageView_DSTIN extends View {
    private Paint paint;
    private Bitmap bitmapDST, bitmapSRC, bitmapRevert;

    public InvertedImageView_DSTIN(Context context) {
        super(context);
        init();
    }

    public InvertedImageView_DSTIN(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        bitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy6);
        bitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.invert_shade);

        Matrix matrix = new Matrix();
        //缩放（缩放X值,缩放Y值）1为原图大小，注意如果x为负数图片横向内容左右对换，如果Y为负数上下内容对换，
        matrix.setScale(1f, -1f);
        //生成倒影图
        bitmapRevert = Bitmap.createBitmap(bitmapDST, 0, 0, bitmapDST.getWidth(), bitmapDST.getHeight(), matrix, false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        画目标图
        canvas.drawBitmap(bitmapDST, 0, 0, paint);
        Log.e("zdh", "------------ 目标  " + canvas.getSaveCount());

        //新建一个新的透明画布
        int layoutId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);


        //向下平移覆盖图的高度 ,改变画倒影的下笔点的Y值
        canvas.translate(0, bitmapSRC.getHeight());

//        画倒影图
        canvas.drawBitmap(bitmapRevert, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        Log.e("zdh", "------------ 画倒影  " + canvas.getSaveCount());


        //画覆盖图
        canvas.drawBitmap(bitmapSRC, 0, 0, paint);
        paint.setXfermode(null);

        Log.e("zdh", "------------ 画覆盖图 " + canvas.getSaveCount());

        //所有的画布整合到一起(合成所有画布的图片信息，保留id层画布，其他层移除)
        canvas.restoreToCount(layoutId);

        Log.e("zdh", "------------ 切换 " + canvas.getSaveCount());



    }


}
