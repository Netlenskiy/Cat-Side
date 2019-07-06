package com.netlenskiy.catside.domain.entities.response.breeds

import com.google.gson.annotations.Expose

data class Breed(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val temperament: String,
    @Expose val description: String,
    @Expose val wikipedia_url: String?
)