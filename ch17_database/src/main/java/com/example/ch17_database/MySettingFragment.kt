package com.example.ch17_database

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.*
import org.w3c.dom.Text

class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        colorPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()

        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)) {
                    "No 설정"
                } else {
                    "설정된 ID : $text"
                }
            }
    }
}