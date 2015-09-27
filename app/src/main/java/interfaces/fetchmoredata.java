package interfaces;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.inflateadapater;
import cz.msebera.android.httpclient.Header;
import models.datamodel;

/**
 * Created by htammare on 9/26/2015.
 */

/**
 * Created by htammare on 9/25/2015.
 */
public class fetchmoredata {
    //String URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    public ArrayList<datamodel> datamodels;
    String URLModified;

    public final void getData(String URL,int Offset,final inflateadapater adapter,final ArrayList<datamodel> al){
        //Log.e("2....","1");
        URLModified = URL+"&start="+Offset;
        //URLModified = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=usa&rsz=8&start=8";
        Log.e("LASTLLLLLLL", URLModified);
        datamodels = new ArrayList<datamodel>();
        // Log.e("3....",URLModified);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //Log.e("4....","1");
        asyncHttpClient.get(URLModified, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseData) {
                JSONArray localarraylsit = null;
                try {
                    //Log.e("2","1");
                    // Log.e("2.1",responseData.toString());
                    JSONObject response = responseData.getJSONObject("responseData");
                    JSONArray results = response.getJSONArray("results");
                    //al.clear();
                    //adapter.clear();
                    //adapter.notifyDataSetChanged();
                    //Log.e("2.2", "1");
                    //Log.e("PREEEE CHECK111111", Integer.toString(al.size()));

                    for(int i=0;i<results.length();i++)
                    {
                        //Log.e("3","1");
                        datamodel tempdatamodel = new datamodel();
                        JSONObject currentdata = results.getJSONObject(i);
                        tempdatamodel.url = currentdata.getString("url");
                        tempdatamodel.titleNoFormatting = currentdata.getString("titleNoFormatting");
                        tempdatamodel.tbUrl = currentdata.getString("tbUrl");

                        Log.e("URLLLLLLLLLLLLLLL", tempdatamodel.url);
                        datamodels.add(tempdatamodel);
                        //Log.e("STRING", datamodels.get(i).titleNoFormatting.toString());
                        //al.add(tempdatamodel);
                        adapter.add(tempdatamodel);
                        //Log.e("FINAL CHECK2222222222", Integer.toString(al.size()));

                        //adapter.add(tempdatamodel);
                        //adapter.notifyDataSetChanged();
                    }
                    //Log.e("3333333333", Integer.toString(al.size()));
                    //adapter.notifyDataSetChanged();



                }catch(Exception e)
                {
                    Log.e("Parsing Error","Unable to Parse Data");

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {

            }
        });
    }

}
