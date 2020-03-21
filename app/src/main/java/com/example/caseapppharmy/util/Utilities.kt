package com.example.caseapppharmy.util

import android.widget.EditText

class Utilities {
    companion object {
        fun validateFields(text: EditText, text2: EditText): Boolean {
            if (text.text.isEmpty()) {
                text.setError("Lütfen bu alanı doldurun.")
                return false
            }
            if (text2.text.isEmpty()) {
                text2.setError("Lütfen bu alanı doldurun.")
                return false
            } else
                return true
        }
    }
}