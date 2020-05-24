package com.example.zqq.sqlite;

/**
 * Created by ZQQ on 2020/5/6.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDao {
    private SQLiteDatabase myDb;  //类的成员
    private DbHelper dbHelper;  //类的成员

    public MyDao(Context context) {  //构造方法，参数为上下文对象
        //第1参数为上下文，第2参数为数据库名
        dbHelper = new DbHelper(context,"test.db",null,1);
    }

    public Cursor allQuery(){    //查询所有记录
        myDb = dbHelper.getReadableDatabase();
        return myDb.rawQuery("select * from friends",null);
    }
    public  int getRecordsNumber(){  //返回数据表记录数
        myDb = dbHelper.getReadableDatabase();
        Cursor cursor= myDb.rawQuery("select * from friends",null);
        return cursor.getCount();
    }

    public void insertInfo(String name,int age){  //插入记录
        myDb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        long rowid=myDb.insert(DbHelper.TB_NAME, null, values);
        if(rowid==-1)
            Log.i("myDbDemo", "数据插入失败！");
        else
            Log.i("myDbDemo", "数据插入成功！"+rowid);
    }
    public void deleteInfo(String selId){  //删除记录
        String where = "_id=" + selId;
        int i = myDb.delete(DbHelper.TB_NAME, where, null);
        if (i > 0)
            Log.i("myDbDemo", "数据删除成功！");
        else
            Log.i("myDbDemo", "数据未删除！");
    }
    public void updateInfo(String name,int age,String selId){  //修改记录
        //方法中的第三参数用于修改选定的记录
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        String where="_id="+selId;
        int i=myDb.update(DbHelper.TB_NAME, values, where, null);

        //上面几行代码的功能可以用下面的一行代码实现
        //myDb.execSQL("update friends set name = ? ,age = ? where _id = ?",new Object[]{name,age,selId});

        if(i>0)
            Log.i("myDbDemo","数据更新成功！");
        else
            Log.i("myDbDemo","数据未更新！");
    }
}

