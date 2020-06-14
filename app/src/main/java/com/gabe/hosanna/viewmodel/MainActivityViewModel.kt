package com.gabe.hosanna.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gabe.hosanna.model.FilterData
import com.gabe.hosanna.repository.FilterDataRepository

class MainActivityViewModel : ViewModel()  {


      val articleRepository: FilterDataRepository
     val  articleResponseLiveData: LiveData<List<FilterData>>
    init {

        articleRepository = FilterDataRepository()
        this.articleResponseLiveData =
            articleRepository.getImagesList()

    }

    fun getFilteredData(): LiveData<List<FilterData>> {
        return articleResponseLiveData
    }


}