package esra.avsar.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import esra.avsar.besinlerkitabi.R
import esra.avsar.besinlerkitabi.model.Besin
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

/**
 * Created by EsraAvsar on 26.01.2023.
 */
class BesinRecyclerAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>() {

    class BesinViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.besin_recycler_row, parent, false)
        return BesinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemView.tvItemBesinIsmi.text = besinListesi.get(position).besinIsim
        holder.itemView.tvItemBesinKalorisi.text = besinListesi.get(position).besinKalori
        //görsel kısmı eklenecek
    }

    fun besinListesiniGuncelle(yeniBesinListesi: List<Besin>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }
}