package com.aor.bank.core.presentation.composables

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.aor.bank.core.data.util.PhotoPickerUtils
import com.aor.bank.core.R
import com.aor.bank.core.ui.theme.Green50

@Composable
fun PhotoPicker(
    photoUri: Uri?,
    onPhotoSelected: (Uri?) -> Unit,
    context: Context,
    onError: (String) -> Unit
) {
    val isPhotoTaken = remember { mutableStateOf(false) }

    val imageFile = remember { PhotoPickerUtils.createImageFile(context) }
    val imageUri = remember { PhotoPickerUtils.getImageUri(context, imageFile) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                isPhotoTaken.value = true
                onPhotoSelected(imageUri)
            } else {
                onError(context.getString(R.string.can_not_take_photo))
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                cameraLauncher.launch(imageUri)
            } else {
                onError(context.getString(R.string.permission_denegate))
            }
        }
    )

    Button(
        onClick = {
            when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                PackageManager.PERMISSION_GRANTED -> cameraLauncher.launch(imageUri)
                else -> permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        colors = ButtonDefaults.buttonColors(
            if (isPhotoTaken.value) Green50 else Color.Gray
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(56.dp)
    ) {
        Text(
            text = if (photoUri != null) {
                stringResource(R.string.photo_selected)
            } else {
                stringResource(R.string.take_photo)
            }
        )
    }
}