/*
 * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
 */

package com.jarvis.weatherj.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

enum class LOADING {
    START, END
}

val zingMp3Url = "https://zingmp3.vn"
val secretKey = "2aa2d1c561e809b267f3638c4a307aab"
val apiKey = "88265e23d4284f25963e6eedac8fbfa3"
val version = "1.6.34";

fun getSHA512HMac(string: String, key: String): String {
    val result = ""
    try {
        val shaHMAC: Mac = Mac.getInstance("HmacSHA512")
        val secretkey = SecretKeySpec(key.toByteArray(charset("UTF-8")), "HmacSHA512")
        shaHMAC.init(secretkey)
        return  printHexBinary(shaHMAC.doFinal(string.toByteArray(charset("UTF-8"))))
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

fun hashParamHome(path:String,ctime: Long,count: Int, version: String): String  {
    return getSHA512HMac(
        path + getSHA256Hash("count=${count}ctime=${ctime}page=1version=${version}"),
        secretKey,
    );
}

fun hashParam(path: String, id: String, ctime: Long):String{
    return  getSHA512HMac(path + getSHA256Hash("ctime=${ctime}id=${id}version=$version"), secretKey)
}

fun apiHome(): String{
    val path = "/api/v2/page/get/home"
    val ctime = 1671087996596
    val count = 10
    val sig = hashParamHome(path, ctime, count, version)
    return "$zingMp3Url$path?page=1&segmentId=-1&count=$count&ctime=$ctime&version=$version&sig=$sig&apiKey=$apiKey"
}


fun apiSongSream(): String{
    val path = "/api/v2/song/get/streaming"
    val encodeId = "Z6U96DO9"
    return getApiHasId(path, encodeId);
}

fun getApiHasId(path: String, id: String):String{
    val ctime = System.currentTimeMillis()/1000
    val sig = hashParam(path, id, ctime)
    return "$zingMp3Url$path?id=$id&ctime=$ctime&version=$version&sig=$sig&apiKey=$apiKey";
}