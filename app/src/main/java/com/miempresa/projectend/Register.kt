package com.miempresa.projectend

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_register)
		btn_ir_inicioSesion.setOnClickListener() {
			val intent = Intent(applicationContext , Login::class.java)
			startActivity(intent)
			finishActivity(1)
		}

		btn_crear_cuenta.setOnClickListener() {
			val name = txt_nombre.text.toString()
			val lastname = txt_apellido.text.toString()
			val email = txt_correo_register.text.toString()
			val password = txt_contrasena_register.text.toString()

			if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
				//Toast.makeText(this , "Error, Campos sin dato" , Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}
			usersRegister()
			val intent = Intent(applicationContext , Login::class.java)
			startActivity(intent)
			finishAffinity()
			//Toast.makeText(this,"Necesitas estar conectado a Internet",Toast.LENGTH_SHORT).show()
		}

	}

	private fun usersRegister() {
		AsyncTask.execute {

			val name = txt_nombre.text.toString()
			val lastname = txt_apellido.text.toString()
			val email = txt_correo_register.text.toString()
			val password = txt_contrasena_register.text.toString()
			val rol : Int = 1

			val queue = Volley.newRequestQueue(this)
			var url = getString(R.string.URL) + "/Users/"
			val postRequest : StringRequest = object : StringRequest(
				Request.Method.POST , url ,
				Response.Listener { response -> // response
					Toast.makeText(
						applicationContext ,
						"Registrado Correctamente" ,
						Toast.LENGTH_LONG
					).show()
				} ,
				Response.ErrorListener { response ->// error
					Toast.makeText(
						applicationContext ,
						"Se produjo un error al guardar los datos" ,
						Toast.LENGTH_LONG
					).show()
				}
			) {
				override fun getParams() : Map<String , String> {
					val params : MutableMap<String , String> =
						HashMap()
					params["name"] = name
					params["lastname"] = lastname
					params["email"] = email
					params["rol"] = rol.toString()
					params["password"] = password
					return params
				}
			}
			queue.add(postRequest)
		}
	}
}