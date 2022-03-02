package dev.acuon.imagesliderwithretrofit.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.acuon.imagesliderwithretrofit.R
import dev.acuon.imagesliderwithretrofit.api.MovieItem
import kotlinx.android.synthetic.main.item_layout.view.*

class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list = mutableListOf<MovieItem>()
    fun setMovieList(movies: List<MovieItem>) {
        this.list = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = list[position]
        return holder.setData(movie)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MovieViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        fun setData(movieItem: MovieItem) {
            Glide.with(itemView.movieImage).load(movieItem.imageUrl).into(itemView.movieImage)
            itemView.movieText.text = movieItem.name
        }
    }

//    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        fun setData(movie: MovieItem) {
//            Glide.with(itemView.movieImage).load(movie.imageUrl).into(view.movieImage)
//            view.movieText
//        }
//    }
}