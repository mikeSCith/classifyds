package com.mikesmithinc.classifyds.ui.main

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.bumptech.glide.request.target.ViewTarget
import com.mikesmithinc.api.ApiService
import com.mikesmithinc.image.ImageOps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val imageOps: ImageOps,
    private val apiService: ApiService
) : ViewModel() {

    fun getImage(
        fragmentActivity: FragmentActivity,
        imageView: ImageView,
        url: String
    ): Flow<ViewTarget<ImageView, Drawable>> {
        return imageOps.loadImage(fragmentActivity, imageView, url)
    }


    @ExperimentalCoroutinesApi
    fun getSomething() = apiService.getClassifieds()

    fun clickAdd() {
        apiService.submit()
    }
}
