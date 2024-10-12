package com.fgustavo.deeptb.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fgustavo.deeptb.MainActivity
import com.fgustavo.deeptb.R

class ActivityLogin : AppCompatActivity() {
    private lateinit var txtiniciarsesion: TextView
    private lateinit var btnx:Button
    private lateinit var btngoogle: Button
    private lateinit var btncreacrcuenta: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciar()
    }
    private fun iniciar() {
        txtiniciarsesion = findViewById(R.id.txtIniciarSesion)
        btnx = findViewById(R.id.BtnX)
        btngoogle = findViewById(R.id.BtnGoogle)
        btncreacrcuenta = findViewById(R.id.BtnCrearCuenta)

        txtiniciarsesion.setOnClickListener {
            val intent = Intent(this, ActivityIniciarSesion::class.java)
            startActivity(intent)
        }
        btnx.setOnClickListener {  }
        btngoogle.setOnClickListener {  }
        btncreacrcuenta.setOnClickListener {
            val intent = Intent(this, ActivityCrearCuenta::class.java)
            startActivity(intent)
        }

    }
}