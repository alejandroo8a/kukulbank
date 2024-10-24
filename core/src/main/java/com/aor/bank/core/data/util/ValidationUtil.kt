package com.aor.bank.core.data.util

import android.net.Uri
import java.util.regex.Pattern

object ValidationUtil {

    fun validateName(name: String): String? {
        return if (name.isBlank()) "El nombre no puede estar vacío" else null
    }

    fun validateLastName(lastName: String): String? {
        return if (lastName.isBlank()) "El apellido no puede estar vacío" else null
    }

    fun validateEmail(email: String): String? {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return if (!emailPattern.matcher(email).matches()) "El correo no es válido" else null
    }

    fun validatePassword(password: String): String? {
        val passwordPattern = Pattern.compile(
            "^(?=.*[A-Z])[A-Za-z\\d@\$!%*?&]{8,}$" // At least one uppercase letter and minimum 8 characters
        )
        return if (!passwordPattern.matcher(password).matches()) {
            "La contraseña debe tener al menos 8 caracteres y contener una letra mayúscula"
        } else null
    }

    fun validatePhoto(photoUri: Uri?): String? {
        return if (photoUri == null) {
            "Debe tomar una foto de su identificación" // Error message in Spanish
        } else {
            null
        }
    }
}