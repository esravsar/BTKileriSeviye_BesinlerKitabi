package esra.avsar.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import esra.avsar.besinlerkitabi.R
import esra.avsar.besinlerkitabi.databinding.BesinRecyclerRowBinding
import esra.avsar.besinlerkitabi.model.Besin
import esra.avsar.besinlerkitabi.util.gorselIndir
import esra.avsar.besinlerkitabi.util.placeHolderYap
import esra.avsar.besinlerkitabi.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinRecyclerAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>() {

    class BesinViewHolder(var view : BesinRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.besin_recycler_row, parent, false)
        val view = DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater, R.layout.besin_recycler_row, parent, false)
        return BesinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

        holder.view.besin = besinListesi[position]
        /*
        holder.itemView.tvItemBesinIsmi.text = besinListesi.get(position).besinIsim
        holder.itemView.tvItemBesinKalorisi.text = besinListesi.get(position).besinKalori

        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment()
            action.besinId = besinListesi.get(position).uuid
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.ivItemImage.gorselIndir(besinListesi.get(position).besinGorsel, placeHolderYap(holder.itemView.context))
        */
    }

    fun besinListesiniGuncelle(yeniBesinListesi: List<Besin>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }
}