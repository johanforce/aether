@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.jarvis.weatherj.presentation.pref

import com.google.gson.Gson
import com.jarvis.weatherj.common.DebugLog
import com.tencent.mmkv.MMKV


object AppPrefs {
    private val sharedMMKV by lazy {
        MMKV.defaultMMKV()
    }

    val gson = Gson()

    /**
     * Remove object
     * @param key is the key for saving
     */
    fun remove(key: String) {
        sharedMMKV.edit().remove(key).apply()
    }

    /**
     * Remove all object
     */
    fun removeAll() {
        sharedMMKV.edit().clear().apply()
    }

    /**
     * Save string value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveString(key: String, value: String) {
        sharedMMKV.edit()
            .putString(key, value)
            .apply()
    }

    /**
     * Get string value from pfs
     * @param key is the key for getting
     */
    fun getString(key: String): String? {
        return try {
            // Get value
            sharedMMKV.decodeString(key)

        } catch (e: Exception) {
            DebugLog().d(e.message.toString())
            return null
        }
    }

    /**
     * Save boolean value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveLong(key: String, value: Long?) {
        sharedMMKV.edit()
            .putLong(key, value ?: 0)
            .apply()
    }

    /**
     * Get long value from pfs
     * @param key is the key for getting
     */
    fun getLong(key: String): Long {
        return sharedMMKV.getLong(key, 0L)
    }

    /**
     * Save boolean value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveBoolean(key: String, value: Boolean?) {
        sharedMMKV.edit()
            .putBoolean(key, value ?: false)
            .apply()
    }

    /**
     * Get boolean value from pfs
     * @param key is the key for getting
     */
    fun getFloat(key: String): Float {
        return sharedMMKV.getFloat(key, 0f)
    }

    /**
     * Save boolean value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveFloat(key: String, value: Float?) {
        sharedMMKV.edit()
            .putFloat(key, value ?: 0f)
            .apply()
    }

    /**
     * Get boolean value from pfs
     * @param key is the key for getting
     */
    fun getBoolean(key: String): Boolean {
        return sharedMMKV.getBoolean(key, false)
    }

    /**
     * This is function clear data reference
     */
    fun clear() {
        sharedMMKV.edit().clear().apply()
    }

    /**
     * Save int value
     * @param key is the key for saving
     * @param value is the value to save
     */
    fun saveInt(key: String, value: Int?) {
        sharedMMKV.edit()
            .putInt(key, value ?: 0)
            .apply()
    }

    /**
     * Get int value from pfs
     * @param key is the key for getting
     */
    fun getInt(key: String): Int {
        return sharedMMKV.getInt(key, 0)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrNull(key: String, anonymousClass: Class<T>): T? {
        return runCatching {
            when (anonymousClass) {
                String::class.java -> getString(key)
                Boolean::class.java -> getBoolean(key)
                Float::class.java -> getFloat(key)
                Int::class.java -> getInt(key)
                Long::class.java -> getLong(key)
                else -> gson.fromJson(getString(key), anonymousClass)
            }
        }.getOrNull() as? T
    }

    fun <T> save(key: String, data: T?) {
        runCatching {
            val editor = sharedMMKV.edit()
            when (data) {
                is String -> saveString(key, data)
                is Boolean -> saveBoolean(key, data)
                is Float -> saveFloat(key, data)
                is Int -> saveInt(key, data)
                is Long -> saveLong(key, data)
                else -> saveString(key, gson.toJson(data))
            }
            editor.apply()
        }
    }
}
