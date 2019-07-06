package com.netlenskiy.catside.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netlenskiy.catside.R
import com.netlenskiy.catside.databinding.FeedFragmentBinding
import com.netlenskiy.catside.domain.FeedViewModel
import com.netlenskiy.catside.domain.entities.response.images.Image
import timber.log.Timber

class FeedFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FeedFragmentBinding

    private val feedAdapter = FeedAdapter(arrayListOf())

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_fragment, container, false)
        val viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        initRecycler()
        setObserver()
        viewModel.loadImages()
        return binding.root
    }

    private fun initRecycler() {
        binding.feedRecycler.layoutManager = LinearLayoutManager(context)
        binding.feedRecycler.adapter = feedAdapter
        binding.feedRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as? LinearLayoutManager
                val firstVisibleItemCount = lm?.findFirstVisibleItemPosition() ?: 0
                val visibleCount = lm?.childCount ?: 0
                val totalItemCount = lm?.itemCount ?: 0
                if (!viewModel.isLoading.get()) {
                    if ((firstVisibleItemCount + visibleCount >= totalItemCount)) {
                        viewModel.loadImages()
                    }
                }
            }
        })
    }

    private fun setObserver() {
        binding.viewModel?.images?.observe(this, Observer<List<Image>> {
            Timber.d("OBSERVER IMAGES COUNT: ${it.size}")
            val positionStart = feedAdapter.items.size
            feedAdapter.items.addAll(it)
            feedAdapter.notifyItemRangeChanged(positionStart, it.size)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
    }

}
