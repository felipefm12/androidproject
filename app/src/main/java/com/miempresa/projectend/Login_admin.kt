package com.miempresa.projectend

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

class Login_admin: AppCompatActivity() {
    var ListUser:ArrayList<user>? = null
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        if (NetWork.hayRed(this)){
            btn_iniciar_sesion.setOnClickListener() {
                //id de vistas
                val Email = txt_correo.text.toString()
                val Password = txt_contrasena.text.toString()
                //val queue = Volley.newRequestQueue(this)
                if (Email.isEmpty() || Password.isEmpty()) {
                    //Toast.makeText(this , "Error, Campos sin dato" , Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var url = getString(R.string.URL)+"/Users?format=json&"

                val Url = url + "email=" + Email + "&password=" + Password + "&rol=" + 0
                VolleyHttp(Url)
            }

        }else{
            val intent = Intent(applicationContext, SinRed::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"Necesitas estar conectado a Internet", Toast.LENGTH_SHORT).show()
        }
    }
    private fun VolleyHttp(url: String){
        val queue = Volley.newRequestQueue(this)
        val stringRequest = JsonArrayRequest(url,
            {
                    response ->
                try {
                    val valor =response.getJSONObject(0)
                    val rol = valor.getInt("rol")
                    if (rol == 0){
                        val intent = Intent(applicationContext, Admin::class.java)
                        startActivity(intent)
                        //finish()
                    }else{
                        Toast.makeText(applicationContext,"Sin rol", Toast.LENGTH_SHORT).show()
                    }
                    //Toast.makeText(applicationContext,"La Contraseña o el email es incorrecto",Toast.LENGTH_SHORT).show()

                }catch (e:Exception){
                    Toast.makeText(applicationContext,"La Contraseña o el email es incorrecto",
                        Toast.LENGTH_SHORT).show()
                }
            } , {
                val intent = Intent(applicationContext, SinRed::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this,"Necesitas estar conectado a Internet", Toast.LENGTH_SHORT).show()
                //Toast.makeText(applicationContext,"No hay internet",Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest)
    }
}