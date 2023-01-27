package esra.avsar.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import esra.avsar.besinlerkitabi.model.Besin
import esra.avsar.besinlerkitabi.servis.BesinDatabase
import kotlinx.coroutines.launch

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinDetayiViewModel(application: Application) : BaseViewModel(application) {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int) {

        launch {
            val dao = BesinDatabase(getApplication()).besinDAO()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }
}