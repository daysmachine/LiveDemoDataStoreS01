package com.example.livedemo_datastores01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    lateinit var firstInput: TextView
    lateinit var secondInput: TextView
    lateinit var thirdInput: TextView
    lateinit var fourthInput: TextView

    private val myViewModel: MyViewModel by lazy {
        MyPreferencesRepository.initialize(this)
        MyViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.connectViews()

        this.setupListeners()

        this.myViewModel.loadInputs(this)
    }

    private fun connectViews() {
        this.saveButton = this.findViewById(R.id.button_save)
        this.clearButton = this.findViewById(R.id.button_clear)

        this.firstInput = this.findViewById(R.id.input1)
        this.secondInput = this.findViewById(R.id.input2)
        this.thirdInput = this.findViewById(R.id.input3)
        this.fourthInput = this.findViewById(R.id.input4)
    }

    private fun setupListeners() {
        this.saveButton.setOnClickListener {
            this.myViewModel.saveInput(this.firstInput.text.toString(), 1)
            this.myViewModel.saveInput(this.secondInput.text.toString(), 2)
            this.myViewModel.saveInput(this.thirdInput.text.toString(), 3)
            this.myViewModel.saveInput(this.fourthInput.text.toString(), 4)
        }

        this.clearButton.setOnClickListener {
            this.firstInput.text = ""
            this.secondInput.text = ""
            this.thirdInput.text = ""
            this.fourthInput.text = ""
        }
    }
}