package com.fgustavo.deeptb

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private val IMAGE_REQUEST_CODE = 100
    private lateinit var btncargarimagen: Button
    private lateinit var menuboton: ImageButton
    private lateinit var imagencargada: ImageView
    private lateinit var deleteboton: ImageButton
    private lateinit var progressbar: ProgressBar
    private lateinit var resultMessage: TextView
    private lateinit var drawerlayout: DrawerLayout
    private lateinit var naviView: NavigationView
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
        progressbar = findViewById(R.id.progressBar)
        resultMessage = findViewById(R.id.predictionResult)
        drawerlayout = findViewById(R.id.draw)
        naviView = findViewById(R.id.nav_view)


        btncargarimagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }
        menuboton.setOnClickListener {
            // Open the menu here
            drawerlayout.openDrawer(naviView)
        }
        deleteboton.setOnClickListener {
            // Delete the image here
            imagencargada.setImageResource(0)  // Elimina la imagen cargada
            imagencargada.setBackgroundColor(getColor(android.R.color.darker_gray))  // Vuelve a poner el fondo gris
            deleteboton.visibility = ImageButton.GONE  // Oculta el botón de eliminar
            resultMessage.visibility = TextView.GONE  // Oculta el mensaje de resultados
        }

        naviView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> drawerlayout.closeDrawers()
                R.id.nav_logout -> logout()
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val inputStream = contentResolver.openInputStream(imageUri!!)
            val selectedImage = BitmapFactory.decodeStream(inputStream)

            imagencargada.setImageBitmap(selectedImage)
            deleteboton.visibility = ImageButton.VISIBLE  // Muestra el botón de eliminar

            progressbar.visibility = ProgressBar.VISIBLE

            // Simular un tiempo de procesamiento (por ejemplo, 3 segundos)
            Handler(Looper.getMainLooper()).postDelayed({
                // Ocultar la rueda de progreso
                progressbar.visibility = ProgressBar.GONE

                // Mostrar mensaje de resultado
                resultMessage.text = "Imagen procesada correctamente"
                resultMessage.visibility = TextView.VISIBLE

            }, 3000) // Simular 3 segundos de procesamiento
        }
    }

    private fun logout() {
        //Aquí se cerrara la sesión del usuario
        AlertDialog.Builder(this)
            .setTitle("Confirmación")
            .setMessage("¿Desea cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
//                with(sharedPreferences.edit()) {
//                    clear()
//                    apply()
//                }
//                FirebaseAuth.getInstance().signOut()
//                LoginManager.getInstance().logOut()
//                val intent = Intent(this, ActivityLogin::class.java)
//                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}