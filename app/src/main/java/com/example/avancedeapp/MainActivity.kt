package com.example.avancedeapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var changeImageButton: Button

    private lateinit var tvName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvDepartment: TextView
    private lateinit var tvDistrict: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvPassword: TextView
    private lateinit var tvCardNumber: TextView


    // Request code para identificar la actividad de selección de imagen
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var btnEditProfile: Button

    companion object {
        // Crear un objeto compañero para almacenar los datos del usuario
        var userData = JSONObject()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar las vistas
        profileImage = findViewById(R.id.profileImage)
        changeImageButton = findViewById(R.id.changeImageButton)
        btnEditProfile = findViewById(R.id.btnEditProfile)  // <-- Mueve esta línea aquí

        // Establecer el listener del botón
        changeImageButton.setOnClickListener {
            openGallery()
        }

        // No necesitas llamar a setContentView(R.layout.activity_main) nuevamente, así que elimina la siguiente línea:
        // setContentView(R.layout.activity_main)

        // Initialize views
        tvName = findViewById(R.id.tvName)
        tvLastName = findViewById(R.id.tvLastName)
        tvEmail = findViewById(R.id.tvEmail)
        tvDepartment = findViewById(R.id.tvDepartment)
        tvDistrict = findViewById(R.id.tvDistrict)
        tvAddress = findViewById(R.id.tvAddress)
        tvPhone = findViewById(R.id.tvPhone)
        tvPassword = findViewById(R.id.tvPassword)
        tvCardNumber = findViewById(R.id.tvCardNumber)

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            // Pasar los datos actuales al EditProfileActivity
            intent.putExtra("Nombre", tvName.text.toString())
            intent.putExtra("Apellido", tvLastName.text.toString())
            intent.putExtra("Correo", tvEmail.text.toString())
            intent.putExtra("Departamento", tvDepartment.text.toString())
            intent.putExtra("Distrito", tvDistrict.text.toString())
            intent.putExtra("Direccion", tvAddress.text.toString())
            intent.putExtra("Celular", tvPhone.text.toString())
            intent.putExtra("Contraseña", tvPassword.text.toString())
            intent.putExtra("Número de tarjeta", tvCardNumber.text.toString())
            startActivity(intent)
        }

        // Fetch user details from fake API
        fetchUserDetails()
    }

    // Función para abrir la galería
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    // Función para manejar el resultado de la selección de imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            if (data.hasExtra("userData")) {
                val updatedUserDataString = data.getStringExtra("userData")
                val updatedUserData = JSONObject(updatedUserDataString)
                updateUIWithUserData(updatedUserData)
            }

            val selectedImageUri = data.data
            val imageStream: InputStream? = contentResolver.openInputStream(selectedImageUri!!)
            val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
            profileImage.setImageBitmap(selectedImage)
        }
    }

    private fun updateUIWithUserData(updatedUserData: JSONObject) {
        tvName.text = updatedUserData.getString("Nombre")
        tvLastName.text = updatedUserData.getString("Apellido")
        tvEmail.text = updatedUserData.getString("Correo")
        tvDepartment.text = updatedUserData.getString("Departamento")
        tvDistrict.text = updatedUserData.getString("Distrito")
        tvAddress.text = updatedUserData.getString("Direccion")
        tvPhone.text = updatedUserData.getString("Celular")
        tvPassword.text = updatedUserData.getString("Contraseña")
        tvCardNumber.text = updatedUserData.getString("Número de tarjeta")
    }
    private fun fetchUserDetails() {
        // For simplicity, we'll use a coroutine to simulate fetching data from a fake API
        CoroutineScope(Dispatchers.IO).launch {
            // Simulate network delay
            Thread.sleep(2000)

            // Fake API response
            val jsonResponse = """
                {
                    "Nombre": "Juan",
                    "Apellido": "Pérez",
                    "Correo": "juan.perez@example.com",
                    "Departamento": "Lima",
                    "Distrito": "Miraflores",
                    "Direccion": "Av. Larco 123",
                    "Celular": "987654321",
                    "Contraseña": "password123",
                    "Número de tarjeta": "1234 5678 9012 3456"
                }
            """

            val jsonObject = JSONObject(jsonResponse)

            withContext(Dispatchers.Main) {
                userData = jsonObject
                // Update UI with the data
                tvName.text = jsonObject.getString("Nombre")
                tvLastName.text = jsonObject.getString("Apellido")
                tvEmail.text = jsonObject.getString("Correo")
                tvDepartment.text = jsonObject.getString("Departamento")
                tvDistrict.text = jsonObject.getString("Distrito")
                tvAddress.text = jsonObject.getString("Direccion")
                tvPhone.text = jsonObject.getString("Celular")
                tvPassword.text = jsonObject.getString("Contraseña")
                tvCardNumber.text = jsonObject.getString("Número de tarjeta")
            }
        }
    }
}