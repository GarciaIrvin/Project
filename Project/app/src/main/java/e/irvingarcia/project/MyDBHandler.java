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
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSOWRD = "password";
    public static final String COLUMN_FIRST = "first";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_LAST = "last";

    public static final String TABLE_SERVICE = "services";
    public static final String COLUMN_SERVICE="service";
    public static final String COLUMN_HOURLY="hour_rate";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSOWRD + " TEXT2,"+ COLUMN_FIRST+ " TEXT3," +COLUMN_ROLE+ " TEXT4"+ ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_SERVICE_TABLE = "CREATE TABLE " +
                TABLE_SERVICE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SERVICE
                + " STRING,"+COLUMN_HOURLY+ " DOUBLE"+")";
        db.execSQL(CREATE_SERVICE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        onCreate(db);
    }
    public void addUser(Users user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,user.getUser());
        values.put(COLUMN_PASSOWRD,user.getPass());
        values.put(COLUMN_FIRST, user.getFirst());
        values.put(COLUMN_ROLE,user.getRole());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public void addAdmin(Admin admin){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,admin.getUsername());
        values.put(COLUMN_PASSOWRD,admin.getPassword());
        values.put(COLUMN_FIRST, admin.getFirstName());
        values.put(COLUMN_ROLE, "Admin");
        //values.put(COLUMN_LAST,user.getLast());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }
    public void addService(Service newService){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_SERVICE, newService.getService());
        values.put(COLUMN_HOURLY,newService.getHour());
        db.insert(TABLE_SERVICE,null,values);
        db.close();
    }
    public Service findService(String service){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+ " = \"" +service+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Service newService=new Service();
        if(cursor.moveToFirst()){
            newService.setService(cursor.getString(1));
            newService.setHour(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        }
        else{
            newService=null;
        }

        db.close();
        return newService;

    }
    public void updateService(Service updatedService,Service oldService){
        SQLiteDatabase db= this.getWritableDatabase();
//        String query="UPDATE" +TABLE_SERVICE +"SET"+ COLUMN_SERVICE+"="+ updatedService.getService()+
//                "WHERE"+ COLUMN_SERVICE+ "=";
//        db.execSQL(query);
        ContentValues values=new ContentValues();
        values.put(COLUMN_SERVICE,updatedService.getService());
        values.put(COLUMN_HOURLY,updatedService.getHour());
//        db.update(TABLE_SERVICE,values,COLUMN_SERVICE+"=" +oldService.getService(),null);
//        db.close();
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+
                " = \"" +oldService.getService()+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            System.out.println("Test");
            String idStr=cursor.getString(0);
            db.update(TABLE_SERVICE,values,COLUMN_ID +" = "+idStr,null);
            cursor.close();
        }
        db.close();
    }

    public boolean deleteService(String service){
        boolean result= false;
        SQLiteDatabase db= this.getWritableDatabase();
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+
                " = \"" +service+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.delete(TABLE_SERVICE,COLUMN_ID +" = "+idStr,null);
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }


    public boolean findUser(String user, String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Users users=new Users();
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
    public Users getProfile(String user, String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Users users=new Users();
        if(cursor.moveToFirst()){
            users.setUser(cursor.getString(1));
            users.setPass(cursor.getString(2));
            users.setFirst(cursor.getString(3));
            users.setRole(cursor.getString(4));
            cursor.close();
        }
        else{
            users=null;
        }

        db.close();
        return users;
    }

}
