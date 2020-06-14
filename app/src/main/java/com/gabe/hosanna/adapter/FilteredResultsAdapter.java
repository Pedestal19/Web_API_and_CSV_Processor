package com.gabe.hosanna.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabe.hosanna.R;
import com.gabe.hosanna.model.FilteredResults;

import java.util.List;

public class FilteredResultsAdapter extends BaseAdapter {

    LayoutInflater inflter;

    private Context mContext;
    private List<FilteredResults> dataItems;
    public FilteredResultsAdapter(Context context, List<FilteredResults> getList){
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
        convertView = inflter.inflate(R.layout.filtered_results_row_item_2, null); // inflate the layout
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon); // get the reference of ImageView
//        icon.setImageResource(logos[i]); // set logo images

//        Picasso.with(mContext).load(dataItems.get(position).getPreviewURL()).placeholder(R.drawable.download).into(icon);
        TextView gender = (TextView) convertView.findViewById(R.id.gender); // get the reference of ImageView
        TextView country = (TextView) convertView.findViewById(R.id.country); // get the reference of ImageView
        TextView fullname = (TextView) convertView.findViewById(R.id.fullname); // get the reference of ImageView
        TextView email = (TextView) convertView.findViewById(R.id.email); // get the reference of ImageView
        TextView carmake = (TextView) convertView.findViewById(R.id.carmake); // get the reference of ImageView
        TextView bio = (TextView) convertView.findViewById(R.id.bio);
        TextView jobtitle = (TextView) convertView.findViewById(R.id.jobtitle);



        country.setText("Country: "+dataItems.get(position).getCountry());
        gender.setText("Gender: "+dataItems.get(position).getGender());
        fullname.setText("Fullname: "+dataItems.get(position).getFirstName()+" "+dataItems.get(position).getLastName());
        email.setText("email: "+dataItems.get(position).getEmail());
        carmake.setText("Car Make, Colour & Year: "+dataItems.get(position).getCarModel()+" ,"+dataItems.get(position).getCarColour()+", "+dataItems.get(position).getCarModelYear());
        bio.setText("Bio: "+dataItems.get(position).getBio());
        jobtitle.setText("Job Title: "+dataItems.get(position).getJobTitle());



        return convertView;
    }
}
