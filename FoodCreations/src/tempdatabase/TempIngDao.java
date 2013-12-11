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

public class TempIngDao{

	// Database fields
	private SQLiteDatabase database;
	private TempIngSQLiteHelper dbHelper;
	private String[] allColumns = { 
			TempIngSQLiteHelper.COLUMN_ID,
			TempIngSQLiteHelper.COLUMN_INGREDIENT_NAME,
			TempIngSQLiteHelper.COLUMN_AMOUNT,
			TempIngSQLiteHelper.COLUMN_UNIT,
			};

	public TempIngDao(Context context) {
		dbHelper = new TempIngSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Ingredients createIngredient(String name, String amount, String unit) {
		ContentValues values = new ContentValues();
		values.put(TempIngSQLiteHelper.COLUMN_INGREDIENT_NAME, name);
		values.put(TempIngSQLiteHelper.COLUMN_AMOUNT, amount);
		values.put(TempIngSQLiteHelper.COLUMN_UNIT, unit);
		long insertId = database.insert(TempIngSQLiteHelper.TABLE_RECIPE, null,
				values);
		Cursor cursor = database.query(TempIngSQLiteHelper.TABLE_RECIPE,
				allColumns, TempIngSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Ingredients newRecipe = cursorToRecipe(cursor);
		cursor.close();
		return newRecipe;
	}

//	public void deleteRecipe(Recipe recipe) {
//		long id = recipe.getId();
//		System.out.println("Recipe deleted with id: " + id);
//		database.delete(JonSQLiteHelper.TABLE_RECIPE, JonSQLiteHelper.COLUMN_ID
//				+ " = " + id, null);
//	}

	public List<Ingredients> getAllIngredients() {
		List<Ingredients> ingredients = new ArrayList<Ingredients>();

		Cursor cursor = database.query(TempIngSQLiteHelper.TABLE_RECIPE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Ingredients ing = cursorToRecipe(cursor);
			ingredients.add(ing);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return ingredients;
	}
	
	public void deleteAll(){
		database.delete(TempIngSQLiteHelper.TABLE_RECIPE, null, null);
	}

	private Ingredients cursorToRecipe(Cursor cursor) {
		Ingredients ing = new Ingredients();
		ing.setId(cursor.getInt(0));
		ing.setName(cursor.getString(1));
		ing.setAmt(cursor.getString(2));
		ing.setUnit(cursor.getString(3));
		return ing;
	}
}