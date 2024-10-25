package com.aor.bank.sign_up

import android.net.Uri
import com.aor.bank.core.data.repository.firebase.FirebaseAuthService
import com.aor.bank.sign_up.data.repository.SignUpRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SignUpRepositoryImplTest {

    private lateinit var firebaseAuthService: FirebaseAuthService
    private lateinit var signUpRepository: SignUpRepositoryImpl

    @Before
    fun setup() {
        firebaseAuthService = mock()
        signUpRepository = SignUpRepositoryImpl(firebaseAuthService)
    }

    @Test
    fun `createUser returns success when input is valid`(): Unit = runTest {
        // Arrange
        val name = "John"
        val lastName = "Doe"
        val email = "john.doe@example.com"
        val password = "Password123!"
        val photoUri = mock<Uri>()

        whenever(
            firebaseAuthService.createUserWithEmailAndPassword(
                name, lastName, email, password, photoUri
            )
        ).thenReturn(Result.success(Unit))

        // Act
        val result = signUpRepository.createUser(name, lastName, email, password, photoUri)

        // Assert
        assertThat(result.isSuccess).isTrue()
        verify(firebaseAuthService).createUserWithEmailAndPassword(
            name, lastName, email, password, photoUri
        )
    }

    @Test
    fun `createUser returns failure when photoUri is null`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "John", lastName = "Doe", email = "john.doe@example.com",
            password = "Password123!", photoUri = null
        )

        // Assert
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `createUser returns failure when password is invalid`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "John", lastName = "Doe", email = "john.doe@example.com",
            password = "123", photoUri = mock()
        )

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo(
            "La contraseña debe tener al menos 8 caracteres y contener una letra mayúscula"
        )
    }

    @Test
    fun `createUser does not call FirebaseAuthService when validation fails`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "", lastName = "", email = "invalid-email",
            password = "123", photoUri = null
        )

        // Assert
        assertThat(result.isFailure).isTrue()
        verify(firebaseAuthService, never()).createUserWithEmailAndPassword(any(), any(), any(), any(), any())
    }

    @Test
    fun `createUser returns failure when email is invalid`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "John", lastName = "Doe", email = "invalid-email",
            password = "Password123!", photoUri = mock()
        )

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("El correo no es válido")
    }

    @Test
    fun `createUser returns failure when last name is invalid`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "John", lastName = "", email = "john.doe@example.com",
            password = "Password123!", photoUri = mock()
        )

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("El apellido no puede estar vacío")
    }

    @Test
    fun `createUser returns failure when name is invalid`() = runTest {
        // Act
        val result = signUpRepository.createUser(
            name = "", lastName = "Doe", email = "john.doe@example.com",
            password = "Password123!", photoUri = mock()
        )

        // Assert
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("El nombre no puede estar vacío")
    }
}