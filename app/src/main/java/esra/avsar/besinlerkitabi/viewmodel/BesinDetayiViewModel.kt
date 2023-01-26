package esra.avsar.besinlerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import esra.avsar.besinlerkitabi.model.Besin

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinDetayiViewModel : ViewModel() {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl() {
        val muz = Besin("Muz", "100", "10", "5", "1", "www.test.com")
        besinLiveData.value = muz
    }
}