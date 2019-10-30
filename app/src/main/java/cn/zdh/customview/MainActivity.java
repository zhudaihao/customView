package cn.zdh.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.zdh.customview.xfermode.src_out.EraserView_SRCOUT;

public class MainActivity extends AppCompatActivity  {

    /**
     * paint高级使用
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        //------------------渲染-------------
//        setContentView(new MyShader(this));
//        setContentView(R.layout.text);

        //------------------过滤---------------
//        setContentView(new MaskFilter(this));

        //------------------Xfermode-------------

        /**
         * 设xfermodel 配置xfermode 模式
         */


        //----------------------具体模式----------------------------
        /**
         * DST_IN
         * DST_IN  保留覆盖像素的目标像素，丢弃其余的源和目标像素 （通过覆盖图实现 目标图多种样式 图片，比如圆角，星星。。）
         */
        //dts  ---->DST_IN 圆角图片效果
//        setContentView(new RoundImageView_DSTIN(this));
//
//        //dts  ---->DST_IN 倒影效果
//        setContentView(new InvertedImageView_DSTIN(this));

        //dts  ---->DST_IN 水波纹
//        setContentView(new IrregularWaveView_DSTIN(this));

        //心跳图
//        setContentView(new HeartMap_DSTIN(this));


        /**
         * lighten
         */

        //书架 灯光照射效果
//        setContentView(new LightBookView_LIGHTEN(this));

        /**
         * MULTIPLY
         * 将源和目标像素相乘(源是覆盖图，目标是目标图)
         */

        //小鸟效果
//        setContentView(new TwitterView_MULTIPLY(this));


        /**
         * SRC_IN
         */

        //圆角图片
//        setContentView(new RoundImageView_SRCIN(this));

        //倒影
//        setContentView(new InvertedImageView_SRCIN(this));


        /**
         * SRC_OUT
         */
        //刮刮卡
        setContentView(new EraserView_SRCOUT(this));
//        setContentView(new My_GuaGuaCardView_SRCOUT(this));



    }






}
