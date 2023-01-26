package esra.avsar.besinlerkitabi.model

import com.google.gson.annotations.SerializedName

/**
 * Created by EsraAvsar on 26.01.2023.
 */
data class Besin(
    @SerializedName("isim")
    val besinIsim: String?,
    @SerializedName("kalori")
    val besinKalori: String?,
    @SerializedName("karbonhidrat")
    val besinKarbonhidrat: String?,
    @SerializedName("protein")
    val besinProtein: String?,
    @SerializedName("yag")
    val besinYag: String?,
    @SerializedName("gorsel")
    val besinGorsel: String?
)