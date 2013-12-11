package tempdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TempProcSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_RECIPE = "tempprocedures";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_PROCEDURE = "procedure";
  private static final String DATABASE_NAME = "tempPROC.db";
  private static final int DATABASE_VERSION = 1;
 

  // Database creation sql statement
  private static final String CREATE_TABLE_PROCEDURE= "create table "
      + TABLE_RECIPE + "(" + COLUMN_ID  + " integer primary key autoincrement, " + COLUMN_PROCEDURE  + " text not null);";

  public TempProcSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CREATE_TABLE_PROCEDURE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(TempProcSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
    onCreate(db);
  }


} 