package com.marcosoft.danceuniversity.firstAppActivty

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcosoft.danceuniversity.R

class ResultActivity : AppCompatActivity() {
    private lateinit var txtResult: AppCompatTextView
    private lateinit var btnGoBack: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.result_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val result:Double = intent.extras?.getDouble("IMC_RESULT") ?: -1.0
        initComponents()
        showResult()
    }

    private fun initComponents(){
        txtResult= findViewById<AppCompatTextView>(R.id.txtResult)
        btnGoBack= findViewById<AppCompatButton>(R.id.btnGoBack)
    }
    private fun initListeners(){
        btnGoBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun showResult(){

    }
}