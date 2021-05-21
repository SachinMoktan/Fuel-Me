package sachin.com.fuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;

import sachin.com.fuel.Helper.Helper;
import sachin.com.fuel.Helper.UserInfo;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "fuelme";
    static int version = 1;

    String CreateUserTableSql = "CREATE TABLE if NOT EXISTS \"user\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"address\"\tTEXT,\n" +
            "\t\"phone\"\tNUMERIC,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"photo\"\tBLOB,\n" +
            "\tPRIMARY KEY(\"id\")\n" +
            ")";


    public DatabaseHelper( Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateUserTableSql);

    }

    public ArrayList<UserInfo> getUserList(){
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo> list = new ArrayList<>();

        while (cursor.moveToNext()){
            UserInfo info = new UserInfo();
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.phone = cursor.getString(cursor.getColumnIndex("phone"));
            info.gender = cursor.getString(cursor.getColumnIndex("gender"));
            list.add(info);

        }
        cursor.close();
        return list;
    }


    public UserInfo getUserInfo(String id){
        String sql = "Select * from user where id="+id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        UserInfo info = new UserInfo();



        while (cursor.moveToNext()){
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.phone = cursor.getString(cursor.getColumnIndex("phone"));
            info.gender = cursor.getString(cursor.getColumnIndex("gender"));

        }
        cursor.close();
        return info;
    }


    public void insertUser(ContentValues contentValues){ ;
        getWritableDatabase().insert("user", "", contentValues);

    }

    public void updateUser(String id, ContentValues contentValues) {
        getWritableDatabase().update("user", contentValues, "id=" + id, null);
//        getWritableDatabase().update("user",contentValues,"id=?",new String[]{id});
    }

    public boolean isLoginValid (String username, String password){
        String sql = "select count(*) from user where username = '" +username+"' and password = '"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        if (l==1){
            return true;
        }
        return false;
    }

    public void deleteUser(String id) {

        getWritableDatabase().delete("user", "id=" + id, null);
    }


    public void concept(){

       int[] ints = new int[10];
       String[] strings = new String[10];
       ints[0]=12;
       int a = ints[0];
       strings[0]="Asdklj";
       strings[1]="cat";
       strings[2]="hari";
       strings[3]="suhana";
       String aw=strings[0];

       Helper[] helpers = new Helper[10];

        ArrayList<Integer> list = new ArrayList<>();
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);
        list.add(23);

        Integer integer = list.get(0);

        ArrayList<Helper> list1 = new ArrayList<>();
        Helper helper = new Helper();
        helper.setUsername("Sachin");
        helper.setPassword("Sachin");
        helper.setEmail("Sachin");
        helper.setAddress("Sachin");
        helper.setAddress("Sachin");
        list1.add(helper);

        Helper helper1 = list1.get(0);
        UserInfo info = new UserInfo();
        info.id = "1";
        info.username = "suhana";
        info.password = "asasa";
        info.address = "asasa";
        info.email = "asasa";
        info.phone = "1";

        ArrayList<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(info);

        HashMap<String,UserInfo>hashMap = new HashMap<>();
        hashMap.put("suhana",info);

        hashMap.get("suhana");

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", "1");
        contentValues.put("username", "suhana");
        contentValues.put("address", "nepal");
        contentValues.put("username", "asdfasdf");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateUserTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
