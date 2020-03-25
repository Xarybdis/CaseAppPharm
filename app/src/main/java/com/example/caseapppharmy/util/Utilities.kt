package com.example.caseapppharmy.util

import android.widget.EditText

class Utilities {
    companion object {

        fun validateFields(vararg viewEditText: EditText) {

            viewEditText.forEach {
                if (it.text.isEmpty()) {
                    it.setError("Lütfen bu alanı doldurun.")
                    return
                }
            }
        }
    }
}