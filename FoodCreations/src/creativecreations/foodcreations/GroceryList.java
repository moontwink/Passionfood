package creativecreations.foodcreations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class GroceryList extends Activity implements OnClickListener, OnKeyListener {
    
	private EditText m_vwEditText;
    private Button m_vwButton;
    private Button m_vwButton2;
    private ListView m_vwList;
    private ArrayAdapter<String> m_adapter;
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        
        m_vwEditText = (EditText) findViewById(R.id.editText);
        m_vwButton = (Button) findViewById(R.id.button);
        m_vwButton2 = (Button) findViewById(R.id.button2);
        m_vwList = (ListView) findViewById(R.id.list);
        
        m_adapter = new ArrayAdapter<String>(this, R.layout.textview);
        
		
        m_vwList.setAdapter(m_adapter);
        readSomeValues();
        
        m_vwButton.setOnClickListener(this);
        m_vwButton2.setOnClickListener(this);
        m_vwEditText.setOnKeyListener(this);
    }
    
    /** Called when the Button is clicked */
    @Override
    public void onClick(View v) {
        
    	
    	
    	switch (v.getId()) {
        case R.id.button: 
        	
        	String item = m_vwEditText.getText().toString();
            saveSomeValues(item);
            m_vwEditText.setText("");
            if (!item.equals("")) {
    		m_adapter.add(item);
    		}
         break;
         
        case R.id.button2:
        	
        	new AlertDialog.Builder(this)
            .setTitle("Clear Shopping List")
            .setMessage("Are you sure you want to clear the shopping list?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // continue with delete
                	
                	m_adapter.clear();
                	BufferedWriter writer = null;
                	try {
                		writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("GROCERY_items", MODE_PRIVATE)));
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
             })
             
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // do nothing
                }
             })
             .show();
        	
         break;
     }
    	
    	
        
        
        
    }
    
    
    
    
    /** Called when a key is pressed while the EditText view has focus */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                String item = m_vwEditText.getText().toString();
                m_vwEditText.setText("");
                if (!item.equals("")){
                	m_adapter.add(item);
                	
                }
            }
            return true;
        }
        return false;
    }



public void saveSomeValues(String item)
{
	BufferedWriter writer = null;
	try {
		writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("GROCERY_items", MODE_PRIVATE | MODE_APPEND)));
		writer.write("\n" + item + "\n");
		
		
		
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


public void readSomeValues()
{
	BufferedReader input = null;
	String line = null;
	try {
		input = new BufferedReader(new InputStreamReader(openFileInput("GROCERY_items")));
		do {
		     line = input.readLine();
		     m_adapter.add(input.readLine().toString()); 
		  } while (line != null);
		
		
		
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



}
