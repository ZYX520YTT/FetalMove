package fetalmove.neusoft.com.fetalmove.bean;

/**
 * Created by 张宇翔 on 2016/12/10 19:40.
 * Email：1124751755@qq.com
 * 功能：
 */

public class CountInfo {

    private String date;
    private String time;
    private int count;

    public CountInfo() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CountInfo{" +
                "count=" + count +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
