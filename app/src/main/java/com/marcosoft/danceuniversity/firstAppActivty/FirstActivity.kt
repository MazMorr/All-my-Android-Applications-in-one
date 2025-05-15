package com.marcosoft.danceuniversity.firstAppActivty

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcosoft.danceuniversity.R
import com.marcosoft.danceuniversity.firstAppActivty.imcCalculator.IMCActivity
import com.marcosoft.danceuniversity.firstAppActivty.toDoList.TodoActivity
import kotlin.jvm.java

class FirstActivity : AppCompatActivity() {

    private lateinit var txtView: AppCompatTextView
    private lateinit var cvIMC: CardView
    private lateinit var cvToDo: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.first_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents(){
        txtView= findViewById<AppCompatTextView>(R.id.txtHello)
        cvIMC=findViewById<CardView>(R.id.cvIMC)
        cvToDo= findViewById<CardView>(R.id.cvToDo)
    }

    private fun initListeners(){
        cvIMC.setOnClickListener {
            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
        }

        cvToDo.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }

}