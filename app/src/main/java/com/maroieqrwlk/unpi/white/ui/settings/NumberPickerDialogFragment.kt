package com.maroieqrwlk.unpi.white.ui.settings

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.maroieqrwlk.unpi.white.MAX_SIZE
import com.maroieqrwlk.unpi.R

class NumberPickerDialogFragment(
    val isTextBased: Boolean,
    private val title: Int,
    private val range: IntRange = 1..MAX_SIZE,
    private val initialValue: Int = range.first
) : DialogFragment() {
    private var listener: ((Int) -> Unit)? = null

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        if (isTextBased) {
            @SuppressLint("InflateParams")
            val view =
                requireActivity().layoutInflater.inflate(
                    R.layout.fragment_dialog_numberpicker_edittext,
                    null,
                    false
                )
            val editText = view.findViewById<EditText>(R.id.edittext_value)

            editText.setText(initialValue.toString())

            view.findViewById<TextView>(R.id.spinner_title).setText(title)
            view.findViewById<TextView>(R.id.spinner_range).text =
                getString(R.string.dialog_spinner_range).format(range.first, range.last)

            builder.setView(view)
            builder.setPositiveButton(android.R.string.ok) { _, _ ->
                listener?.let {
                    val value = editText.text.toString()
                    val intValue = if (value.isBlank()) range.first else {
                        val minValue = range.first.toBigInteger()
                        val maxValue = range.last.toBigInteger()
                        val bigIntValue = value.toBigInteger()
                        bigIntValue.min(maxValue).max(minValue).toInt()
                    }
                    it(intValue)
                }
            }
        } else {
            @SuppressLint("InflateParams")
            val view =
                requireActivity().layoutInflater.inflate(
                    R.layout.fragment_dialog_numberpicker_spinner,
                    null,
                    false
                )
        }

        return builder.create()
    }
}
