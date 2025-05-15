package com.marcosoft.danceuniversity.firstAppActivty.imcCalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.marcosoft.danceuniversity.R

class IMCActivity : AppCompatActivity() {

    private lateinit var btnResult: AppCompatButton
    private lateinit var rsHeight: RangeSlider
    private lateinit var txtHeight: AppCompatTextView
    private lateinit var txtWeight: AppCompatTextView
    private lateinit var txtAge: AppCompatTextView
    private lateinit var plusWeight: FloatingActionButton
    private lateinit var subtractWeight: FloatingActionButton
    private lateinit var viewMale: CardView
    private lateinit var plusAge: FloatingActionButton
    private lateinit var subtractAge: FloatingActionButton
    private lateinit var viewFemale: CardView
    private var height: Float = 120f
    private var isMaleSelected = true
    private var weight = 60
    private var age = 20
    private var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.imc_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        btnResult = findViewById<AppCompatButton>(R.id.btnResult)

        rsHeight = findViewById<RangeSlider>(R.id.rsHeight)
        txtHeight = findViewById<AppCompatTextView>(R.id.txtHeight)
        txtWeight = findViewById<AppCompatTextView>(R.id.txtWeight)
        plusWeight = findViewById<FloatingActionButton>(R.id.plusWeight)
        subtractWeight = findViewById<FloatingActionButton>(R.id.subtractWeight)

        txtAge = findViewById<AppCompatTextView>(R.id.txtAge)
        plusAge = findViewById<FloatingActionButton>(R.id.plusAge)
        subtractAge = findViewById<FloatingActionButton>(R.id.substractAge)

        viewMale = findViewById<CardView>(R.id.viewMale)
        viewFemale = findViewById<CardView>(R.id.viewFemale)


    }

    private fun initListeners() {
        btnResult.setOnClickListener {
            calculateIMC()
            val intent = Intent(this, IMCResultActivity::class.java)
            intent.putExtra("IMC_RESULT", result)
            startActivity(intent)
        }

        viewFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }
        viewMale.setOnClickListener {
            isMaleSelected = true
            setGenderColor()
        }

        plusWeight.setOnClickListener { plusWeight() }
        subtractWeight.setOnClickListener { subtractWeight() }

        plusAge.setOnClickListener { plusAge() }
        subtractAge.setOnClickListener { subtractAge() }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            height = value
            txtHeight.text = "$result cm"
        }
    }

    private fun setGenderColor() {
        val gray = ContextCompat.getColor(this, R.color.gray)
        val lightBlack = ContextCompat.getColor(this, R.color.light_black)
        if (isMaleSelected) {
            viewMale.setCardBackgroundColor(gray)
            viewFemale.setCardBackgroundColor(lightBlack)
        } else {
            viewFemale.setCardBackgroundColor(gray)
            viewMale.setCardBackgroundColor(lightBlack)
        }

    }

    private fun subtractWeight() {
        if (weight > 30) {
            weight = weight - 1
            txtWeight.text = "$weight kg"
        }
    }

    private fun plusWeight() {
        if (weight < 150) {
            weight = weight + 1
            txtWeight.text = "$weight kg"
        }
    }

    private fun plusAge() {
        if (age < 110) {
            age = age + 1
            txtAge.text = "$age"
        }
    }

    private fun subtractAge() {
        if (age > 3) {
            age = age - 1
            txtAge.text = "$age"
        }
    }

    private fun calculateIMC() {
        val df = DecimalFormat("#.##")
        val imc = weight / ((height / 100) * (height / 100))
        result = df.format(imc).toDouble()
    }

}