package com.fgustavo.deeptb.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fgustavo.deeptb.MainActivity
import com.fgustavo.deeptb.R

class ActivityIniciarSesion : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_iniciar_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbar = findViewById(R.id.toolbarIniciarSesion)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Cambiar el comportamiento del botón de navegación
        toolbar.setNavigationOnClickListener {
            toolbar.navigationIcon?.setTint(getColor(R.color.black)) // Cambia el color del ícono
            onBackPressed() // Vuelve a la pantalla anterior
        }
        iniciar()
    }
    private fun iniciar() {
        loginButton = findViewById(R.id.BtnIniciarSesion)
        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}