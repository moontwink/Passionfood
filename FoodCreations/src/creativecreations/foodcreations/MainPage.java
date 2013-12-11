package creativecreations.foodcreations;

import com.example.actionbartab.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		
		ImageView button = (ImageView) findViewById(R.id.recipeShelfBtn);
	    ImageView button2 = (ImageView) findViewById(R.id.myRecipesBtn);
	    ImageView button3 = (ImageView) findViewById(R.id.groceryBtn);
	    ImageView button4 = (ImageView) findViewById(R.id.recoBtn);
	    
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, RecipeShelf.class);
                startActivity(intent);
            }
        });
        
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, GroceryList.class);
                startActivity(intent);
            }
        });
        
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, FoodTip.class);
                startActivity(intent);
            }
        });

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

}
