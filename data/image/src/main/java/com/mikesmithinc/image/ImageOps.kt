package com.mikesmithinc.image

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ImageOps
@Inject
constructor() {

    fun loadImage(
        fragmentActivity: FragmentActivity,
        imageView: ImageView,
        url: String
    ): Flow<ViewTarget<ImageView, Drawable>> = flowOf(
        Glide.with(fragmentActivity)
            .load(url)
            .fitCenter()
            .into(imageView)
    )

}