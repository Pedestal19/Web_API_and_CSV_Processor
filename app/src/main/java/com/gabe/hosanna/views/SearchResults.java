package com.gabe.hosanna.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gabe.hosanna.R;
import com.gabe.hosanna.adapter.FilteredResultsAdapter;
import com.gabe.hosanna.model.FilteredResults;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchResults extends AppCompatActivity {
    private TextView tv, tv2, tvWait;
    private int i=0;
    private int j=0;
    private String searchGender, searchColour, searchCountry;
    private ArrayList<FilteredResults> filteredResults = new ArrayList<>();
    private FilteredResultsAdapter mAdapter;
    private GridView gridView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);
        tv = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        tvWait = (TextView) findViewById(R.id.tv_wait);
        gridView = (GridView) findViewById(R.id.simpleGridView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Intent intent = getIntent();

        searchGender = intent.getStringExtra("gender");
        searchCountry = intent.getStringExtra("country");
        searchColour = intent.getStringExtra("colour");


        processData();

    }

    private void processData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                getFilteredResults(searchGender, searchCountry, searchColour);

            }
        });

    }

    public FilteredResultsAdapter getFilteredResults(String genderParam, String countryParam, String colourPram) {
        Reader in = null;
        try {
//            Log.e(" gefile dir  ", Environment.getExternalStorageDirectory().toString());
            in = new FileReader(Environment.getExternalStorageDirectory()+"/decagon/car_owners_data.csv");
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {

                String gender = record.get(8);
                String carColour = record.get(7);
                String country = record.get(4);
                Log.e(" results list "+i+" ","from csv: " +gender+ " " +carColour+" "+country);
                i++;
                if(gender.toLowerCase().equals(genderParam) || gender.equalsIgnoreCase("")){
                    ArrayList<String> splitedCountry = new ArrayList<>(Arrays.asList(countryParam.split(",")));
                    ArrayList<String> splitedColours = new ArrayList<>(Arrays.asList(colourPram.split(",")));

//                    String[] myData = searchCountry.split("/");

                    for(String pCountry : splitedCountry){
                        if(pCountry.equalsIgnoreCase(country) || pCountry.equals("")){
                            for (String pColour : splitedColours){
                                if(pColour.equalsIgnoreCase(carColour)|| pColour.equals("")){
                                    String first_name = record.get(1);
                                    String last_name = record.get(2);
                                    String email = record.get(3);
                                    String car_model = record.get(5);
                                    String car_model_year = record.get(6);
                                    String job_title = record.get(9);
                                    String bio = record.get(10);
                                    Log.e(" criteria passed "+i+" ","result found: ");
                                    j++;
                                    filteredResults.add(new FilteredResults(first_name,last_name,email,country, car_model, car_model_year, carColour, gender,job_title, bio));
                                    Log.e(" added to results "+i+" ",":-: ");
                                    break;
                                }
                            }
                        }
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("Total Records: "+ i);
                        tv2.setText("Search Results: "+ j);

                    }
                });
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        mAdapter = new FilteredResultsAdapter(this,filteredResults);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("Total Records: "+ i);
                tv2.setText("Search Results: "+ j);
                progressBar.setVisibility(View.GONE);
                gridView.setAdapter(mAdapter);
                tvWait.setVisibility(View.GONE);
            }
        });

        return mAdapter;
    }


    public void readf(View view) {




    }


}
