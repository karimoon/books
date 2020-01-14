package com.karim.booksapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karim.booksapp.R
import com.karim.booksapp.data.models.Book
import kotlinx.android.synthetic.main.book_grid_item.view.*

class BooksListRecyclerAdapter(val context: Context,
                               var books: List<Book>, val itemListener: BookItemListener):
    RecyclerView.Adapter<BooksListRecyclerAdapter.ViewHolder>()

{
    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_grid_item, parent, false)
        return ViewHolder(view)
    }

    fun clear() {
        val size = books.size
        books = emptyList()
        notifyItemRangeRemoved(0, size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with(holder) {
            nameText?.let {

                it.text = book.title

                it.contentDescription = book.description
            }
            ratingBar?.rating = book.averageRating.toFloat()


            if(book.thumbnail.isNullOrBlank()){

                Glide.with(context)
                    .load("https://books.google.fr/googlebooks/images/no_cover_thumb.gif")
                    .into(bookImage)

            }
            else{
                Glide.with(context)
                    .load(book.thumbnail)
                    .into(bookImage)
            }

            holder.itemView.setOnClickListener{
                itemListener.onBookItemClick(book)
            }
        }
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.nameText
        val bookImage = itemView.bookImage
        val ratingBar = itemView.ratingBar
    }

    interface BookItemListener {
        fun onBookItemClick(book: Book)
    }
}