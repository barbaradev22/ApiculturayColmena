package com.barbara.apiculturaycolmena

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.PopupMenu

fun configurarNavegacion(activity: Activity) {

    val navInicio = activity.findViewById<View>(R.id.navInicio)
    val navMenu = activity.findViewById<View>(R.id.navMenu)

    navInicio.setOnClickListener {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(intent)
    }

    navMenu.setOnClickListener {

        val popup = PopupMenu(activity, navMenu)

        popup.menu.add("Apiarios")
        popup.menu.add("Apicultores")
        popup.menu.add("Colmenas")

        popup.setOnMenuItemClickListener { item ->

            when (item.title.toString()) {

                "Apiarios" -> {
                    activity.startActivity(
                        Intent(activity, ApiariosActivity::class.java)
                    )
                    true
                }

                "Apicultores" -> {
                    activity.startActivity(
                        Intent(activity, ApicultoresActivity::class.java)
                    )
                    true
                }

                "Colmenas" -> {
                    activity.startActivity(
                        Intent(activity, ColmenasActivity::class.java)
                    )
                    true
                }

                else -> false
            }
        }

        popup.show()
    }
}