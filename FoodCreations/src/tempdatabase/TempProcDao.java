package tempdatabase;

import java.util.ArrayList;
import java.util.List;

import models.Ingredients;
import models.Recipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TempProcDao{

	// Database fields
	private SQLiteDatabase database;
	private TempProcSQLiteHelper dbHelper;
	private String[] allColumns = { 
			TempProcSQLiteHelper.COLUMN_ID,
			TempProcSQLiteHelper.COLUMN_PROCEDURE
			};

	public TempProcDao(Context context) {
		dbHelper = new TempProcSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public String createIngredient(String name) {
		ContentValues values = new ContentValues();
		values.put(TempProcSQLiteHelper.COLUMN_PROCEDURE, name);
		long insertId = database.insert(TempProcSQLiteHelper.TABLE_RECIPE, null,
				values);
		Cursor cursor = database.query(TempProcSQLiteHelper.TABLE_RECIPE,
				allColumns, TempProcSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		String newRecipe = cursorToRecipe(cursor);
		cursor.close();
		return newRecipe;
	}

//	public void deleteRecipe(Recipe recipe) {
//		long id = recipe.getId();
//		System.out.println("Recipe deleted with id: " + id);
//		database.delete(JonSQLiteHelper.TABLE_RECIPE, JonSQLiteHelper.COLUMN_ID
//				+ " = " + id, null);
//	}

	public List<String> getAllProcedures() {
		List<String> procedures = new ArrayList<String>();

		Cursor cursor = database.query(TempProcSQLiteHelper.TABLE_RECIPE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String proc = cursorToRecipe(cursor);
			procedures.add(proc);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return procedures;
	}
	
	public void deleteAll(){
		database.delete(TempProcSQLiteHelper.TABLE_RECIPE, null, null);
	}

	private String cursorToRecipe(Cursor cursor) {
		String procedure = "";
		procedure = cursor.getString(1);
		return procedure;
	}
}