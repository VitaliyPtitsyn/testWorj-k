@file:Suppress("unused")

package com.example.presentation.tools

import android.content.Context
import android.graphics.Typeface
import android.text.*
import android.text.style.*


private fun span(s: CharSequence, o: Any): SpannableString {
    val spannable = when (s) {
        is String -> SpannableString(s)
        is SpannableString -> s
        else -> SpannableString("")
    }

    return spannable.apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
}

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))
operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))

fun CharSequence.bold() = span(this, StyleSpan(Typeface.BOLD))
fun CharSequence.italic() = span(this, StyleSpan(Typeface.ITALIC))
fun CharSequence.underline() = span(this, UnderlineSpan())
fun CharSequence.strike() = span(this, StrikethroughSpan())
fun CharSequence.sup() = span(this, SuperscriptSpan())
fun CharSequence.sub() = span(this, SubscriptSpan())
fun CharSequence.size(size: Float) = span(this, RelativeSizeSpan(size))
fun CharSequence.color(color: Int) = span(this, ForegroundColorSpan(color))
fun CharSequence.background(color: Int) = span(this, BackgroundColorSpan(color))
fun CharSequence.url(url: String) = span(this, URLSpan(url))
fun CharSequence.style(context: Context, themeId: Int) = span(this, TextAppearanceSpan(context, themeId))
