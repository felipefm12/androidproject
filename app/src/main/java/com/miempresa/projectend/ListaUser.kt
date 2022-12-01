package com.miempresa.projectend

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_lista_user.*
import org.json.JSONException

class ListaUser : AppCompatActivity() {
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_lista_user)
		cargarLista()
	}

	fun cargarLista() {
		listauser.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
		listauser.layoutManager = LinearLayoutManager(this)
		var llenarLista = ArrayList<Elements>()
		AsyncTask.execute {
			val queue = Volley.newRequestQueue(applicationContext)
			val url = getString(R.string.URL) + "/Users"
			val stringRequest = JsonArrayRequest(url,
				{ response ->
					try {
						for (i in 0 until response.length()) {
							val id = response.getJSONObject(i).getString("id")
							val name = response.getJSONObject(i).getString("name")
							val lastname = response.getJSONObject(i).getString("lastname")
							val email = response.getJSONObject(i).getString("email")
							val rol = response.getJSONObject(i).getString("rol")
							val password = response.getJSONObject(i).getString("password")
							llenarLista.add(
								Elements(
									id.toInt() ,
									name ,
									lastname ,
									email,
									rol.toInt(),
									password
								)
							)
						}
						val adapter = AdapterElements(llenarLista)
						listauser.adapter = adapter
					} catch (e : JSONException) {
						Toast.makeText(
							applicationContext ,
							"Error al obtener los datos" ,
							Toast.LENGTH_LONG
						).show()
					}
				} , {
					Toast.makeText(
						applicationContext ,
						"Verifique que esta conectado a internet" ,
						Toast.LENGTH_LONG
					).show()
				})
			queue.add(stringRequest)
		}
	}

}