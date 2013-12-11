package database;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class JonDAO{

	// Database fields
	private SQLiteDatabase database;
	private JonSQLiteHelper dbHelper;
	private String[] allColumns = { 
			JonSQLiteHelper.COLUMN_ID,
			JonSQLiteHelper.COLUMN_RECIPE_NAME,
			JonSQLiteHelper.COLUMN_RECIPE_DESCRIPTION, 
			JonSQLiteHelper.COLUMN_RECIPE_PICID,
			JonSQLiteHelper.COLUMN_RECIPE_INGREDIENTS,
			JonSQLiteHelper.COLUMN_RECIPE_PROCEDURES
			};

	public JonDAO(Context context) {
		dbHelper = new JonSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Recipe createRecipe(String name, String description, int picid, String ingredients, String procedures) {
		ContentValues values = new ContentValues();
		values.put(JonSQLiteHelper.COLUMN_RECIPE_NAME, name);
		values.put(JonSQLiteHelper.COLUMN_RECIPE_DESCRIPTION, description);
		values.put(JonSQLiteHelper.COLUMN_RECIPE_PICID, picid);
		values.put(JonSQLiteHelper.COLUMN_RECIPE_INGREDIENTS, ingredients);
		values.put(JonSQLiteHelper.COLUMN_RECIPE_PROCEDURES, procedures);
		long insertId = database.insert(JonSQLiteHelper.TABLE_RECIPE, null,
				values);
		Cursor cursor = database.query(JonSQLiteHelper.TABLE_RECIPE,
				allColumns, JonSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Recipe newRecipe = cursorToRecipe(cursor);
		cursor.close();
		return newRecipe;
	}

	public void deleteRecipe(Recipe recipe) {
		long id = recipe.getId();
		System.out.println("Recipe deleted with id: " + id);
		database.delete(JonSQLiteHelper.TABLE_RECIPE, JonSQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

//	public List<Recipe> getAllRecipes() {
//		List<Recipe> recipes = new ArrayList<Recipe>();
//
//		Cursor cursor = database.query(JonSQLiteHelper.TABLE_RECIPE,
//				allColumns, null, null, null, null, null);
//
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Recipe recipe = cursorToRecipe(cursor);
//			recipes.add(recipe);
//			cursor.moveToNext();
//		}
//		// make sure to close the cursor
//		cursor.close();
//		return recipes;
//	}
	
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cur = db.rawQuery("SELECT * FROM recipes", null);

		cur.moveToFirst();
		while (!cur.isAfterLast()) {
			Recipe recipe = cursorToRecipe(cur);
			recipes.add(recipe);
			cur.moveToNext();
		}

		db.close();
		return recipes;
	}

	private Recipe cursorToRecipe(Cursor cursor) {
		Recipe recipe = new Recipe();
		recipe.setId(cursor.getInt(0));
		recipe.setName(cursor.getString(1));
		recipe.setDescription(cursor.getString(2));
		recipe.setpicID(cursor.getInt(3));
		recipe.setIngredients(cursor.getString(4));
		recipe.setProcedures(cursor.getString(5));
		return recipe;
	}
}