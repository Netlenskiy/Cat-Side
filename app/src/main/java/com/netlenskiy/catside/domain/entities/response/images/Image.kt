package com.netlenskiy.catside.domain.entities.response.images

import com.google.gson.annotations.Expose
import com.netlenskiy.catside.domain.entities.response.breeds.Breed
import com.netlenskiy.catside.domain.entities.response.categories.Category

data class Image(
    @Expose val id: String,
    @Expose val url: String,
    @Expose val width: Int,
    @Expose val height: Int,
    @Expose val breeds: List<Breed>,
    @Expose val categories: List<Category>?
)