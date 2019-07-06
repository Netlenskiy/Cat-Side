package com.netlenskiy.catside.domain.entities.response.votes

import com.google.gson.annotations.Expose

data class Vote(
    @Expose val id: Long,
    @Expose val image_id: String,
    @Expose val sub_id: String?,
    @Expose val value: Int?
)