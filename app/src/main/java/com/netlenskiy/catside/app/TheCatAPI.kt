package com.netlenskiy.catside.app

import com.netlenskiy.catside.domain.entities.response.categories.Category
import com.netlenskiy.catside.domain.entities.response.images.Image
import com.netlenskiy.catside.domain.entities.response.votes.Vote
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatAPI {

    @GET("/v1/categories")
    fun getCategories(): Observable<Response<List<Category>>>

    @GET("/v1/votes")
    fun getVotes(@Query("sub_id") subId: String): Observable<Response<List<Vote>>>

    @GET("/v1/images/search")
    fun getImages(
        @Query("breed_id") breedId: String,
        @Query("category_id") categoryId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Observable<Response<List<Image>>>
}