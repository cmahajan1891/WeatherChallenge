package com.example.androidchallenge.utils

import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.formatTemperature(temperatureMeasurementStr: String, proportion: Float) {
    val tempSpan = SpannableString(temperatureMeasurementStr)
    if (temperatureMeasurementStr.isNotEmpty()) {
        //the symbol will be smaller then the number
        tempSpan.setSpan(SuperscriptSpan(), temperatureMeasurementStr.length - 2, temperatureMeasurementStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tempSpan.setSpan(RelativeSizeSpan(proportion), temperatureMeasurementStr.length - 2, temperatureMeasurementStr.length, 0)
        this.text = tempSpan
    }
}
