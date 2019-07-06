package com.netlenskiy.catside.domain

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.netlenskiy.catside.app.App
import com.netlenskiy.catside.domain.entities.response.images.Image
import javax.inject.Inject

class FeedViewModel : AndroidViewModel {

    constructor(application: Application) : super(application) {
        App.getInstance().appComponent.inject(this)
    }

    private var page: Int = 0

    private var limit: Int = 5

    val images = MutableLiveData<List<Image>>()

    val isFirstLoading = ObservableBoolean(false)

    val isLoading = ObservableBoolean(false)

    @Inject
    lateinit var imagesRepository: ImagesRepository

    fun loadImages(breedId: String = "", categoryId: String = "") {
        isFirstLoading.set(page == 0)
        isLoading.set(true)
        imagesRepository.loadImages(breedId, categoryId, page++, limit) {
            images.value = it
            isFirstLoading.set(false)
            isLoading.set(false)
        }
    }
}
