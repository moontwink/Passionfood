package com.example.actionbartab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import creativecreations.foodcreations.R;

import database.JonDAO;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class IngredientsFragment extends Fragment implements ActionBar.TabListener{

	JonDAO recipeDB;
	public Fragment thisFragment;
	Spinner ingredient;
	EditText ingamt;
	Spinner ingunit;
	Button ingnext;
	
	int myIngredient = 0;
	int myUnit = 0;
	ArrayList<String> ingredients, units;
	
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
		
		//populate ingredients spinner (FROM DB - EDIT)
		recipeDB = new JonDAO(getActivity());
		
		ingredients = new ArrayList<String>();
		//add to list
		ingredients.add("Apple");
		ingredients.add("Orange");
		ingredients.add("Banana");
		ingredients.add("Kiwi");
		ingredients.add("Grape");
		
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
		
		ingnext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Store to a Temporary IngredientsDB (EDIT)
				saveSomeValues();
				Toast.makeText(getActivity().getApplicationContext(), ingamt.getText().toString() + " " + ingunit.getSelectedItem() + " " + ingredient.getSelectedItem(), Toast.LENGTH_SHORT).show();
			}
		});
		
		ingList = (ListView) getActivity().findViewById(R.id.listViewing);
		List<String> ingrsName = new ArrayList<String>();
		
		//retrieve from ingredientsdb (EDIT)
		for(int i=0; i < recipeDB.getAllRecipes().size(); i++)
			ingrsName.add(recipeDB.getAllRecipes().get(i).getName());
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingrsName);
		ingList.setAdapter(adapter2);
		
	}
	
	public void saveSomeValues()
	{
		String eol = System.getProperty("line.separator");
    	BufferedWriter writer = null;
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_ingredients", getActivity().MODE_PRIVATE)));
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
