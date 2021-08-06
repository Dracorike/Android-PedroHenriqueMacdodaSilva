package com.petruciostech.android_pedrohenriquemacdodasilva.presenter.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petruciostech.android_pedrohenriquemacdodasilva.R
import com.petruciostech.android_pedrohenriquemacdodasilva.databinding.ItemRecyclerviewCurrencyBinding
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyResponseModel
import com.petruciostech.android_pedrohenriquemacdodasilva.model.DataHelper
import com.petruciostech.android_pedrohenriquemacdodasilva.presenter.MainActivity

class RecyclerCurrencyAdapter(private val currencyList:List<CurrencyResponseModel>, private val context:Context,
private val termo:String, private val persiste:String)
    :RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.textvNameCurrency.text = currencyList[position].name
        holder.bind.textvCodeCurrency.text = currencyList[position].code

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            if(termo == "origem") {
                intent.putExtra("escolha",
                    DataHelper(holder.bind.textvCodeCurrency.text.toString(), persiste))
            }else{
                intent.putExtra("escolha",
                    DataHelper(persiste, holder.bind.textvCodeCurrency.text.toString()))
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = currencyList.size
}

class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val bind = ItemRecyclerviewCurrencyBinding.bind(itemView)
}