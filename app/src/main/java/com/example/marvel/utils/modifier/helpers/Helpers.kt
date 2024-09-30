package com.example.marvel.utils.modifier.helpers

//Marvel API does not retrieve thumbnails with https, so we need to replace it (they are actually available with https)
fun formatImageUrl(path: String, extension: String): String {
    return path.replace("http", "https") + "." + extension
}