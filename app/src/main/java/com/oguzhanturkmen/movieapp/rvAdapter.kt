package com.oguzhanturkmen.movieapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanturkmen.movieapp.model.Movie
import kotlinx.android.synthetic.main.populer_movie_item.view.*

class rvAdapter: RecyclerView.Adapter<rvAdapter.MyHolderView>() {
    //var liveData: List<Result>? = null
    //fun setList(liveData: List<Result>){
      //  this.liveData = liveData
        //notifyDataSetChanged()
    //}
    private val differCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    inner class MyHolderView(itemview:View) : RecyclerView.ViewHolder(itemview){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvAdapter.MyHolderView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.populer_movie_item, parent, false)
        return MyHolderView(view)
    }
    private var onItemClickListener: ((Movie) -> Unit)? = null
    override fun onBindViewHolder(holder: rvAdapter.MyHolderView, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(POSTER_PATH+ movie.poster_path).into(movieImage)
            movieTitle.text = movie.title
            movieDescription.text = movie.overview
            moviePublishedAt.text = movie.release_date
            moviePoint.text = movie.vote_average.toString()
            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }
    override fun getItemCount(): Int {
        return  differ.currentList.size
    }
    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}
