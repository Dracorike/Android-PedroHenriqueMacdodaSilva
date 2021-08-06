package com.petruciostech.android_pedrohenriquemacdodasilva.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.petruciostech.android_pedrohenriquemacdodasilva.R
import com.petruciostech.android_pedrohenriquemacdodasilva.databinding.ActivityMainBinding
import com.petruciostech.android_pedrohenriquemacdodasilva.model.DataHelper
import com.petruciostech.android_pedrohenriquemacdodasilva.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG_CHOOISE = "##escolha##"
    }
    private lateinit var viewModel:MainActivityViewModel
    private lateinit var bind:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory(application)
            .create(MainActivityViewModel::class.java)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        initComponents()
        initButtons()
    }

    private fun initComponents(){
        //configuração dos botões
        bind.buttonOrigemChange.setOnClickListener { startList("origem") }
        bind.buttonDestinyChange.setOnClickListener { startList("destino") }
        bind.convertValor.setOnClickListener {  }
    }
    private fun initButtons(){
        //dando título aos botões de origem e destino
        val intent = intent
        if(intent != null){
            val helper = intent.getSerializableExtra("escolha") as DataHelper?
            bind.buttonOrigemChange.text = helper?.buttonOrigem
            bind.buttonDestinyChange.text = helper?.buttonDestino
        }
    }

    private fun startList(termo:String){
        val intent = Intent(this, ListValueActivity::class.java)
        intent.putExtra("escolha", DataHelper(
            bind.buttonOrigemChange.text.toString(),
            bind.buttonDestinyChange.text.toString()))
        intent.putExtra(TAG_CHOOISE, termo)
        startActivity(intent)
    }
}