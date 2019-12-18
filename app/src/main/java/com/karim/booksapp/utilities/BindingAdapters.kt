package com.karim.booksapp.utilities

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view : ImageView , imageUrl:String){
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("joinedAuthors")
fun join(view: TextView, authors :Array<String?>){
    view.text= TextUtils.join(", ", authors)
}