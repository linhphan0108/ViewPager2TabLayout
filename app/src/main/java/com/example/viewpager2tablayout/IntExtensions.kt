package com.example.viewpager2tablayout

import android.content.res.Resources

fun Int.dpToPx(): Int{
    return ((this * Resources.getSystem().displayMetrics.density).toInt())
}

fun Int.pxToDp(): Int{
    return  (this / Resources.getSystem().displayMetrics.density).toInt()
}