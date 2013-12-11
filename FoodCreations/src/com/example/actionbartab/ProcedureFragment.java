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

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ProcedureFragment extends Fragment implements ActionBar.TabListener{
	JonDAO recipeDB;
	public Fragment mFragment; 
	EditText procedure;
	Button addprocedure;
	
	ListView proList;
	
	 @Override 

	 public void onCreate(Bundle savedInstanceState) { 
		 super.onCreate(savedInstanceState); 
		 getActivity().setContentView(R.layout.procedurefragment); 

		 procedure = (EditText) getActivity().findViewById(R.id.procedurET);
		 addprocedure = (Button) getActivity().findViewById(R.id.addProc);
		 
		 recipeDB = new JonDAO(getActivity());
		 readSomeValues();
		 
		 addprocedure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				saveSomeValues();
				Toast.makeText(getActivity().getApplicationContext(), procedure.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		 
		proList = (ListView) getActivity().findViewById(R.id.procedurelist);
		List<String> ingrsName = new ArrayList<String>();
		
		//retrieve from proceduresDB (EDIT)
		for(int i=0; i < recipeDB.getAllRecipes().size(); i++)
			ingrsName.add(recipeDB.getAllRecipes().get(i).getDescription());
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingrsName);
		proList.setAdapter(adapter2);
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

		 ft.remove(mFragment); 

	 } 

	 public void onTabReselected(Tab tab, FragmentTransaction ft) { 

		 // TODO Auto-generated method stub 

	 }

}
