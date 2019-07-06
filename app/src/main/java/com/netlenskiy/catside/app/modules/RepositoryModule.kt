package com.netlenskiy.catside.app.modules

import com.netlenskiy.catside.app.TheCatAPI
import com.netlenskiy.catside.data.imagesrepository.ImagesRepositoryImpl
import com.netlenskiy.catside.domain.ImagesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// TODO move it to a distinct scope with the same lifecycle as FeedViewModel has
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesImagesRepository(api: TheCatAPI): ImagesRepository {
        return ImagesRepositoryImpl(api)
    }
}