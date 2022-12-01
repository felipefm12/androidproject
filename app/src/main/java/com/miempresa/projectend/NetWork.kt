package com.miempresa.projectend

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class NetWork {
	companion object{
		fun hayRed(activity: AppCompatActivity):Boolean{
			val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
			val netwokinfo = connectivityManager.activeNetworkInfo
			return netwokinfo !=null && netwokinfo.isConnected
		}
	}
}