@file:Suppress("unused")

package com.jarvis.weatherj.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

enum class LOADING {
    START, END
}

const val ZINGMP3_URL = "https://zingmp3.vn"
const val SECRET_KEY = "2aa2d1c561e809b267f3638c4a307aab"
const val API_KEY = "88265e23d4284f25963e6eedac8fbfa3"
const val VERSION = "1.6.34"

fun getSHA512HMac(string: String, key: String): String {
    val result = ""
    try {
        val shaHMAC: Mac = Mac.getInstance("HmacSHA512")
        val secretkey = SecretKeySpec(key.toByteArray(charset("UTF-8")), "HmacSHA512")
        shaHMAC.init(secretkey)
        return printHexBinary(shaHMAC.doFinal(string.toByteArray(charset("UTF-8"))))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return result
}

fun getSHA256Hash(string: String): String {
    val result = ""
    try {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(string.toByteArray(charset("UTF-8")))
        return printHexBinary(hash)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return result
}

fun printHexBinary(data: ByteArray): String {
    val herChars = "0123456789abcdef".toCharArray()
    val r = StringBuilder(data.size * 2)
    data.forEach { b ->
        val i = b.toInt()
        r.append(herChars[i shr 4 and 0xF])
        r.append(herChars[i and 0xF])
    }
    return r.toString()
}

fun hashParamHome(path: String, ctime: Long, count: Int, version: String): String {
    return getSHA512HMac(
        path + getSHA256Hash("count=${count}ctime=${ctime}page=1version=${version}"),
        SECRET_KEY,
    )
}

fun hashParam(path: String, id: String, ctime: Long): String {
    return getSHA512HMac(path + getSHA256Hash("ctime=${ctime}id=${id}version=$VERSION"), SECRET_KEY)
}

fun apiHome(): String {
    val path = "/api/v2/page/get/home"
    val ctime = 1671087996596
    val count = 10
    val sig = hashParamHome(path, ctime, count, VERSION)
    return "$ZINGMP3_URL$path?page=1&segmentId=-1&count=$count&ctime=$ctime&version=$VERSION&sig=$sig&apiKey=$API_KEY"
}


fun apiSongSream(): String {
    val path = "/api/v2/song/get/streaming"
    val encodeId = "Z6U96DO9"
    return getApiHasId(path, encodeId)
}

fun getApiHasId(path: String, id: String): String {
    val ctime = System.currentTimeMillis() / 1000
    val sig = hashParam(path, id, ctime)
    return "$ZINGMP3_URL$path?id=$id&ctime=$ctime&version=$VERSION&sig=$sig&apiKey=$API_KEY"
}
