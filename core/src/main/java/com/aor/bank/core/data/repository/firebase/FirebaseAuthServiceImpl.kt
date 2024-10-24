package com.aor.bank.core.data.repository.firebase

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val databaseReference: DatabaseReference,
    private val firebaseStorage: FirebaseStorage,
) : FirebaseAuthService {

    override suspend fun createUserWithEmailAndPassword(
        name: String,
        lastName: String,
        email: String,
        password: String,
        photoUri: Uri
    ): Result<Unit> {
        return try {
            val userId = createFirebaseUser(email, password)
            val photoUrl = uploadUserPhoto(userId, photoUri)
            saveUserData(userId, name, lastName, email, photoUrl)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            signInFirebaseUser(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun createFirebaseUser(email: String, password: String): String {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: throw Exception("Error al obtener el UID del usuario")
    }

    private suspend fun signInFirebaseUser(email: String, password: String): String {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: throw Exception("Error al obtener el UID del usuario")
    }

    private suspend fun uploadUserPhoto(userId: String, photoUri: Uri): String {
        val photoRef = firebaseStorage.reference.child("users/$userId/photo.jpg")
        photoRef.putFile(photoUri).await()
        return photoRef.downloadUrl.await().toString()
    }

    private suspend fun saveUserData(
        userId: String,
        name: String,
        lastName: String,
        email: String,
        photoUrl: String
    ) {
        val userMap = mapOf(
            "email" to email,
            "name" to name,
            "last_name" to lastName,
            "photo_url" to photoUrl
        )
        databaseReference.child("users").child(userId).setValue(userMap).await()
    }
}