package esra.avsar.besinlerkitabi.servis

import esra.avsar.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by EsraAvsar on 26.01.2023.
 */
interface BesinAPI {

    //GET, POST

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    //BASE_URL -> https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<Besin>>
}