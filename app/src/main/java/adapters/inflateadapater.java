package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.htammare.assignment2_gridimagesearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import activites.fullscreen;
import models.datamodel;

/**
 * Created by htammare on 9/25/2015.
 */
public class inflateadapater extends ArrayAdapter<datamodel>{

    ImageView onclickimageView;


    public class ViewHolder{
        ImageView imageView;
        TextView textView;

    }

    public inflateadapater(Context context, ArrayList<datamodel> responsedata) {
        super(context, 0, responsedata);
    }
    datamodel responsedata;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        responsedata = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_grid_details,parent,false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_showtitle);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_showimage);

            //viewHolder.textView.setTextColor();
            convertView.setTag(viewHolder);


        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(responsedata.titleNoFormatting);
        viewHolder.imageView.setImageResource(0);
        Picasso.with(getContext()).load(responsedata.url).placeholder(R.drawable.reload).into(viewHolder.imageView);




                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //datamodel dataToSend = new datamodel();
                        //dataToSend = responsedata;
                        Intent i = new Intent(getContext(), fullscreen.class);
                        //i.putExtra("myData", dataToSend);
                        String urls = getItem(position).url;
                        i.putExtra("myData", urls);

                        //Log.e("URL-SENDING", "-------------------");
                        getContext().startActivity(i);
                        //Log.e("URL-SENDING", "+++++++++++++++++++");


                    }
                });

        /**Picasso.with(getContext())
                .load(R.drawable.reload) // thumbnail url goes here
                .into(viewHolder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(getContext())
                                .load(responsedata.url) // image url goes here
                                .placeholder(viewHolder.imageView.getDrawable())
                                .into(viewHolder.imageView);
                    }

                    @Override
                    public void onError() {

                    }
                });
         */


        //Log.e("URI-URI", responsedata.url);




        return convertView;

    }

}
