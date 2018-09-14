package io.petros.movies.presentation.feature.movies.list

import android.support.v7.widget.RecyclerView
import io.petros.movies.domain.model.movie.Movie
import io.petros.movies.presentation.feature.movies.listener.MovieCallback
import io.petros.movies.presentation.feature.movies.view.MovieItemView

class MovieViewHolder(
    itemView: MovieItemView,
    private val callback: MovieCallback
) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) {
        bindMovie(movie)
        bindCallback(movie)
    }

    private fun bindMovie(movie: Movie) {
        (itemView as MovieItemView).bind(movie)
    }

    private fun bindCallback(movie: Movie) {
        itemView.setOnClickListener { callback.onClick(movie) }
    }

}