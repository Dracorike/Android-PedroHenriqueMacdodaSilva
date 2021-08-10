package com.petruciostech.android_pedrohenriquemacdodasilva.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.petruciostech.android_pedrohenriquemacdodasilva.R
import com.petruciostech.android_pedrohenriquemacdodasilva.databinding.ActivityListValueBinding
import com.petruciostech.android_pedrohenriquemacdodasilva.model.CurrencyResponseModel
import com.petruciostech.android_pedrohenriquemacdodasilva.model.DataHelper
import com.petruciostech.android_pedrohenriquemacdodasilva.presenter.recyclerview.RecyclerCurrencyAdapter
import com.petruciostech.android_pedrohenriquemacdodasilva.viewmodel.ListValueViewModel

class ListValueActivity : AppCompatActivity() {
    private lateinit var viewModel:ListValueViewModel
    private lateinit var bind:ActivityListValueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_value)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory(application)
            .create(ListValueViewModel::class.java)
        bind = ActivityListValueBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.init()
        initComponents()
    }

    private fun initComponents(){
        populateRecyclerView()
        setToolBar()
    }

    private fun populateRecyclerView(){
        val intent = intent
        val termo = intent.getStringExtra("##escolha##") as String
        val helper = intent.getSerializableExtra("escolha") as DataHelper
        viewModel.currencyList.observe(this, {
            bind.recyclerListCurrency.apply {
                if(intent.getStringExtra("##escolha##")!! == "destino") {
                    adapter = RecyclerCurrencyAdapter(
                        currencyList = viewModel.formarList(it.listCurrency.toList()),
                        context = this@ListValueActivity,
                        termo = termo,
                        persiste = helper.buttonOrigem
                    )
                }else{
                    adapter = RecyclerCurrencyAdapter(
                        currencyList = viewModel.formarList(it.listCurrency.toList()),
                        context = this@ListValueActivity,
                        termo = termo,
                        persiste = helper.buttonDestino
                    )
                }
                layoutManager = LinearLayoutManager(applicationContext)
            }
        })
    }

    private fun setToolBar(){
        setSupportActionBar(bind.toolbarListCurrencys)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar_list_currency, menu)
        return true
    }

}