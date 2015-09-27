package activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.htammare.assignment2_gridimagesearch.R;

public class filter extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        /**
         filtermodel filters = (filtermodel) getIntent().getParcelableExtra("fildata");
         String pack = filters.getColorFilter().toString();
         String packs = filters.getColorFilter();
         //String uri = filter.colorFilter.toString();
         **/


        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setTitle(" Filters");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.imagesize_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.colorfilter_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.imgtype_array, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);


        setSpinnerToValue(spinner, getIntent().getStringExtra("imageSize"));
        setSpinnerToValue(spinner2, getIntent().getStringExtra("colorFilter"));
        setSpinnerToValue(spinner3, getIntent().getStringExtra("imageType"));
        tv = (TextView) findViewById(R.id.editText);
        tv.setText(getIntent().getStringExtra("siteSearch"));


}





    public void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void savedata(View view) {



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        tv = (TextView) findViewById(R.id.editText);


        Intent sendresults = new Intent(filter.this,MainActivty_Search.class);
        sendresults.putExtra("imageSize",spinner.getSelectedItem().toString());
        sendresults.putExtra("colorFilter",spinner2.getSelectedItem().toString());
        sendresults.putExtra("imageType",spinner3.getSelectedItem().toString());
        sendresults.putExtra("siteSearch", tv.getText().toString());
        Log.e("SEARCH STRING",tv.getText().toString());
        sendresults.putExtra("code", 20);
        setResult(RESULT_OK, sendresults);
        finish();






    }
}
