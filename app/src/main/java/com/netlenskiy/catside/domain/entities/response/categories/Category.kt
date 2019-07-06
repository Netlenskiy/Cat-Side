package com.netlenskiy.catside.domain.entities.response.categories

import com.google.gson.annotations.Expose

data class Category(
    @Expose val id: Int,
    @Expose val name: String
)