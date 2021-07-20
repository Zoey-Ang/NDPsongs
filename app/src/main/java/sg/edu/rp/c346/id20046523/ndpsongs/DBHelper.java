package sg.edu.rp.c346.id20046523.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ndpsongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONGS = "songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "years";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table song (_id INTEGER PRIMARY KEY AUTONCREMENT, title TEXT, singers TEXT, year INTEGER, stars INTEGER)
        String sqlStmt = "CREATE TABLE " + TABLE_SONGS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT," + COLUMN_YEAR + " INTEGER," + COLUMN_STARS + "INTEGER)";
        db.execSQL(sqlStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
    }

    public long insertSong(String title, String singer, String year, String star)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_SINGERS,singer);
        values.put(COLUMN_YEAR,year);
        values.put(COLUMN_STARS,star);

        long results = db.insert(TABLE_SONGS, null, values);
        db.close();
        return results;
    }

    public ArrayList<Song> getAllSongs()
    {
        ArrayList<Song> songList = new ArrayList<Song>();

        String sqlStmt = "SELECT " + COLUMN_TITLE + " , " + COLUMN_SINGERS + " , " + COLUMN_YEAR + " , "
                + COLUMN_STARS + " FROM " + TABLE_SONGS;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlStmt,null);
        if(cursor.moveToFirst())
        {
            do{
                String title = cursor.getString(0);
                String singer = cursor.getString(1);
                int year = cursor.getInt(2);
                int star = cursor.getInt(3);
                Song song = new Song(title, singer, year, star);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songList;
    }

    public int updateSong(Song data)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";

        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();
        return result;
    }

    public int deleteNote(int id)
    {
        SQLiteDatabase db =getWritableDatabase();
        String condition = COLUMN_ID + " =?";
        String [] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();
        return result;
    }


}
