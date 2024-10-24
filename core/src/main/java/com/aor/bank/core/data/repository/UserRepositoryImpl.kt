package com.aor.bank.core.data.repository

import com.aor.bank.core.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val databaseReference: DatabaseReference
) : UserRepository {

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun getUserDetails(): UserModel {
        val userId = firebaseAuth.currentUser?.uid
            ?: throw Exception("Usuario no autenticado")

        val snapshot = databaseReference.child("users").child(userId).get().await()

        val name = snapshot.child("name").getValue(String::class.java) ?: ""
        val lastName = snapshot.child("last_name").getValue(String::class.java) ?: ""
        val email = snapshot.child("email").getValue(String::class.java) ?: ""
        val photoUrl = snapshot.child("photo_url").getValue(String::class.java)

        return UserModel(userId, name, lastName, email, photoUrl)
    }
}