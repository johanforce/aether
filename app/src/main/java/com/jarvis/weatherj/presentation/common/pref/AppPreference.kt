@file:Suppress("unused")

package com.jarvis.weatherj.presentation.common.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.jarvis.weatherj.MainApplication


@Suppress("UNCHECKED_CAST", "unused")
class AppPreference private constructor() {
    private var mSharedPreferences: SharedPreferences
    private val prefsName = "app_pref"

    init {
        mSharedPreferences =
            MainApplication.applicationContext()
                .getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    private object Holder {
        val INSTANCE = AppPreference()
    }

    companion object {
        @JvmStatic
        fun getInstance(): AppPreference {
            return Holder.INSTANCE
        }
    }


    operator fun <T> get(key: String?, anonymousClass: Class<T>): T? {
        try {
            when (anonymousClass) {
                String::class.java -> {
                    return mSharedPreferences.getString(key, "") as T
                }
                Boolean::class.javaObjectType -> {
                    return java.lang.Boolean.valueOf(mSharedPreferences.getBoolean(key, false)) as T
                }
                Boolean::class.java -> {
                    return java.lang.Boolean.valueOf(mSharedPreferences.getBoolean(key, false)) as T
                }
                Float::class.java -> {
                    return java.lang.Float.valueOf(mSharedPreferences.getFloat(key, 0f)) as T
                }
                Int::class.java, Integer::class.java -> {
                    return Integer.valueOf(mSharedPreferences.getInt(key, 0)) as T
                }
                Long::class.java -> {
                    return java.lang.Long.valueOf(mSharedPreferences.getLong(key, 0)) as T
                }
                else -> {
                    return Gson().fromJson(mSharedPreferences.getString(key, ""), anonymousClass)
                }
            }
        } catch (e: ClassCastException) {
            e.printStackTrace()
            return null
        }
    }

    fun <T> put(key: String?, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> {
                editor.putString(key, data as String)
            }
            is Boolean -> {
                editor.putBoolean(key, (data as Boolean))
            }
            is Float -> {
                editor.putFloat(key, (data as Float))
            }
            is Int -> {
                editor.putInt(key, (data as Int))
            }
            is Long -> {
                editor.putLong(key, (data as Long))
            }
            else -> {
                editor.putString(key, MainApplication.applicationContext().gson.toJson(data))
            }
        }
        editor.apply()
    }

    fun <T> putSync(key: String?, data: T) {
        val editor = mSharedPreferences.edit()
        when (data) {
            is String -> {
                editor.putString(key, data as String)
            }
            is Boolean -> {
                editor.putBoolean(key, (data as Boolean))
            }
            is Float -> {
                editor.putFloat(key, (data as Float))
            }
            is Int -> {
                editor.putInt(key, (data as Int))
            }
            is Long -> {
                editor.putLong(key, (data as Long))
            }
            else -> {
                editor.putString(key, MainApplication.applicationContext().gson.toJson(data))
            }
        }
        editor.apply()
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }
}
