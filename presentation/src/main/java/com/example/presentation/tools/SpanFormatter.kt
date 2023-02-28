package com.example.presentation.tools

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import java.util.regex.Matcher
import java.util.regex.Pattern

object SpanFormatter {

    private val FORMAT_SEQUENCE: Pattern =
        Pattern.compile("%([0-9]+\\$|<?)([^a-zA-z%]*)([a-zA-Z%&&[^tT]]|[tT][a-zA-Z])")

    fun format(format: CharSequence?, vararg args: Any): SpannedString {
        val out = SpannableStringBuilder(format)

        if (args.isEmpty()) return SpannedString(out)

        var i = 0
        var argAt = -1
        while (i < out.length) {
            val m: Matcher = FORMAT_SEQUENCE.matcher(out)
            if (!m.find(i)) break
            i = m.start()

            val exprEnd: Int = m.end()
            val argTerm: String = m.group(1).orEmpty()
            val modTerm: String = m.group(2).orEmpty()
            val typeTerm: String = m.group(3).orEmpty()

            val cookedArg: CharSequence = when (typeTerm) {
                "%" -> "%"
                "n" -> "\n"
                else -> {
                    val argItem = when (argTerm) {
                        "" -> args[++argAt]
                        "<" -> args[argAt]
                        else -> args[argTerm.substring(0, argTerm.length - 1).toInt() - 1]
                    }
                    if (typeTerm == "s" && argItem is Spanned) {
                        argItem
                    } else {
                        String.format("%$modTerm$typeTerm", argItem)
                    }
                }
            }
            out.replace(i, exprEnd, cookedArg)
            i += cookedArg.length
        }
        return SpannedString(out)
    }
}