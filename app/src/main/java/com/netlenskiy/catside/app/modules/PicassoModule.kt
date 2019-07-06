package com.netlenskiy.catside.app.modules

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PicassoModule {

    @Provides
    @Singleton
    fun providesPicasso(): Picasso = Picasso.get()
}