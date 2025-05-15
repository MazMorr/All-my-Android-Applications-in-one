package com.marcosoft.danceuniversity.firstAppActivty.imcCalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcosoft.danceuniversity.R

class IMCResultActivity : AppCompatActivity() {
    private lateinit var txtResultIMC: AppCompatTextView
    private lateinit var txtResultExplanation: AppCompatTextView
    private lateinit var txtResultCategory: AppCompatTextView
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
        initListeners()
        initUI(result)
    }

    private fun initComponents(){
        txtResultIMC= findViewById<AppCompatTextView>(R.id.txtResultIMC)
        txtResultExplanation= findViewById<AppCompatTextView>(R.id.txtResultExplanation)
        txtResultCategory=findViewById<AppCompatTextView>(R.id.txtResultCategory)
        btnGoBack= findViewById<AppCompatButton>(R.id.btnGoBack)
    }
    private fun initListeners(){
        btnGoBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun initUI(result :Double){
        when(result){
            in 0.0 .. 18.5 ->{
                txtResultIMC.text= "$result"
                txtResultCategory.setTextColor(getColor(R.color.red))
                txtResultExplanation.text= "Tienes un peso corporal más bajo de lo normal. Debes comer un poco más."
                txtResultCategory.text= "Bajo peso"
            }
            in 18.51 .. 24.9 ->{
                txtResultIMC.text= "$result"
                txtResultCategory.setTextColor(getColor(R.color.green))
                txtResultExplanation.text= "Tienes un peso corporal normal. ¡Buen trabajo!"
                txtResultCategory.text= "Normal"

            }
            in 25.0 .. 34.99->{
                txtResultIMC.text= "$result"
                txtResultCategory.setTextColor(getColor(R.color.yellow))
                txtResultExplanation.text= "Tienes un peso corporal superior al normal. Debes empezar a hacer ejercicio."
                txtResultCategory.text= "Sobrepeso"
            }
            in 35.0 .. 100.0 ->{
                txtResultIMC.text= "$result"
                txtResultCategory.setTextColor(getColor(R.color.red))
                txtResultCategory.text= "Obesidad"
                txtResultExplanation.text= "Tienes un peso corporal demasiado alto. Debe ir al médico."
            }
        }
    }
}