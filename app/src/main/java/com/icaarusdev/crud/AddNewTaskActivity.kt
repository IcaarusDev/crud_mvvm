package com.icaarusdev.crud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.icaarusdev.crud.databinding.ActivityAddNewTaskBinding

class AddNewTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.editWord.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val task = binding.editWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, task)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.icaarusdev.crud.REPLY"
    }
}