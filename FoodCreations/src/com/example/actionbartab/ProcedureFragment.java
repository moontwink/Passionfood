package com.example.actionbartab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import tempdatabase.TempIngDao;
import tempdatabase.TempProcDao;

import creativecreations.foodcreations.MainPage;
import creativecreations.foodcreations.R;

import database.JonDAO;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ProcedureFragment extends Fragment implements ActionBar.TabListener{
	JonDAO recipeDB;
	TempProcDao tempPDB;
	public Fragment mFragment; 
	EditText procedure;
	Button addprocedure;
	ImageView accept2;
	
	ListView proList;
	
	 @Override 

	 public void onCreate(Bundle savedInstanceState) { 
		 super.onCreate(savedInstanceState); 
		 getActivity().setContentView(R.layout.procedurefragment); 

		 procedure = (EditText) getActivity().findViewById(R.id.procedurET);
		 addprocedure = (Button) getActivity().findViewById(R.id.addProc);
		 accept2 = (ImageView) getActivity().findViewById(R.id.acceptBtn2);
		 
//		 recipeDB = new JonDAO(getActivity());
		 tempPDB = new TempProcDao(getActivity());
		 tempPDB.open();
		 
		 readSomeValues();
		 
		 addprocedure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				saveSomeValues();
				Toast.makeText(getActivity().getApplicationContext(), procedure.getText().toString(), Toast.LENGTH_SHORT).show();
				tempPDB.createIngredient(procedure.getText().toString());
				
				proList = (ListView) getActivity().findViewById(R.id.procedurelist);
				List<String> procsName = new ArrayList<String>();
				
				//retrieve from proceduresDB (EDIT)
				for(int i=0; i < tempPDB.getAllProcedures().size(); i++)
					procsName.add(tempPDB.getAllProcedures().get(i));
				
				ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, procsName);
				proList.setAdapter(adapter2);
			}
		});
		 
		proList = (ListView) getActivity().findViewById(R.id.procedurelist);
		List<String> procsName = new ArrayList<String>();
		
		//retrieve from proceduresDB (EDIT)
		for(int i=0; i < tempPDB.getAllProcedures().size(); i++)
			procsName.add(tempPDB.getAllProcedures().get(i));
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, procsName);
		proList.setAdapter(adapter2);
		
		accept2.setOnClickListener(new OnClickListener() {
			
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
		 TempIngDao tempDB = new TempIngDao(getActivity());
		 tempDB.open();
		 
			String str = "";
			for(int i=0; i < tempDB.getAllIngredients().size(); i++)
				str = str.concat(tempDB.getAllIngredients().get(i).getAmt() + " " 
						+ tempDB.getAllIngredients().get(i).getUnit() + " "
						+ tempDB.getAllIngredients().get(i).getName() + "\n");
			
			tempDB.deleteAll();
			return str;
		}
		
		public String readProcedures(){
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
	    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_procedures", getActivity().MODE_PRIVATE)));
	    		writer.write(procedure.getText().toString() + eol);
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
	    		input = new BufferedReader(new InputStreamReader(getActivity().openFileInput("PASSIONFOOD_procedures")));
	    	    procedure.setText(input.readLine());
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

	 public void onTabSelected(Tab tab, FragmentTransaction ft) { 

		 // TODO Auto-generated method stub 
	
		 mFragment = new ProcedureFragment(); 
	
		 // Attach settingsfragment.xml layout 
	
		 ft.add(android.R.id.content, mFragment); 
	
		 ft.attach(mFragment);
	 }
	 
	 public void onTabUnselected(Tab tab, FragmentTransaction ft) { 

		 // TODO Auto-generated method stub 

		 // Remove settingsfragment.xml layout 

		 ft.detach(mFragment); 

	 } 

	 public void onTabReselected(Tab tab, FragmentTransaction ft) { 

		 // TODO Auto-generated method stub 

	 }

}
