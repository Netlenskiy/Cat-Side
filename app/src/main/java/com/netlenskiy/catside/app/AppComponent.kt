package com.netlenskiy.catside.app

import com.netlenskiy.catside.app.modules.NetworkModule
import com.netlenskiy.catside.app.modules.PicassoModule
import com.netlenskiy.catside.app.modules.RepositoryModule
import com.netlenskiy.catside.data.imagesrepository.ImagesRepositoryImpl
import com.netlenskiy.catside.domain.FeedViewModel
import com.netlenskiy.catside.presentation.FeedAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (RepositoryModule::class), (PicassoModule::class)])
interface AppComponent {
    fun inject(mainActivity: ImagesRepositoryImpl)
    fun inject(feedViewModel: FeedViewModel)
    fun inject(feedAdapter: FeedAdapter)
}