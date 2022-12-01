package com.miempresa.projectend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class Adapter_Element_Distrito(var context : Context , items:ArrayList<Elements_Distrito>): BaseAdapter() {

	var items:ArrayList<Elements_Distrito>? = null
	var itemsCopia:ArrayList<Elements_Distrito>? = null

	init {
		this.items = ArrayList(items)
		this.itemsCopia = items
	}

	override fun getCount() : Int {
		return items?.count()!!
	}

	override fun getItem(position : Int) : Any {
		return  items?.get(position)!!
	}

	override fun getItemId(position : Int) : Long {
		return  position.toLong()
	}

	override fun getView(position : Int , converView : View? , parent : ViewGroup?) : View {
		var view = converView
		var holder:ViewHolder? = null

		if (view == null){
			view = LayoutInflater.from(context).inflate(R.layout.elements_distrido, null)
			holder = ViewHolder(view)
			view.tag = holder
		}else{
			holder = view.tag as? ViewHolder
		}

		val item = items?.get(position) as Elements_Distrito
		holder?.nombre?.text = item.nombre
		holder?.imagen?.load(item?.imagen!!)
		//holder?.fImagen?.load(item[position].imagen)

		return view!!
	}

	fun filtrar(str:String){
		items?.clear()
		if (str.isEmpty()){
			items = ArrayList(itemsCopia)
			notifyDataSetChanged()
			return
		}

		var busqueda = str
		busqueda = busqueda.toLowerCase()

		for (item in itemsCopia!!){
			val nombre = item.nombre.toLowerCase()

			if (nombre.contains(busqueda)){
				items?.add(item)
			}
		}
		notifyDataSetChanged()
	}

	private class ViewHolder(view : View){
		var nombre: TextView? = null
		var imagen:ImageView? = null

		init {
			nombre = view.findViewById(R.id.textId)
			imagen = view.findViewById(R.id.imagenElement)
		}
	}
}

/*class Adapter_Element_Distrito(val ListaElementos:ArrayList<Elements_Distrito>): RecyclerView.Adapter<Adapter_Element_Distrito.ViewHolder>() {

	override fun getItemCount(): Int {
		return ListaElementos.size;
	}
	class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView) {
		val fImagen = itemView.findViewById<ImageView>(R.id.imgDistrito);
		val fNombre = itemView.findViewById<TextView>(R.id.txtnombredistrito)
		//val fDuracion = itemView.findViewById<TextView>(R.id.elemento_duracion)

		//set the onclick listener for the singlt list item
	}
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder?.fImagen?.load(ListaElementos[position].imagen)
		holder?.fNombre?.text=ListaElementos[position].nombre
		//holder?.fDuracion?.text=ListaElementos[position].duracion.toString() + "min"
	}
	override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent?.context).inflate(R.layout.elements_distrido, parent, false);
		return ViewHolder(v);
	}
}*/
