package com.test.energyaustralia.data.model

import com.google.gson.annotations.SerializedName

class APIResponse(
    @SerializedName("bands")
    val bands: List<Bands>,
    @SerializedName("name")
    val festivalName: String?
)