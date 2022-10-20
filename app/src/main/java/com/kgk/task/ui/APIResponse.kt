package com.kgk.task.ui

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("businesses") val businessesData: List<BusinessesData>,
    @SerializedName("total") val total: Int,
)

data class BusinessesData(
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("location") val location: LocationData,
    @SerializedName("rating") val rating: Double,
    @SerializedName("is_closed") val is_closed: Boolean,
)

data class LocationData(
    @SerializedName("display_address") val display_address: ArrayList<String>,
)

