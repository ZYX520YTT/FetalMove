package fetalmove.neusoft.com.fetalmove;
/**
 * Created by 张宇翔 on 2016/12/10 18:46.
 * Email：1124751755@qq.com
 * 功能：
 */
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Date;

import fetalmove.neusoft.com.fetalmove.activity.CharActivity;
import fetalmove.neusoft.com.fetalmove.activity.HirstoryActivity;
import fetalmove.neusoft.com.fetalmove.db.dao.CountDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.iv_count)
    private ImageView iv_count;
    @ViewInject(R.id.tv_count)
    private TextView tv_count;
    @ViewInject(R.id.tv_time)
    private TextView tv_time;
    @ViewInject(R.id.ib_hirstory)
    private ImageButton ib_hirstory;
    @ViewInject(R.id.ib_char)
    private ImageButton ib_char;


    private boolean isFirst = false;//两种状态
    private boolean isok = true;//判断是否在5秒内

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);

        iv_count.setOnClickListener(this);
        ib_hirstory.setOnClickListener(this);
        ib_char.setOnClickListener(this);

    }

    private int fetal_count = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_count:
                if (!isFirst) {
                    //切换背景，以及计时
                    ChangeAndTime();
                    isFirst = true;
                } else {
                    if(isok){
                        fetal_count++;
                        tv_count.setText(fetal_count + "");
                        isok=false;
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                SystemClock.sleep(5000);
                                isok=true;
                            }
                        }.start();
                    }else{
                        Toast.makeText(getApplicationContext(),"5秒内算一次",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ib_hirstory:
                startActivity(new Intent(MainActivity.this, HirstoryActivity.class));
                break;
            case R.id.ib_char:
                startActivity(new Intent(MainActivity.this, CharActivity.class));
                break;
            default:
                break;
        }
    }

    private int time_count = 60;

    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            time_count--;
            if (time_count == 0) {
                tv_count.setText("");
                tv_time.setText("60");
                handler.removeMessages(0);
                fetal_count = 0;
                isFirst = false;
                time_count = 60;
                iv_count.setImageResource(R.drawable.begincount);

                //插入记录到数据库
                InsertData();
            } else {
                handler.sendEmptyMessageDelayed(0, 1000);
                tv_time.setText(time_count + "");
            }


        }
    };

    private void ChangeAndTime() {
        //1.改变背景
        iv_count.setImageResource(R.drawable.aftercount);
        tv_count.setText("0");
        //2.计时
        handler.sendEmptyMessage(0);
    }


    //插入数据到数据库
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void InsertData(){
        CountDao dao=new CountDao(this);
//        dao.add();
        Date Date=new Date();
        SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format2=new SimpleDateFormat("HH:mm:ss");
        String date=format1.format(Date);
        String time=format2.format(Date);
        dao.add(date,time,fetal_count);
    }

}
