package e.irvingarcia.project;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSOWRD = "password";
    public static final String COLUMN_FIRST = "first";
    public static final String COLUMN_LAST = "last";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSOWRD + " STRING"+")";
        db.execSQL(CREATE_USER_TABLE);

//        String CREATE_ADMIN_TABLE = "CREATE TABLE " +
//                TABLE_ADMIN + "("
//                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
//                + " TEXT," + COLUMN_PASSOWRD + " STRING"+")";
//        db.execSQL(CREATE_ADMIN_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public void addUser(Users user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,user.getUser());
        values.put(COLUMN_PASSOWRD,user.getPass());
        //values.put(COLUMN_FIRST, user.getFirst());
        //values.put(COLUMN_LAST,user.getLast());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }
    public void addAdmin(Admin admin){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,admin.getUsername());
        values.put(COLUMN_PASSOWRD,admin.getPassword());
        //values.put(COLUMN_FIRST, user.getFirst());
        //values.put(COLUMN_LAST,user.getLast());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public boolean findUser(String user, String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        //Users users=new Users();
        if(cursor.moveToFirst()){
            String userRetrieve=(cursor.getString(1));
            String userPassword=cursor.getString(2);
            cursor.close();
            if(!userPassword.equals(pass)){
                return false;
            }
        }
        else{
            return false;
        }

        db.close();
        return true;
    }

}
