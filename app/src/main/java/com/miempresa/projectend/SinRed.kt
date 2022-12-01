package com.miempresa.projectend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sin_red.*

class SinRed : AppCompatActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_sin_red)

		btnReintentar.setOnClickListener(){
			val intent = Intent(applicationContext, MainActivity::class.java)
			startActivity(intent)
		}
	}
}