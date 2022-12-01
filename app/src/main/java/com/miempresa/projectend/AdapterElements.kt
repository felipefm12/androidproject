package com.miempresa.projectend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterElements (val ListaElementos:ArrayList<Elements>): RecyclerView.Adapter<AdapterElements.ViewHolder>() {

	override fun getItemCount(): Int {
		return ListaElementos.size;
	}
	class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView) {
		val fname = itemView.findViewById<TextView>(R.id.lblName);
		val flastname = itemView.findViewById<TextView>(R.id.lblLastname)
		val femail = itemView.findViewById<TextView>(R.id.lblcorreo)

		//set the onclick listener for the singlt list item
	}
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder?.fname?.text=ListaElementos[position].name
		holder?.flastname?.text=ListaElementos[position].lastname
		holder?.femail?.text=ListaElementos[position].email
	}
	override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
		val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementsuser, parent, false);
		return ViewHolder(v);
	}
}
