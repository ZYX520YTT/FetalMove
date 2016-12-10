package fetalmove.neusoft.com.fetalmove.activity;

/**
 * Created by 张宇翔 on 2016/12/10 18:46.
 * Email：1124751755@qq.com
 * 功能：趋势图
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import fetalmove.neusoft.com.fetalmove.R;
import fetalmove.neusoft.com.fetalmove.bean.CountInfo;
import fetalmove.neusoft.com.fetalmove.db.dao.CountDao;

public class CharActivity extends AppCompatActivity {

    @ViewInject(R.id.ib_back)
    private ImageButton ib_back;
    @ViewInject(R.id.chart)
    private LineChart chart;

    private LineData datay;
    private ArrayList<String> xVals;
    private LineDataSet dataSet;
    private ArrayList<Entry> yVals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        ViewUtils.inject(this);

        Init();
    }

    private void Init() {

        //查询出历史数据
        CountDao countDao = new CountDao(this);
        List<CountInfo> countInfos = countDao.finall();


        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        xVals = new ArrayList<>();
        yVals = new ArrayList<>();

        for (int i = 0; i < countInfos.size(); i++) {

            yVals.add(new Entry(countInfos.get(i).getCount(), i));
            xVals.add(countInfos.get(i).getDate());
        }
        dataSet = new LineDataSet(yVals, "胎动趋势表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        datay = new LineData(xVals, dataSet);

        chart.setData(datay);

        chart.getFillFormatter();
        chart.setDescription("胎动趋势表");
        chart.animateY(3000);
    }
}
