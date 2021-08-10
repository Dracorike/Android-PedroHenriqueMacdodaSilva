package com.petruciostech.android_pedrohenriquemacdodasilva.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
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
    private lateinit var listCurrency:List<CurrencyResponseModel>
    private var listCurrencyFilter = arrayListOf<CurrencyResponseModel>()
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
        populateList()
        setToolBar()
    }

    private fun populateList(){
        viewModel.currencyList.observe(this, {
            try{
                listCurrency = viewModel.formarList(it.listCurrency.toList())
                listCurrencyFilter.addAll(listCurrency)
                populateRecyclerView()
            }catch (ex:Exception){
                populateRecyclerView()
            }

        })
    }

    private fun setToolBar(){
        setSupportActionBar(bind.toolbarListCurrencys)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar_list_currency, menu)
        val search:SearchView = menu?.findItem(R.id.search_currency)?.actionView as SearchView
        search.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                listCurrencyFilter.clear()
                listCurrency.forEach {
                    if(it.code.contains(newText.toString()) || it.name.contains(newText.toString())){
                        listCurrencyFilter.add(it)
                    }
                }
                populateRecyclerView()
                return false
            }

        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.sort_to_code -> {
                listCurrencyFilter.clear()
                listCurrencyFilter.addAll(listCurrency.sortedBy{ it.code })
                populateRecyclerView()
                true
            }
            R.id.sort_to_name -> {
                listCurrencyFilter.clear()
                listCurrencyFilter.addAll(listCurrency.sortedBy { it.name })
                populateRecyclerView()
                true
            }

            else -> false
        }

    }
    private fun populateRecyclerView(){
        val intent = intent
        val termo = intent.getStringExtra("##escolha##") as String
        val helper = intent.getSerializableExtra("escolha") as DataHelper

        bind.recyclerListCurrency.apply {
            if (intent.getStringExtra("##escolha##")!! == "destino") {
                adapter = RecyclerCurrencyAdapter(
                    currencyList = listCurrencyFilter,
                    context = this@ListValueActivity,
                    termo = termo,
                    persiste = helper.buttonOrigem
                )
            } else {
                adapter = RecyclerCurrencyAdapter(
                    currencyList = listCurrencyFilter,
                    context = this@ListValueActivity,
                    termo = termo,
                    persiste = helper.buttonDestino
                )
            }
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

}