package activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.htammare.assignment2_gridimagesearch.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import adapters.inflateadapater;
import interfaces.fetchmoredata;
import interfaces.serviceconnection;
import models.datamodel;
import models.filtermodel;
import models.globalvalues;

public class MainActivty_Search extends AppCompatActivity {
    public Button button;
    public EditText editText;
    public GridView gridView;
    public ArrayList<datamodel> al;
    public inflateadapater adapter;
    public serviceconnection connection;
    private Toolbar toolbar;
    public  ArrayList<String> filters ;
    public  ArrayList<String> writefilters ;

    public filtermodel filtersfinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activty__search);
        gridView = (GridView) findViewById(R.id.gv_master);
        al = new ArrayList<datamodel>();
        adapter = new inflateadapater(this,al);
        button = (Button) findViewById(R.id.btn_search);
        editText = (EditText) findViewById(R.id.edSearchString);
        gridView.setAdapter(adapter);


                //toolbar = (Toolbar) findViewById(R.id.toolbar);
                //setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setTitle(" Image Search");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        gridView.setOnScrollListener(new endlessscroll() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.e("sammmmmmmmmm","111111111111111");
                customLoadMoreDataFromApi();

            }
        });

    }
    public void customLoadMoreDataFromApi() {
        fetchmoredata moredata = new fetchmoredata();

        String URL = globalvalues.getURL();
        int offset = globalvalues.getOffset();

        moredata.getData(URL, offset, adapter, al);
        int offsetcounter =  offset+8;
        globalvalues.setOffset(offsetcounter);
        //Log.e("######",globalvalues.getOffset());

        //connection.getData(editText.getText().toString(), filtersfinal.imageSize, filtersfinal.colorFilter, filtersfinal.imageType, filtersfinal.siteSearch,adapter, al);
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main_activty__search, menu);




         //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.mipmap.ic_launcher);
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);
        //toolbar.setTitle("sammmmmm");
         //android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setLogo(R.mipmap.ic_launcher);
        //actionBar.setLogo(R.mipmap.ic_launcher);
        //actionBar.setTitle("ppppppppppppppppppp");
        //actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);


        //toolbar.setDisplayUseLogoEnabled(true);
        //toolbar.setDisplayShowHomeEnabled(true);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    public void fetchdetails(View view) {
        //editText.getText().toString();
        //Toast.makeText(this,"Hi",Toast.LENGTH_SHORT).show();

        readfromfile();
        connection = new serviceconnection();
        //Toast.makeText(this,"Hi2",Toast.LENGTH_SHORT).show();
        connection.getData(editText.getText().toString(),filtersfinal.imageSize,filtersfinal.colorFilter,filtersfinal.imageType,filtersfinal.siteSearch,adapter, al);

        //gridView.setAdapter(adapter);
        //gridView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();


    }

    public void onFilterSelected(MenuItem item) {

        readfromfile();

        //Log.e("00", filtersfinal.imageSize);
        //Log.e("11", filtersfinal.colorFilter);
        //Log.e("22", filtersfinal.imageType);
        //Log.e("33", filtersfinal.siteSearch);


         /**Intent i = new Intent(this,filter.class);
         filtermodel fildata = new filtermodel();
         fildata = filtersfinal;
         i.putExtra("filterData", fildata);
         startActivityForResult(i, 1234);
         **/

        Intent i = new Intent(this,filter.class);
        i.putExtra("imageSize", filtersfinal.imageSize);
        i.putExtra("colorFilter", filtersfinal.colorFilter);
        i.putExtra("imageType", filtersfinal.imageType);
        i.putExtra("siteSearch", filtersfinal.siteSearch);

        //Log.e("000", filtersfinal.imageSize);
        //Log.e("111", filtersfinal.colorFilter);
        //Log.e("222", filtersfinal.imageType);
        //Log.e("333", filtersfinal.siteSearch);


        startActivityForResult(i, 1234);


        }

    public void readfromfile(){
        filters = new ArrayList<String>();
        File filedir = getFilesDir();
        File file = new File(filedir,"Filters.txt");

        try{
            filters = new ArrayList<String>(FileUtils.readLines(file));
            //FileUtils.writeLines(file,filters);
        }catch(IOException e){
            filters = new ArrayList<String>();
            Toast.makeText(this, "Filters Not added to list.Please try again by relaunching the app", Toast.LENGTH_SHORT).show();
            Log.e("Caused from Filters", "Unable to Write to File");
        }
        filtersfinal = new filtermodel();

        if (filters.size()>0) {
            filtersfinal.imageSize = filters.get(0);
            filtersfinal.colorFilter = filters.get(1);
            filtersfinal.imageType = filters.get(2);
            filtersfinal.siteSearch = filters.get(3);

        }else{
            filtersfinal.imageSize  = "";
            filtersfinal.colorFilter  = "";
            filtersfinal.imageType  = "";
            filtersfinal.siteSearch  = "";
        }
    }

    public void writetoFile(){
        File filedir = getFilesDir();
        File file = new File(filedir,"Filters.txt");
        try{
            FileUtils.writeLines(file,writefilters);
        }catch(IOException e){
            Toast.makeText(this, "Filters Not added to list.Please try again by relaunching the app", Toast.LENGTH_SHORT).show();
            Log.e("Caused from Filters", "Unable to Write to File");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
         //Toast.makeText(this,data.getExtras().getString("siteSearch").toString(),Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK ) {
            writefilters = new ArrayList<String>();
            // Extract name value from result extras
            //String name = data.getExtras().getString("ModifiedText");
            //int code = data.getExtras().getInt("code", 0);
            writefilters.add(data.getExtras().getString("imageSize"));
            writefilters.add(data.getExtras().getString("colorFilter"));
            writefilters.add(data.getExtras().getString("imageType"));
            writefilters.add(data.getExtras().getString("siteSearch"));
            //Log.e("0", writefilters.get(0));
            //Log.e("1",writefilters.get(1));
            //Log.e("2",writefilters.get(2));
            //Log.e("3",writefilters.get(3));

            writetoFile();
            Log.e("sizeee",Integer.toString(filters.size()));
        }
    }

}

