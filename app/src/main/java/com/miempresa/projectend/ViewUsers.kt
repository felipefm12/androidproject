package com.miempresa.projectend

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_view_users.*
import org.json.JSONException
import androidx.appcompat.widget.Toolbar

class ViewUsers : AppCompatActivity () {
	var toolbar:Toolbar? = null
	var adapter:Adapter_Element_Distrito? = null

	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_view_users)

		//Toolbar
		toolbar = findViewById(R.id.Toolbar_distridos)
		toolbar?.title = "Mi titulo"
		setSupportActionBar(toolbar)

		cargarListaDistrito()


	}

	fun cargarListaDistrito() {
		//ListaDistridos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
		//ListaDistridos.layoutManager = LinearLayoutManager(this)
		var grid: GridView = findViewById(R.id.ListaDistridos)
		//val adapter = Adapter(this, )
		var llenarLista = ArrayList<Elements_Distrito>()
		AsyncTask.execute {
			val queue = Volley.newRequestQueue(applicationContext)
			val url = getString(R.string.URL) + "/distritos"
			val stringRequest = JsonArrayRequest(url,
				{ response ->
					try {
						for (i in 0 until response.length()) {
							val id = response.getJSONObject(i).getString("id")
							val nombre = response.getJSONObject(i).getString("nombre")
							val imagen = response.getJSONObject(i).getString("imagen")
							llenarLista.add(Elements_Distrito(id.toInt(),nombre,imagen))
						}
						adapter = Adapter_Element_Distrito(this, llenarLista)
						ListaDistridos.adapter = adapter

						grid.onItemClickListener = AdapterView.OnItemClickListener { parent , view , position , id ->
							val intent = Intent(this,ViewEmpresas::class.java)
							intent.putExtra("id", llenarLista.get(position).id)
							startActivity(intent)
							Toast.makeText(this,llenarLista.get(position).nombre,Toast.LENGTH_SHORT).show()

						}
					} catch (e: JSONException) {
						Toast.makeText(
							applicationContext,
							"Error al obtener los datos",
							Toast.LENGTH_LONG
						).show()
					}
				} , {
					Toast.makeText(
						applicationContext,
						"Verifique que esta conectado a internet",
						Toast.LENGTH_LONG
					).show()
				})
			queue.add(stringRequest)
		}

	}



	override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
		menuInflater.inflate(R.menu.menu_distritos, menu)
		val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
		val itembusqueda = menu?.findItem(R.id.busqueda)
		var vistaBusqueda = itembusqueda?.actionView as SearchView

		vistaBusqueda.setSearchableInfo(searchManager.getSearchableInfo(componentName))
		vistaBusqueda.queryHint = "Buscar distritos"
		vistaBusqueda.setOnQueryTextFocusChangeListener{v, hasFocus->
			//preparar datos
		}
		vistaBusqueda.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
			override fun onQueryTextChange(newText : String?) : Boolean {
				adapter?.filtrar(newText!!)
				return true
				//filtrar
			}

			override fun onQueryTextSubmit(query : String?) : Boolean {
				return true
				//filtrar
			}
		})



		return super.onCreateOptionsMenu(menu)
	}

	//toolbar elementos
	override fun onOptionsItemSelected(item : MenuItem) : Boolean {
		return super.onOptionsItemSelected(item)
	}

}