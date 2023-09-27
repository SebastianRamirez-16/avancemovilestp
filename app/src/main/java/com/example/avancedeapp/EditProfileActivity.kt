package com.example.avancedeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class EditProfileActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etDepartment: EditText
    private lateinit var etDistrict: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCardNumber: EditText
    private lateinit var btnSaveChanges: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize views
        etName = findViewById(R.id.etName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etDepartment = findViewById(R.id.etDepartment)
        etDistrict = findViewById(R.id.etDistrict)
        etAddress = findViewById(R.id.etAddress)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etCardNumber = findViewById(R.id.etCardNumber)
        btnSaveChanges = findViewById(R.id.btnSaveChanges)

        // Set data from intent extras
        etName.setText(intent.getStringExtra("Nombre"))
        etLastName.setText(intent.getStringExtra("Apellido"))
        etEmail.setText(intent.getStringExtra("Correo"))
        etDepartment.setText(intent.getStringExtra("Departamento"))
        etDistrict.setText(intent.getStringExtra("Distrito"))
        etAddress.setText(intent.getStringExtra("Direccion"))
        etPhone.setText(intent.getStringExtra("Celular"))
        etPassword.setText(intent.getStringExtra("Contraseña"))
        etCardNumber.setText(intent.getStringExtra("Número de tarjeta"))

        btnSaveChanges.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {
        // Obtener los datos actualizados del usuario de los EditText
        val updatedUserData = JSONObject().apply {
            put("Nombre", etName.text.toString())
            put("Apellido", etLastName.text.toString())
            put("Correo", etEmail.text.toString())
            put("Departamento", etDepartment.text.toString())
            put("Distrito", etDistrict.text.toString())
            put("Direccion", etAddress.text.toString())
            put("Celular", etPhone.text.toString())
            put("Contraseña", etPassword.text.toString())
            put("Número de tarjeta", etCardNumber.text.toString())
            // ... (put other fields in the JSONObject)
        }

        // Crear un nuevo objeto Intent y establecer el extra "userData"
        val result = Intent()
        result.putExtra("userData", updatedUserData.toString())

        // Volver a la actividad principal e indicar que la operación fue exitosa
        setResult(RESULT_OK, result)
        finish()
    }
}
