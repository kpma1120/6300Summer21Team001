package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public  static DBHandler getDbHandler(Context context)
    {
        return new DBHandler(context, "db", null, 1);

    }
    public  static DBHandler getDbHandler(Context context, String dbname)
    {
        return new DBHandler(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SETTING = "CREATE TABLE SETTING (ID INTEGER PRIMARY KEY, SALARY INTEGER DEFAULT 1,  BONUS INTEGER DEFAULT 1, SHARE INTEGER DEFAULT 1, TELEWORK INTEGER DEFAULT 1, LEAVE INTEGER DEFAULT 1 )";
        String CURRENT_JOB= "CREATE TABLE CURRENTJOB (ID INTEGER PRIMARY KEY, TITLE TEXT,  COMPANY TEXT, CITY TEXT, STATE TEXT, COSTOFLIVING INTEGER, SALARY INTEGER, BONUS INTEGER, TELEWORK INTEGER, LEAVE INTEGER, SHARE INTEGER )";
        String JOB_OFFER= "CREATE TABLE JOBOFFER (ID INTEGER PRIMARY KEY, TITLE TEXT,  COMPANY TEXT, CITY TEXT, STATE TEXT, COSTOFLIVING INTEGER, SALARY INTEGER, BONUS INTEGER, TELEWORK INTEGER, LEAVE INTEGER, SHARE INTEGER )";
        db.execSQL(CURRENT_JOB);
        db.execSQL(JOB_OFFER);
        db.execSQL(SETTING);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SETTING");
        db.execSQL("DROP TABLE IF EXISTS CURRENTJOB");
        db.execSQL("DROP TABLE IF EXISTS JOBOFFER");
        onCreate(db);

    }

    public void saveSetting(SettingModel setting) {
        ContentValues values = new ContentValues();
        values.put("SALARY", setting.getSalary());
        values.put("BONUS", setting.getBonus());
        values.put("TELEWORK", setting.getTelework());
        values.put("LEAVE", setting.getLeave());
        values.put("SHARE", setting.getShare());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("SETTING", null, values);
        db.close();
    }

    public SettingModel getSettings()
    {
        String query = "Select * FROM SETTING WHERE ID =(SELECT MAX(ID) FROM SETTING)";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        SettingModel sm= new SettingModel();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            sm.setSalary(Integer.parseInt(cursor.getString(1)));
            sm.setBonus(Integer.parseInt(cursor.getString(2)));
            sm.setShare(Integer.parseInt(cursor.getString(3)));
            sm.setTelework(Integer.parseInt(cursor.getString(4)));
            sm.setLeave(Integer.parseInt(cursor.getString(5)));
            cursor.close();
        } else {
            sm.setSalary(1);
            sm.setBonus(1);
            sm.setShare(1);
            sm.setTelework(1);
            sm.setLeave(1);
        }
        db.close();
        return sm;
    }
    public void saveCurrentJob(JobDetail jdm)
    {
        saveJobDetails(jdm, "CURRENTJOB");
    }
    public void saveJobOffer(JobDetail jdm)
    {
        saveJobDetails(jdm, "JOBOFFER");

    }
    private void saveJobDetails(JobDetail jdm, String tableName)
    {
        String CURRENT_JOB= " TITLE TEXT,  COMPANY TEXT, CITY TEXT, STATE TEXT, COSTOFLIVING INTEGER, SALARY INTEGER, BONUS INTEGER, TELEWORK INTEGER, LEAVE INTEGER, SHARE INTEGER )";
        ContentValues values = new ContentValues();
        values.put("TITLE", jdm.getTitle());
        values.put("COMPANY", jdm.getCompany());
        values.put("CITY", jdm.getCity());
        values.put("STATE", jdm.getState());
        values.put("COSTOFLIVING", jdm.getCostOfLiving());
        values.put("SALARY", jdm.getSalary());
        values.put("BONUS", jdm.getBonus());
        values.put("TELEWORK", jdm.getTelework());
        values.put("LEAVE ", jdm.getLeave());
        values.put("SHARE", jdm.getShare());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();

    }


    public JobDetail getCurrentJob()
    {
        String query = "Select * FROM CURRENTJOB WHERE ID =(SELECT MAX(ID) FROM CURRENTJOB)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        JobDetail jdm=null;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            jdm = extractJobDetails(cursor);
            jdm.setCurrentJob(true);
            cursor.close();
        }
        db.close();
        return jdm;
    }
    public JobDetail getOfferedJob()
    {
        String query = "Select * FROM JOBOFFER WHERE ID =(SELECT MAX(ID) FROM JOBOFFER)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        JobDetail jdm=null;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            jdm = extractJobDetails(cursor);
            cursor.close();
        }
        db.close();
        return jdm;
    }


    public JobDetail getOfferedJob(String id) {
        String query = "Select * FROM JOBOFFER WHERE id =?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String [] {id});
        JobDetail jdm=null;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            jdm = extractJobDetails(cursor);
            cursor.close();
        };
        db.close();
        return jdm;
    }

    public List<JobDetail> getOfferedJobs() {
      List<JobDetail>  offeredJobs = new ArrayList<>();
        String query = "Select * FROM JOBOFFER";
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                offeredJobs.add(extractJobDetails(cursor));
            }
            cursor.close();
        }
        db.close();
        return offeredJobs;
    }

    private JobDetail extractJobDetails(Cursor cursor)
    {
        JobDetail jdm= new JobDetail();
            jdm.setId(cursor.getString(0));
            jdm.setTitle(cursor.getString(1));
            jdm.setCompany(cursor.getString(2));
            jdm.setCity(cursor.getString(3));
            jdm.setState(cursor.getString(4));
            jdm.setCostOfLiving(Integer.parseInt(cursor.getString(5)));
            jdm.setSalary(Integer.parseInt(cursor.getString(6)));
            jdm.setBonus(Integer.parseInt(cursor.getString(7)));
            jdm.setTelework(Integer.parseInt(cursor.getString(8)));
            jdm.setLeave(Integer.parseInt(cursor.getString(9)));
            jdm.setShare(Integer.parseInt(cursor.getString(10)));
            return jdm;
    }
}
