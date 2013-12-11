package com.example.actionbartab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import creativecreations.foodcreations.R;
import database.JonDAO;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class Recipe_Form extends Fragment implements ActionBar.TabListener {

	JonDAO recipeDB;
	public Fragment thisFragment;
	EditText name;
	EditText description;
	Button next;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.recipefragment); 
		
		name = (EditText) getActivity().findViewById(R.id.recipeName);
		description = (EditText) getActivity().findViewById(R.id.recipeDesc);
		next = (Button) getActivity().findViewById(R.id.recipeNext);
		
		recipeDB = new JonDAO(getActivity());
		
		readSomeValues();
		
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Recipe newReci = new Recipe();
//				newReci.setName(name.getText().toString());
//				newReci.setDescription(description.getText().toString());
//				
//				long success = recipeDB.addRecipe(newReci);
//				
//				if(success > -1){
//					Toast.makeText(getActivity().getApplicationContext(), "Continue to next step!", Toast.LENGTH_SHORT).show();
//					
//				}else
//					Toast.makeText(getActivity().getApplicationContext(), "Something went wrong! Recipe was not saved.", Toast.LENGTH_SHORT).show();
				
				saveSomeValues();
			}
		});
	}
	
	//This function saves selections to a file stored in the application's internal storage
	public void saveSomeValues()
	{
		String eol = System.getProperty("line.separator");
    	BufferedWriter writer = null;
    	try {
    		writer = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("PASSIONFOOD_recipes", getActivity().MODE_PRIVATE)));
    		writer.write(name.getText().toString() + eol);
    		writer.write(description.getText().toString() + eol);
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
	
	//This function reads saved selections from a file stored in the application's internal storage
	public void readSomeValues()
	{
		BufferedReader input = null;
    	try {
    		input = new BufferedReader(new InputStreamReader(getActivity().openFileInput("PASSIONFOOD_recipes")));
    	    name.setText(input.readLine());
    	    description.setText(input.readLine());
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
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		thisFragment = new Recipe_Form();
		ft.add(android.R.id.content, thisFragment);
		ft.attach(thisFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		ft.detach(thisFragment);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	
}
