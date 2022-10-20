package com.kgk.task.network

import com.kgk.task.ui.HomeResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface APIMethods {

    @GET
    fun getHomeData(@Url url: String): Observable<Response<List<HomeResponse>>>

    @GET
    fun getData(
        @Header("Authorization") header: String, @Url url: String,
    ): Observable<Response<HomeResponse>>


    /*@POST(APIEndPoints.DOOR_STEP_API)
    fun getTokenVerify(
        @Header("Authorization") header: String, @Body map: MutableMap<String, String>
    ): Observable<Response<DoorStepVerifyToken>>*/
}