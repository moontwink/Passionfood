package com.example.actionbartab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import tempdatabase.TempIngDao;
import tempdatabase.TempIngSQLiteHelper;
import tempdatabase.TempProcDao;

import creativecreations.foodcreations.MainPage;
import creativecreations.foodcreations.R;

import database.CopyOfJonDAO;
import database.JonDAO;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class IngredientsFragment extends Fragment implements ActionBar.TabListener{

	JonDAO recipeDB;
	CopyOfJonDAO ingDB;
	TempIngDao tempDB;
	public Fragment thisFragment;
	Spinner ingredient;
	EditText ingamt;
	Spinner ingunit;
	Button ingnext;
	ImageView accept;
	
	int myIngredient = 0;
	int myUnit = 0;
	int myAlpha = 0;
	List<String> ingredients, units;
	
	ListView ingList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.ingredientfragment); 
		
		ingredient = (Spinner) getActivity().findViewById(R.id.ingredientList);
		ingamt = (EditText) getActivity().findViewById(R.id.ingAmt);
		ingunit = (Spinner) getActivity().findViewById(R.id.ingUnit);
		ingnext = (Button) getActivity().findViewById(R.id.ingNext);
		accept = (ImageView) getActivity().findViewById(R.id.acceptBtn);
		
		//populate ingredients spinner (FROM DB - EDIT)
		ingDB = new CopyOfJonDAO(getActivity());
		ingDB.open();
		
		ingredients = ingDB.getAllIngredients();
		//add to list
		if(ingredients.isEmpty()){
				ingredients.add("Abalone");	ingredients.add("Almonds");	ingredients.add("Anchovies");
				ingredients.add("Apples");	ingredients.add("Apricots");	ingredients.add("Asparagus");
				
				ingredients.add("bacon");	ingredients.add("bagoong");	ingredients.add("baking powder");
				ingredients.add("baking soda");	ingredients.add("balsamic vinegar");	ingredients.add("basil");
				ingredients.add("bean sprouts");	ingredients.add("broccoli");	ingredients.add("brown sugar");
				
				ingredients.add("canola oil");	ingredients.add("carrots");	ingredients.add("cassava");
				ingredients.add("celery");	ingredients.add("cheddar cheese");	ingredients.add("cheese");
				ingredients.add("coconut oil");	ingredients.add("crabs");	ingredients.add("curry leaves");
				
				ingredients.add("dates");	ingredients.add("dill");	ingredients.add("dried leeks");
				ingredients.add("duck");	ingredients.add("eels");	ingredients.add("eggs");
				ingredients.add("eggplants");	ingredients.add("escalopes");	ingredients.add("fennel");
				
				ingredients.add("figs");	ingredients.add("fish sauce");	ingredients.add("flour");
				ingredients.add("ginger");	ingredients.add("gorgonzola");	ingredients.add("graham crackers");
				ingredients.add("granola");	ingredients.add("grapes");	ingredients.add("ham");
				
				ingredients.add("hazelnuts");	ingredients.add("honey");	ingredients.add("ice cream");
				ingredients.add("jelly beans");	ingredients.add("kiwi");	ingredients.add("lamb");
				ingredients.add("leeks");	ingredients.add("lemons");	ingredients.add("lobsters");
				
				ingredients.add("macaroni");	ingredients.add("mangoes");	ingredients.add("mint");
				ingredients.add("nectarines");	ingredients.add("nori");	ingredients.add("oatmeal");
				
				ingredients.add("olives");	ingredients.add("onions");	ingredients.add("oregano");
				ingredients.add("pancetta");	ingredients.add("parsley");	ingredients.add("peanuts");
				ingredients.add("pepper");	ingredients.add("plums");	ingredients.add("pumpkins");
				
				ingredients.add("quail");	ingredients.add("raisins");	ingredients.add("rice");
				ingredients.add("rum");	ingredients.add("saffron");	ingredients.add("salmon");
				ingredients.add("sardines");	ingredients.add("sugar");	ingredients.add("sweet potatoes");
				
				ingredients.add("tamarind");	ingredients.add("tea");	ingredients.add("tomatoes");
				ingredients.add("tuna");	ingredients.add("turkeys");	ingredients.add("turnips");
				
				ingredients.add("unsweetened chocolate");	ingredients.add("vanilla");	ingredients.add("vinegar");
				ingredients.add("walnuts");	ingredients.add("water");	ingredients.add("watermelons");
				ingredients.add("wine");	ingredients.add("yeast");	ingredients.add("yogurt");
				
				ingredients.add("zest");	
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ingredients);
		ingredient.setAdapter(adapter);
		
		readSomeValues();
		
		
		ingredient.setSelection(myIngredient);
		ingredient.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int myIngInt, long arg3) {
				// TODO Auto-generated method stub
				myIngredient = myIngInt;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		//populate units spinner
		units = new ArrayList<String>();
		//add to list
		units.add("cup");		units.add("cm");		units.add("dl");
		units.add("gallon");	units.add("fl oz");		units.add("gram");
		units.add("inch");		units.add("kg");		units.add("l");
		units.add("m");			units.add("mg");		units.add("ml");
		units.add("mm");		units.add("ounce");		units.add("pcs");
		units.add("pint");
		units.add("pound");		units.add("quart");		units.add("tsp.");
		units.add("tbsp.");
		
		ArrayAdapter<String> adapteru = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, units);
		ingunit.setAdapter(adapteru);
		
		ingunit.setSelection(myUnit);
		ingunit.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int myUnitInt, long arg3) {
				// TODO Auto-generated method stub
				myUnit = myUnitInt;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tempDB = new TempIngDao(getActivity());
		tempDB.open();
		
		ingnext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Store to a Temporary IngredientsDB (EDIT)
				saveSomeValues();
				Toast.makeText(getActivity().getApplicationContext(), ingamt.getText().toString() + " " + ingunit.getSelectedItem() + " " + ingredient.getSelectedItem(), Toast.LENGTH_SHORT).show();
				
				String amtformat = ingamt.getText().toString();
				tempDB.createIngredient(ingredient.getSelectedItem().toString(), amtformat, ingunit.getSelectedItem().toString());
			
				ingList = (ListView) getActivity().findViewById(R.id.listViewing);
				List<String> ingrsName = new ArrayList<String>();
				
				//retrieve from ingredientsdb (EDIT)
				for(int i=0; i < tempDB.getAllIngredients().size(); i++)
					ingrsName.add(tempDB.getAllIngredients().get(i).getAmt() + " " 
							+ tempDB.getAllIngredients().get(i).getUnit() + " "
							+ tempDB.getAllIngredients().get(i).getName());
				
				ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingrsName);
				ingList.setAdapter(adapter2);
			}
		});
		
		ingList = (ListView) getActivity().findViewById(R.id.listViewing);
		List<String> ingrsName = new ArrayList<String>();
		
		//retrieve from ingredientsdb (EDIT)
		for(int i=0; i < tempDB.getAllIngredients().size(); i++)
			ingrsName.add(tempDB.getAllIngredients().get(i).getAmt() + " " 
					+ tempDB.getAllIngredients().get(i).getUnit() + " "
					+ tempDB.getAllIngredients().get(i).getName());
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingrsName);
		ingList.setAdapter(adapter2);
		
		accept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Recipe r = readAllValues();
				
				r.setIngredients(readIngredients());
				r.setProcedures(readProcedures());
				
				recipeDB = new JonDAO(getActivity());
				recipeDB.open();
				recipeDB.createRecipe(r.getName(), r.getDescription(), R.drawable.ic_launcher, r.getIngredients(), r.getProcedures());
			
				Toast.makeText(getActivity().getApplicationContext(), r.getName() + "  saved!", Toast.LENGTH_SHORT).show();
				eraseAllValues();
				
				Intent intent = new Intent(getActivity(), MainPage.class);
                startActivity(intent);
			}
		});
		
	}
	
	public String readIngredients(){
		String str = "";
		for(int i=0; i < tempDB.getAllIngredients().size(); i++)
			str = str.concat(tempDB.getAllIngredients().get(i).getAmt() + " " 
					+ tempDB.getAllIngredients().get(i).getUnit() + " "
					+ tempDB.getAllIngredients().get(i).getName() + "\n");
		tempDB.deleteAll();
		return str;
	}
	
	public String readProcedures(){
		TempProcDao tempPDB = new TempProcDao(getActivity());
		tempPDB.open();
		
		String str = "";
		for(int i=0; i < tempPDB.getAllProcedures().size(); i++)
			str = str.concat(tempPDB.getAllProcedures().get(i) + "\n");
		
		tempPDB.deleteAll();
		return str;
	}
	
	public Recipe readAllValues()
	{
		Recipe recipe = new Recipe();
		BufferedReader input = null;
    	try {
    		input = new BufferedReader(new InputStreamReader(getActivity().openFileInput("PASSIONFOOD_recipes")));
    	    recipe.setName(input.readLine());
    	    recipe.setDescription(input.readLine());
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return recipe;
	}
	
	public void eraseAllValues(){
		BufferedWriter writer = null;
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_recipes", getActivity().MODE_PRIVATE)));
    		writer.write("");
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_ingredients", getActivity().MODE_PRIVATE)));
    		writer.write("");
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_procedures", getActivity().MODE_PRIVATE)));
    		writer.write("");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (writer != null) {
    			try {
    				writer.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    		}
    	}
	}
	
	public void saveSomeValues()
	{
		String eol = System.getProperty("line.separator");
    	BufferedWriter writer = null;
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_ingredients", getActivity().MODE_PRIVATE)));
    		writer.write(myAlpha + eol);
    		writer.write(myIngredient + eol);
    		writer.write(ingamt.getText().toString() + eol);
    		writer.write(myUnit + eol);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (writer != null) {
    			try {
    				writer.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    		}
    	}
	}
	
	public void readSomeValues() {
		BufferedReader input = null;
    	try {
    		input = new BufferedReader(new InputStreamReader(getActivity().openFileInput("PASSIONFOOD_ingredients")));
    	    myAlpha = Integer.parseInt(input.readLine());
    		myIngredient = Integer.parseInt(input.readLine());
    	    ingamt.setText(input.readLine());
    	    myUnit = Integer.parseInt(input.readLine());
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		thisFragment = new IngredientsFragment();
		ft.add(android.R.id.content, thisFragment);
		ft.attach(thisFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.detach(thisFragment);
	}

}
