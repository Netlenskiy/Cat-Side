package com.netlenskiy.catside.data.imagesrepository

import com.netlenskiy.catside.app.App
import com.netlenskiy.catside.app.TheCatAPI
import com.netlenskiy.catside.domain.ImagesRepository
import com.netlenskiy.catside.domain.entities.response.images.Image
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

class ImagesRepositoryImpl(private val api: TheCatAPI) : ImagesRepository {

    init {
        App.getInstance().appComponent.inject(this)
    }

    override fun loadImages(
        breedId: String,
        categoryId: String,
        page: Int,
        limit: Int,
        callback: ((List<Image>) -> Unit)?
    ) {
        api.getImages(breedId, categoryId, page, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Response<List<Image>>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Response<List<Image>>) {
                    t.body()?.let {
                        callback?.let { callback ->
                            //                            Handler().postDelayed({callback(it)}, 10000)
                            callback(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

            })
    }
}