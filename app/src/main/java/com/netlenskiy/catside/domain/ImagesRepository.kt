package com.netlenskiy.catside.domain

import com.netlenskiy.catside.domain.entities.response.images.Image

interface ImagesRepository {
    fun loadImages(
        breedId: String,
        categoryId: String,
        page: Int,
        limit: Int,
        callback: ((List<Image>) -> Unit)? = null
    )
}