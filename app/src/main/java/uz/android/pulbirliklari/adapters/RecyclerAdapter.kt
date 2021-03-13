package uz.android.pulbirliklari.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_excample.view.*
import uz.android.pulbirliklari.R
import uz.android.pulbirliklari.model.Currencyy

class RecyclerAdapter(val list: List<Currencyy>, val onClick: (currency: Currencyy) -> Unit):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun onBind(currency: Currencyy) {
            itemView.apply {
                data.text = currency.Date
                dollar.text = currency.CcyNm_UZ
                som.text = currency.Rate
            }
            itemView.setOnClickListener {
                onClick(currency)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_excample, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.onBind(list[position])
    }
    override fun getItemCount(): Int = list.size

}