package creativecreations.foodcreations;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import database.JonDAO;

import android.app.ListActivity;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeShelf extends ListActivity {
	JonDAO jdao;
	private List<String> names;
	private List<String> desc;
    private List<String> ingr;
    private List<String> procs;
	public List<Integer> pics;

	@Override
	public void onCreate(Bundle icicle) {
		jdao = new JonDAO(this);
		jdao.open();
		
		names = new ArrayList<String>();
		pics = new ArrayList<Integer>();
		desc = new ArrayList<String>();
		ingr = new ArrayList<String>();
		procs = new ArrayList<String>();
		
		List<Recipe> nvm = jdao.getAllRecipes();
		
		if(nvm.isEmpty()){
			jdao.createRecipe("Balsamic", "DESCRIPTION", R.drawable.roastedporkloin, "INGREDIENTS", "PROCEDURES");				
			jdao.createRecipe("Best Chocolate Chip Cookies", "DESCRIPTION", R.drawable.chocolatechipcookies, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Delicious Ham and Potato Soup", "DESCRIPTION", R.drawable.potatohamsoup, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Grandma's Green Bean Casserole", "DESCRIPTION", R.drawable.greenbeancasserole, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Homemade Mac and Cheese", "DESCRIPTION", R.drawable.mac_cheese, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Easy Chicken and Corn Chowder", "DESCRIPTION", R.drawable.chickenandcornchowder, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Stuffed Pork Chops I", "DESCRIPTION", R.drawable.stuffedporkchops, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Mushroom Slow Cooker Roast Beef", "DESCRIPTION", R.drawable.slowcookerroastbeef, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Oven Roasted Parmesan Potatoes", "DESCRIPTION", R.drawable.roastedparmesanpotatoes, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Rusty Chicken Thighs", "DESCRIPTION", R.drawable.chickenthighs, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Baked French Toast", "DESCRIPTION", R.drawable.frenchtoast, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Country-Style Steak", "DESCRIPTION", R.drawable.countrystylesteak, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Brown Sugar Smokies", "DESCRIPTION", R.drawable.sugarsmokies, "INGREDIENTS", "PROCEDURES");
			jdao.createRecipe("Buffalo Chicken Dip", "DESCRIPTION", R.drawable.buffalochickendip, "INGREDIENTS", "PROCEDURES");
		}
		
		List<Recipe> allrecipes = jdao.getAllRecipes();
		
		for(Recipe r: allrecipes){
			names.add(r.getName());
			pics.add(r.getpicID());
			desc.add(r.getDescription());
			ingr.add(r.getIngredients());
			procs.add(r.getProcedures());
		}

		
		
		//System.out.println("picZZs = "+ pics.get(3));
		
		
		super.onCreate(icicle);
		setListAdapter(new IconicAdapter());
		
		
	}

	class IconicAdapter extends ArrayAdapter<String> {
		
	
		IconicAdapter() {
			super(RecipeShelf.this, R.layout.icon_text_layout, R.id.label,
					names);
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int i = position;
			final String recipe_name = names.get(i);
			final String recipe_procs = procs.get(i);
			final int recipe_picid = pics.get(i);
			final String recipe_ingr = ingr.get(i);
			
			
			
			
			
			
			View row = super.getView(position, convertView, parent);
			ImageView icon = (ImageView) row.findViewById(R.id.icon);
			icon.setImageResource(pics.get(position));
			
			final TextView label = (TextView) row.findViewById(R.id.label);
			
			String refinedDesc = desc.get(position);
			if(refinedDesc.length() > 50){
			refinedDesc = refinedDesc.substring(0, 50);
			refinedDesc = refinedDesc.concat("...");
			}
			label.setText(names.get(position) + "\n" + refinedDesc);
			
			row.setOnClickListener(new View.OnClickListener() {
				
				 @Override
		            public void onClick(View view) {
					 Intent intent = new Intent(RecipeShelf.this, RecipeSpecific.class);
					 System.out.println("i = " + i);
					 System.out.println("doubleChck recipe = " + names.get(i) + ": picId" + pics.get(i));
					 intent.putExtra("rname",  recipe_name);
					 intent.putExtra("rprocs", recipe_procs);
					 intent.putExtra("rpicid", recipe_picid);
					 intent.putExtra("ringr", recipe_ingr);
					 startActivity(intent);
		            }
		        });
			
			return (row);
		}
	}
}