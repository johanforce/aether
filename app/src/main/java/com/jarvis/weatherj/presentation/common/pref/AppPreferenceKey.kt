@file:Suppress("unused")

package com.jarvis.bmihealth.presentation.common.pref


/* Công thức đặt tên key:
    KEY_ObjectClass_Scope_Noun
    Example: KEY_STRING_ALL_APP_NAME
             KEY_BOOLEEN_ALL_IS_FIRST_TIME_OPEN_APP
             KEY_PROFILE_URSER_ALL_PROFILE_MODEL
*/
interface AppPreferenceKey {
    companion object {
        const val KEY_PRIVACY =
            "aHR0cHM6Ly9kb2NzLmdvb2dsZS5jb20vZG9jdW1lbnQvZC9lLzJQQUNYLTF2UW1fMklTSDFzY3lZRjl6ZG0tb3NtQU1IazFjUWxfREFZVWNmUUYtSjdHa3RUOHZfUmQ0WDc2SDZseVlXd0EySGR0Um9DWm9LNnlyODVkL3B1Yg=="
        const val KEY_TOS =
            "aHR0cHM6Ly9kb2NzLmdvb2dsZS5jb20vZG9jdW1lbnQvZC9lLzJQQUNYLTF2VEFkMzBSUGlHQVhOOFlYMXRCRFZXRlNkQ0FKb2JHOEQ4MU1DRzczNGpjUXQtRV9SQjJfLVZFWGhjWHhxczBHUktOaFJVZDBfc0R4dC1oL3B1Yg=="
        const val KEY_LOCALE_ALL_APP_LANGUAGE = "key_locale_all_app_language"
        const val KEY_STRING_ALL_NOUN = "key_string_all_url_banner"
        const val KEY_INT_RATE_STAR = "key_int_rate_star"
        const val KEY_BOOLEAN_UNIT_METRIC = "key_boolean_unit_metric"
        const val KEY_BOOLEAN_UNIT_METRIC_GOAL = "key_boolean_unit_metric_goal"
        const val KEY_IS_FIRST_USING_APP = "key_is_first_using_app"
        const val KEY_THEMEMODE = "key_thememode"
        const val KEY_CHECK_CHANGE_UNIT_FIRST = "key_check_change_unit_first"
        const val KEY_IS_INPUT_INFO_SUCCESS = "key_is_input_info_success"
        const val KEY_IS_CHANGE_LANGUAGE = "key_is_change_language"
        const val KEY_LOCALE_SETTING = "key_locale_setting"
        const val KEY_INT_USER_ID = "key_user_id"
        const val KEY_FIRST_TIME_SHOW_RATE_DIALOG = "key_first_time_show_dialog_rate"
        const val KEY_LAST_TIME_SHOW_RATE_DIALOG = "key_start_current_day"
        const val KEY_SHOW_RATE_DIALOG_LOVE_WELLBE_QUESTION = "key_show_rate_dialog_love_question"
        const val KEY_RATED_DIALOG_LOVE_WELLBE_QUESTION = "key_rated_dialog_love_question"
        const val KEY_SALE_START_TIME = "key_sale_start_time"
        const val KEY_SALE_END_TIME = "key_sale_end_time"
        const val KEY_LAST_TIME_SUGGEST_PURCHASE = "key_last_time_sugguest_purchase"
        const val KEY_CONFIG_ADS = "key_config_ads"

        const val KEY_SAVE_WEIGHT_START_GOAL = "key_save_weight_start_goal"
        const val KEY_SAVE_WEIGHT_GOAL_END = "key_save_weight_goal_end"
        const val KEY_SAVE_TIME_START_GOAL = "key_save_time_start_goal"
        const val KEY_SAVE_TIME_END_GOAL = "key_save_time_end_goal"
        const val KEY_SAVE_WEIGHT_FINAL = "key_save_weight_final"

        const val KEY_INT_SAVE_PROFILE_ID_SELECTED = "key_int_save_profile_id_selected"
        const val KEY_USER_PROFILE_PRIMARY = "key_user_primary"

        const val KEY_BATTERY_CLICK = "key_battery_click"
        const val KEY_AUTO_START_CLICK = "key_auto_start_click"
        const val KEY_TIME_FIRST_OPEN_APP_IN_DAY = "key_time_first_open_app_in_day"

        const val KEY_IS_FETCH_DATA_SUCCESS = "key_is_fetch_data_success"
        const val KEY_LONG_LAST_SYNC_TIME_WEIGHT = "key_last_sync_time_weight"
        const val KEY_LONG_LAST_SYNC_TIME_HEIGHT = "key_last_sync_time_height"
        const val KEY_LONG_LAST_SYNC_TIME_USER_PROFILE = "key_last_sync_time_user_profile"
        const val KEY_LONG_LAST_SYNC_TIME_STEP = "key_last_sync_time_step"
        const val KEY_LONG_LAST_SYNC_TIME_STEP_GOAL = "key_last_sync_time_step_goal"
        const val KEY_LONG_LAST_SYNC_TIME_WEIGHT_GOAL = "key_last_sync_time_weight_goal"
        const val KEY_LONG_LAST_SYNC_TIME_BODY_INDEX = "key_long_last_sync_time_bodyindex"

        const val KEY_IS_LOGIN = "key_is_login"

        const val KEY_TOPIC_SUBSCRIBED = "key_topic_subscribed"
    }
}
