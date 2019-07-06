package com.netlenskiy.catside.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netlenskiy.catside.app.App
import com.netlenskiy.catside.databinding.FeedItemBinding
import com.netlenskiy.catside.domain.entities.response.images.Image
import com.squareup.picasso.Picasso
import timber.log.Timber
import javax.inject.Inject

class FeedAdapter(val items: ArrayList<Image>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    init {
        App.getInstance().appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, picasso)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: FeedItemBinding, private val picasso: Picasso) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            Timber.d("BIND IMAGE, BOUND IMAGE ID: ${image.id}")
            binding.image = image
            binding.executePendingBindings()
            picasso.load(image.url)
                .fit()
                .centerCrop()
                .noFade()
                .into(binding.imageView)
        }
    }
}