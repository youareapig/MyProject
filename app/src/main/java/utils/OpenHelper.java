package utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
    String CREATETABLE = "create table search(_id INTEGER primary key autoincrement,historyText varchar(20) unique)";

    public OpenHelper(Context context) {
/**创建数据库mydatabase*/
        super(context, "history.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
/**创建数据表user*/
        sqLiteDatabase.execSQL(CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}