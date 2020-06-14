package com.gabe.hosanna.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabe.hosanna.R;
import com.gabe.hosanna.views.SearchResults;
import com.gabe.hosanna.model.FilterData;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    LayoutInflater inflter;

    private Context mContext;
    private List<FilterData> dataItems;
    public CustomAdapter(Context context, List<FilterData> getList){
        dataItems = getList;
        mContext = context;

        inflter = (LayoutInflater.from(context));

    }


    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.row_item, null); // inflate the layout
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon); // get the reference of ImageView
//        icon.setImageResource(logos[i]); // set logo images

//        Picasso.with(mContext).load(dataItems.get(position).getPreviewURL()).placeholder(R.drawable.download).into(icon);
        TextView createdAt = (TextView) convertView.findViewById(R.id.createdat); // get the reference of ImageView
        TextView gender = (TextView) convertView.findViewById(R.id.gender); // get the reference of ImageView
        TextView country = (TextView) convertView.findViewById(R.id.country); // get the reference of ImageView
        TextView colours = (TextView) convertView.findViewById(R.id.colours); // get the reference of ImageView

        final String genderValue = dataItems.get(position).getGender();
        final ArrayList<String> coloursValue = dataItems.get(position).getColors();
        final ArrayList<String> countryValue = dataItems.get(position).getColors();


        createdAt.setText("Date: "+dataItems.get(position).getCreatedAt());
        gender.setText("Gender "+dataItems.get(position).getGender());

        /*String ctlist="";
        for(int i=1; i < dataItems.get(position).getColors().size(); i++){
            ctlist = dataItems.get(position).getColors().get(i).toString()+",";
        }
*/
        country.setText("Country: All");
        colours.setText("Colours: All");

        final String commaSeparatedCountries = android.text.TextUtils.join(",", dataItems.get(position).getCountries());
        final String commaSeparatedColours = android.text.TextUtils.join(",", dataItems.get(position).getColors());



        if (dataItems.get(position).getCountries() != null && !(dataItems.get(position).getCountries().isEmpty())) {
            country.setText("Country: "+commaSeparatedCountries);
        }
        if (dataItems.get(position).getColors() != null && !(dataItems.get(position).getColors().isEmpty())) {
            colours.setText("Colours: "+commaSeparatedColours);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "hey hey "+genderValue, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SearchResults.class);
                intent.putExtra("gender", genderValue);
                intent.putExtra("country", commaSeparatedCountries);
                intent.putExtra("colour", commaSeparatedColours);


                mContext.startActivity(intent);
            }
        });


        return convertView;
    }
}
