package tempdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TempIngSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_RECIPE = "tempingredients";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_INGREDIENT_NAME = "name";
  public static final String COLUMN_AMOUNT = "_amount";
  public static final String COLUMN_UNIT = "unit";
  private static final String DATABASE_NAME = "tempING.db";
  private static final int DATABASE_VERSION = 1;
 

  // Database creation sql statement
  private static final String CREATE_TABLE_INGREDIENT= "create table "
      + TABLE_RECIPE + "(" + COLUMN_ID  + " integer primary key autoincrement, " + COLUMN_INGREDIENT_NAME  + " text not null, " + COLUMN_AMOUNT + " text not null, " + COLUMN_UNIT + " text not null );";

  public TempIngSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CREATE_TABLE_INGREDIENT);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(TempIngSQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
    onCreate(db);
  }


} 