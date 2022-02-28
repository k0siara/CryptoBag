package com.patrykkosieradzki.cryptobag.common.ui.imageloading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun CryptoBagImage(
    modifier: Modifier = Modifier,
    url: String?
) {
    Image(
        modifier = Modifier.size(50.dp),
        painter = rememberImagePainter(url),
        contentDescription = null
    )
}