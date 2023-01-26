package esra.avsar.besinlerkitabi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import esra.avsar.besinlerkitabi.model.Besin

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinListesiViewModel : ViewModel() {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()

    fun refreshData() {
        val muz = Besin("Muz", "100", "10", "5", "1", "www.test.com")
        val cilek = Besin("Çilek", "200", "20", "10", "2", "www.test.com")
        val elma = Besin("Elma", "300", "30", "15", "3", "www.test.com")

        val besinListesi = arrayListOf<Besin>(muz, cilek, elma)

        besinler.value = besinListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }
}