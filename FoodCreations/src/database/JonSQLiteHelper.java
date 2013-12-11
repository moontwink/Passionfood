package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JonSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_RECIPE = "recipe";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_RECIPE_NAME = "name";
  public static final String COLUMN_RECIPE_DESCRIPTION = "description";
  public static final String COLUMN_RECIPE_PICID = "picid";
  public static final String COLUMN_RECIPE_INGREDIENTS = "ingredient";
  public static final String COLUMN_RECIPE_PROCEDURES = "procedures";
  private static final String DATABASE_NAME = "passionfoodFINAL2.db";
  private static final int DATABASE_VERSION = 1;
 

  // Database creation sql statement
  private static final String CREATE_TABLE_RECIPE= "create table "
      + TABLE_RECIPE + "(" + COLUMN_ID  + " integer primary key autoincrement, " + COLUMN_RECIPE_NAME  + " text not null, " + COLUMN_RECIPE_DESCRIPTION   + " text not null," +  COLUMN_RECIPE_PICID + " integer not null, " + COLUMN_RECIPE_INGREDIENTS + " text not null ," + COLUMN_RECIPE_PROCEDURES + " text not null);";

  public JonSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CREATE_TABLE_RECIPE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(JonSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
    onCreate(db);
  }


} 