package activites;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.htammare.assignment2_gridimagesearch.R;
import com.squareup.picasso.Picasso;

public class fullscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        //datamodel object = (datamodel) getIntent().getParcelableExtra("myData");
        //String uri = object.url.toString();
        String uri =  getIntent().getStringExtra("myData");
        ImageView iv = (ImageView) findViewById(R.id.iv_fullimage);
        iv.setImageResource(0);
        //Log.e("URI-ENTERED", uri);
        Picasso.with(this).load(uri).into(iv);

        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setTitle(" Details");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fullscreen, menu);
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
}
