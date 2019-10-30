package cn.zdh.customview.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.zdh.customview.R;

public class MaskFilter extends View {
    private Paint paint;
    private Bitmap bitmap;
    private RectF rectF;

    public MaskFilter(Context context) {
        super(context);
        init();
    }


    public MaskFilter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tp);
        rectF = new RectF(0, 0, bitmap.getWidth() + 200, bitmap.getHeight() + 200);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //模糊遮罩滤镜
//        setBlurMaskFilter(canvas);
        //浮雕遮罩滤镜
//        setEmbossMaskFilter(canvas);



        //修改像素ARGC值
        setColorMaskFilter(canvas);

    }

    /**
     * 修改像素ARGC值
     * @param canvas
     */
    private void setColorMaskFilter(Canvas canvas) {
        /**
         * 平移运算 ----加法
         */
//        float[] flosts={
//                1,0,0,0,0,//R红
//                0,1,0,0,100,//G绿
//                0,0,1,0,0,//B蓝
//                0,0,0,1,0,//A透明度
//        };

        /**
         * 反相效果 ---底片效果
         */
//        float[] flosts={
//                -1,0,0,0,255,//R
//                0,-1,0,0,255,//G
//                0,0,-1,0,255,//B
//                0,0,0,1,0,//A
//        };


        /**
         * 缩放运算 --乘法 --颜色增强
         */
//        float[] flosts={
//                1.2f,0,0,0,0,//R红
//                0,1.2f,0,0,0,//G绿
//                0,0,1.2f,0,0,//B蓝
//                0,0,0,1.2f,0,//A透明度
//        };





        /**
         * 黑白照片
         *
         * 去色原理： 只要把RGB三通道的色彩信息设置成一样 ，那么图像就会变成灰色
         * 同时为了保证图片亮度不变 同一通道里面的R+G+B=1(0.213f + 0.715f + 0.072f =1)
         */
//        float[] flosts={
//               0.213f,0.715f,0.072f,0,0,//R红
//                0.213f,0.715f,0.072f,0,0,//G绿
//                0.213f,0.715f,0.072f,0,0,//B蓝
//                0,0,0,1,0,//A透明度
//        };



        /**
         * 发光效果 （比如红绿交换）
         */
//        float[] flosts={
//                1,0,0,0,0,//R红
//                0,0,1,0,0,//G绿
//                0,1,0,0,0,//B蓝
//                0,0,0,0.5f,0,//A透明度
//        };


        /**
         * 复古效果 （比如红绿交换）
         */
//        float[] flosts={
//                1/2f,1/2f,1/2f,0,0,//R红
//                1/3f,1/3f,1/3f,0,0,//G绿
//                1/4f, 1/4f, 1/4f,0,0,//B蓝
//                0,0,0,1,0,//A透明度
//        };
//

        /**
         * 颜色通道过滤（比如本身颜色矩阵A 过滤矩阵C）
         */
        float[] flosts={
                1.3f,0,0,0,0,//R红
                0,1.3f,0,0,0,//G绿
                0,0,1.3f,0,0,//B蓝
                0,0,0,1f,0,//A透明度
        };



        ColorMatrix colorMatrix=new ColorMatrix(flosts);
        //设置到画笔里面
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        //画个图片
        canvas.drawBitmap(bitmap,null,rectF,paint);






    }

    /**
     * 浮雕滤镜 （使用图片更有立体感）
     * 参数(指定光源的方向，环境光量（0-1 越接近0环境越暗），高光系数（反射系数 越接近0反射越强），模糊半径 （值越大 模糊效果越明显）)
     *
     * @param canvas
     */

    private void setEmbossMaskFilter(Canvas canvas) {
        //需要关闭view 硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, paint);

        float[] direction = {1, 1, 1};
        EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(direction, 0f, 800f, 300);
        paint.setMaskFilter(embossMaskFilter);
        paint.setColor(Color.RED);

        //画矩形
        canvas.drawRect(rectF,paint);

    }


    /**
     * 模糊 滤镜（图片模糊效果）
     * <p>
     * 参数（阴影半径，阴影样式）
     * 阴影样式: INNER  模糊内部边界 ，外部什么也不画（内部边界模糊，外部不画）
     * NORMAL 模糊内部和外部 原始边界（内部边界和外部都模糊）
     * OUTER  在边框内不画 ，在边框外模糊化（内部空白，模糊外部）
     * SOLID  在边框内画实线，在边框外模糊（指模糊外部）
     */
    private void setBlurMaskFilter(Canvas canvas) {
        //关闭当view的 硬件加速 （只针对当前这个类）
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(bitmap.getWidth() / 2, BlurMaskFilter.Blur.SOLID);
        paint.setMaskFilter(blurMaskFilter);


        //画个矩形
        canvas.drawBitmap(bitmap, null, rectF, paint);
    }
}
