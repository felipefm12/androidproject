package com.miempresa.projectend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin.*

class Admin : AppCompatActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_admin)

		btnLista.setOnClickListener(){
			val intent = Intent(applicationContext, ListaUser::class.java)
			startActivity(intent)
			//finish()
			//Toast.makeText(this,"Necesitas estar conectado a Internet",Toast.LENGTH_SHORT).show()
		}
	}
}