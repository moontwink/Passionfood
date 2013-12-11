package database;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CopyOfJonDAO{

	// Database fields
	private SQLiteDatabase database;
	private CopyOfJonSQLiteHelper dbHelper;
	private String[] allColumns = { 
			CopyOfJonSQLiteHelper.COLUMN_ID,
			CopyOfJonSQLiteHelper.COLUMN_INGREDIENT_NAME,
			};

	public CopyOfJonDAO(Context context) {
		dbHelper = new CopyOfJonSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public String createIngredient(String name) {
		ContentValues values = new ContentValues();
		values.put(CopyOfJonSQLiteHelper.COLUMN_INGREDIENT_NAME, name);
		long insertId = database.insert(CopyOfJonSQLiteHelper.TABLE_RECIPE, null,
				values);
		Cursor cursor = database.query(CopyOfJonSQLiteHelper.TABLE_RECIPE,
				allColumns, CopyOfJonSQLiteHelper.COLUMN_ID + " = " + insertId, null,
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

	public List<String> getAllIngredients() {
		List<String> ingredients = new ArrayList<String>();

		Cursor cursor = database.query(CopyOfJonSQLiteHelper.TABLE_RECIPE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String ing = cursorToRecipe(cursor);
			ingredients.add(ing);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return ingredients;
	}
	
	public List<String> getAllIngredientsByAlpha(String letter) {
		List<String> ingredients = new ArrayList<String>();

		Cursor cursor = database.query(CopyOfJonSQLiteHelper.TABLE_RECIPE,
				allColumns, "name like '"+letter+"%'", null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String ing = cursorToRecipe(cursor);
			ingredients.add(ing);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return ingredients;
	}

	private String cursorToRecipe(Cursor cursor) {
		String ingredient = "";
		ingredient = cursor.getString(1);
		return ingredient;
	}
}