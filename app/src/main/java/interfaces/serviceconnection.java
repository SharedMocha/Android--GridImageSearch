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
import models.globalvalues;

/**
 * Created by htammare on 9/25/2015.
 */
public class serviceconnection {
    String URL = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";
    public ArrayList<datamodel> datamodels;
    String URLModified;

    public final void getData(final String searchstring,final String imageSize,final String colorFilter,final String imageType,final String siteSearch,final inflateadapater adapter,final ArrayList<datamodel> al){
        //Log.e("2....","1");
        String sizeoflist = "&rsz=8";
        if(imageSize.trim()!=""){
            String tempimageSize = "&imgsz="+imageSize;
            String tempcolorfilter = "&imgcolor="+colorFilter;
            String tempiamgetype = "&imgtype="+imageType;
            String tempsitesearch = "&as_sitesearch="+siteSearch;



            URLModified = URL+searchstring+tempimageSize+tempcolorfilter+tempiamgetype+tempsitesearch+sizeoflist;

        }else{
            URLModified = URL+searchstring+sizeoflist;
        }

        globalvalues.setOffset(8);
        globalvalues.setURL(URLModified);

        //Log.e("URLLLLLLLLLLLLLLL11111", globalvalues.getOffset().toString());
        //Log.e("URLLLLLLLLLLLLLLL2222", globalvalues.getURL());
        //Log.e("URLLLLLLLLLLLLLLL123", URLModified);
        //Log.e("URLLLLLLLLLLLLLLL123", URLModified);

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
                    adapter.clear();
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
