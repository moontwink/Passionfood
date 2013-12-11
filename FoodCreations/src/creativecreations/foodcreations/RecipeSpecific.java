package creativecreations.foodcreations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeSpecific extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_specific);
		
		Bundle extras = getIntent().getExtras();
		String recipe_name = extras.getString("rname");
		int recipe_picid = extras.getInt("rpicid");
		String recipe_proc = extras.getString("rprocs");
		String recipe_ingr = extras.getString("ringr");
				
		TextView rname = (TextView) findViewById(R.id.recipe_specific_name);
		ImageView rimage = (ImageView) findViewById(R.id.recipe_specific_image);
		TextView ringr = (TextView) findViewById(R.id.recipe_shelf_ingredients);
		TextView rproc = (TextView) findViewById(R.id.recipe_shelf_proc);
		    
		rimage.setImageResource(recipe_picid);
		rname.setText(recipe_name);
		
		String ingredients = recipe_ingr;
		ingredients = ingredients.replaceAll("<", "\n-");
		ringr.setText(ingredients);
		
		String procs = recipe_proc;
		int numofcomma = countOccurrences(recipe_proc, ',');
		System.out.println("numComa " + numofcomma);
		
		for(int i = 2; i < numofcomma+2 ; i++)
		procs = procs.replaceFirst("<", "\n" +i +". ");
		
		rproc.setText("1. " + procs);
	
	
		

}


public  int countOccurrences(String haystack, char needle)
{
    int count = 0;
    for (int i=0; i < haystack.length(); i++)
    {
        if (haystack.charAt(i) == needle)
        {
             count++;
        }
    }
    return count;
}

}