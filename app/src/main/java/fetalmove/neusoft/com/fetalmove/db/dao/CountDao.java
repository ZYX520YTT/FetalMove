package fetalmove.neusoft.com.fetalmove.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fetalmove.neusoft.com.fetalmove.bean.CountInfo;
import fetalmove.neusoft.com.fetalmove.db.CountDBOpenHelper;

/**
 * Created by 张宇翔 on 2016/12/10 19:12.
 * Email：1124751755@qq.com
 * 功能：插入数据
 */

public class CountDao {

    private CountDBOpenHelper helper;

    public CountDao(Context context){
        helper=new CountDBOpenHelper(context);
    }

    //添加日期，时间，次数
    public boolean add(String date,String time,int count){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("date",date);
        values.put("time",time);
        values.put("count",count);
        long id=db.insert("info",null,values);
        if(id==-1)
            return false;
        return true;
    }


    public List<CountInfo> finall(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("info",new String[]{"date","time","count"},null,null,null,null,null);
        List<CountInfo> countInfos=new ArrayList<>();
        while(cursor.moveToNext()){
            CountInfo countInfo=new CountInfo();
            String date=cursor.getString(0);
            String time=cursor.getString(1);
            int count=cursor.getInt(2);
            countInfo.setDate(date);
            countInfo.setTime(time);
            countInfo.setCount(count);
            countInfos.add(countInfo);
        }
        cursor.close();
        db.close();
        return countInfos;
    }

}
