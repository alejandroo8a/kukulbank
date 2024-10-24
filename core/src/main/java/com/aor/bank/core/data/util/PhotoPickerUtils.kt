package com.aor.bank.core.data.util

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object PhotoPickerUtils {
    fun createImageFile(context: Context): File {
        return File(context.cacheDir, "photo_${System.currentTimeMillis()}.jpg")
    }

    // Function to get the URI for the file using FileProvider
    fun getImageUri(context: Context, imageFile: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    }
}