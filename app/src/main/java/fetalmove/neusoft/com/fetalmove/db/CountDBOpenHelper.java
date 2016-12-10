package fetalmove.neusoft.com.fetalmove.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 张宇翔 on 2016/12/10 19:07.
 * Email：1124751755@qq.com
 * 功能：创建数据库
 */

public class CountDBOpenHelper extends SQLiteOpenHelper {


    public CountDBOpenHelper(Context context) {
        super(context, "count.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table info(_id integer primary key autoincrement,date varchar(20),time varchar(20),count integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
