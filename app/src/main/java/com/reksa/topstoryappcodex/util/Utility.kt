package com.reksa.topstoryappcodex.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class Utility {
    companion object {
        fun showLog(message: String) {
            Log.d("_logTopStory", message)
        }

        fun getDateTimeFromEpocLongOfSeconds(time: Long): String? {
            return try {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val netDate = Date(time * 1000)
                sdf.format(netDate).toString()
            } catch (e: Exception) {
                e.toString()
            }
        }

        fun readContentFromUrl(url: String): String {
            val yahoo = URL(url)
            val bf = BufferedReader(
                InputStreamReader(
                    yahoo.openStream()
                )
            )

            var inputLine: String?

            while (bf.readLine().also { inputLine = it } != null) println(inputLine)

            bf.close()

            return inputLine!!
        }
    }
}