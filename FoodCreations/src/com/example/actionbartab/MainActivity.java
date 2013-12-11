package com.example.actionbartab;

import creativecreations.foodcreations.R;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.view.Menu;

public class MainActivity extends Activity {


	Tab tab;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create the actionbar 

        ActionBar actionBar = getActionBar(); 
        
        // Create Actionbar Tabs 

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 

        // Create first Tab 

        tab = actionBar.newTab().setTabListener(new Recipe_Form()); 

        // Set Tab Title 

        tab.setText("Create Recipe"); 
        tab.setTag("Recipe Tag");

        actionBar.addTab(tab); 

        // Create Second Tab 

        tab = actionBar.newTab().setTabListener(new IngredientsFragment()); 

        // Set Tab Title 

        tab.setText("Ingredients"); 
        tab.setTag("View Recipe Tag");

        actionBar.addTab(tab);
        
        // Create Third Tab 

        tab = actionBar.newTab().setTabListener(new ProcedureFragment()); 

        // Set Tab Title 

        tab.setText("Procedures"); 
        tab.setTag("Recipe Procedure Tag");

        actionBar.addTab(tab);
    }
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
//    	super.onRestoreInstanceState(savedInstanceState);
    	if(savedInstanceState.containsKey("selected_navigation_item")){
    		getActionBar().setSelectedNavigationItem(savedInstanceState.getInt("selected_navigation_item"));
    	}
    }
}
