package com.test.energyaustralia.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Bands(
    @SerializedName("name")
    val name: String?,
    @SerializedName("recordLabel")
    val recordLabel: String?
) : Serializable