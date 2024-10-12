package com.fgustavo.deeptb

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val IMAGE_REQUEST_CODE = 100
    private lateinit var btncargarimagen: Button
    private lateinit var menuboton: ImageButton
    private lateinit var imagencargada: ImageView
    private lateinit var deleteboton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialize()
    }
    private fun initialize() {
        btncargarimagen = findViewById(R.id.BtnCargarImagen)
        menuboton = findViewById(R.id.menu_button)
        imagencargada = findViewById(R.id.imagenCargar)
        deleteboton = findViewById(R.id.deleteButton)
        btncargarimagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
        menuboton.setOnClickListener {
            // Open the menu here
        }
        deleteboton.setOnClickListener {
            // Delete the image here
            imagencargada.setImageResource(0)  // Elimina la imagen cargada
            imagencargada.setBackgroundColor(getColor(android.R.color.darker_gray))  // Vuelve a poner el fondo gris
            deleteboton.visibility = ImageButton.GONE  // Oculta el botón de eliminar
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val inputStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(inputStream)

            val imagePreview = findViewById<ImageView>(R.id.imagenCargar)
            val deleteButton = findViewById<ImageButton>(R.id.deleteButton)

            imagePreview.setImageBitmap(selectedImage)
            deleteButton.visibility = ImageButton.VISIBLE  // Muestra el botón de eliminar
        }
    }
}