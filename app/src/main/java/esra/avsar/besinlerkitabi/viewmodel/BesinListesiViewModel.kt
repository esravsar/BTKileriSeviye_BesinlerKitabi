package esra.avsar.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import esra.avsar.besinlerkitabi.model.Besin
import esra.avsar.besinlerkitabi.servis.BesinAPIServis
import esra.avsar.besinlerkitabi.servis.BesinDatabase
import esra.avsar.besinlerkitabi.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinListesiViewModel(application: Application) : BaseViewModel(application) {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()
    private val guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())

    fun refreshData() {

        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani) {
            //Sqlite'tan çek
            verileriSQLitetanAl()
        } else {
            verileriInternettenAl()
        }
    }

    fun refreshFromInternet() {
        verileriInternettenAl()
    }

    private fun verileriSQLitetanAl() {
        besinYukleniyor.value = true
        
        launch { 
            val besinListesi = BesinDatabase(getApplication()).besinDAO().getAllBesin()    
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(), "Besinleri Room'dan Aldık", Toast.LENGTH_LONG).show()
        }
    }

    private fun verileriInternettenAl() {
        besinYukleniyor.value = true

        //IO, Default, UI

        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Besin>>() {
                    override fun onSuccess(t: List<Besin>) {
                        //Başarılı olursa
                        sqliteSakla(t)
                        Toast.makeText(getApplication(), "Besinleri Internet'ten Aldık", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        besinHataMesaji.value = true
                        besinYukleniyor.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun besinleriGoster(besinlerListesi : List<Besin>) {
        besinler.value = besinlerListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }

    private fun sqliteSakla(besinListesi : List<Besin>) {
        launch {
            val dao = BesinDatabase(getApplication()).besinDAO()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())
            var i = 0
            while (i < besinListesi.size) {
                besinListesi[0].uuid = uuidListesi[i].toInt()
                i += 1
            }
            besinleriGoster(besinListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}