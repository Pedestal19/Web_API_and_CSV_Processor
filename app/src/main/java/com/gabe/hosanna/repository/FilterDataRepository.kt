package com.gabe.hosanna.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabe.hosanna.model.FilterData
import com.gabe.hosanna.utils.RetrofitClientInstance
import com.gabe.hosanna.utils.endpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilterDataRepository {

    lateinit var imageList : MutableLiveData<List<FilterData>>
    private val TAG = FilterDataRepository::class.java!!.getSimpleName()
    private val apiRequest: endpoints



    init {

        apiRequest = RetrofitClientInstance.getRetrofitInstance().create(endpoints::class.java)
    }
    fun getImagesList() : LiveData<List<FilterData>>{

        imageList = MutableLiveData()




        apiRequest.getFilterData().enqueue(object : Callback<List<FilterData>>{
            override fun onResponse(call: Call<List<FilterData>>, response: Response<List<FilterData>>) {
                Log.e(TAG,"******"+response.code().toString())
                Log.e(TAG,"******"+response.body().toString())

                    imageList.value = response.body()!!.toMutableList()

            }

            override fun onFailure(call: Call<List<FilterData>>, t: Throwable) {
                Log.e(TAG,"******errrr\n"+t.message)

              }
        })


        return imageList
    }
}