package esra.avsar.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import esra.avsar.besinlerkitabi.R
import esra.avsar.besinlerkitabi.viewmodel.BesinDetayiViewModel
import kotlinx.android.synthetic.main.fragment_besin_detayi.*

class BesinDetayiFragment : Fragment() {

    private lateinit var viewModel : BesinDetayiViewModel
    private var besinId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_detayi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl()

        arguments?.let {
            besinId = BesinDetayiFragmentArgs.fromBundle(it).besinId
            println(besinId)
        }

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin ->
            besin?.let {
                tvDetayBesinIsim.text = it.besinIsim
                tvDetayBesinKalori.text = it.besinKalori
                tvDetayBesinKarbonhidrat.text = it.besinKarbonhidrat
                tvDetayBesinProtein.text = it.besinProtein
                tvDetayBesinYag.text = it.besinYag
            }

        })
    }
}