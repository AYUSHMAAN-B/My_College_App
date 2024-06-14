package com.example.grades.QuickLinks_Screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebScreen(
    page : String
)
{
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    val url = "https://iitdh.ac.in/$page"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = object  : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        isLoading = false
                    }
                }
                loadUrl(url ?: "")
            }
        })
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}