package com.gabe.hosanna.views

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gabe.hosanna.R
import com.gabe.hosanna.adapter.CustomAdapter
import com.gabe.hosanna.model.FilterData
import com.gabe.hosanna.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {

    var REQUEST_CODE_WRITE_STORAGE_PERMISION = 105


    lateinit var articleViewModel: MainActivityViewModel
    lateinit var mAdapter: CustomAdapter
    lateinit var imageList : ArrayList<FilterData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        checkStoragePermissions(this);
        initialization()

        getImageData()

    }


    fun initialization(){

        imageList = ArrayList()
        mAdapter = CustomAdapter(this,imageList)

        simpleGridView.adapter = mAdapter


        simpleGridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            //here you can use the position to determine what checkbox to check
            //this assumes that you have an array of your checkboxes as well. called checkbox
//            checkBox[position].setChecked(!checkBox.isChecked())
        })


        articleViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }


    fun getImageData(){


        articleViewModel.getFilteredData().observe(this, Observer {
            imageList.addAll(it)
            progressBar.visibility = View.GONE
            mAdapter.notifyDataSetChanged()
            Log.d("MainActivity",it.size.toString())
        })

    }
    private fun checkStoragePermissions(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION)
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION)
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_STORAGE_PERMISION)
                    }
                }
            } else {


            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_STORAGE_PERMISION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    }
                }
            }

        }



}