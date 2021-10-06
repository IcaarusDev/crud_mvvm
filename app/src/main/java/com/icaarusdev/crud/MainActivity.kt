package com.icaarusdev.crud

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.icaarusdev.crud.databinding.ActivityMainBinding
import com.icaarusdev.crud.feature.adapter.TaskListAdapter
import com.icaarusdev.crud.feature.data.entity.Task
import com.icaarusdev.crud.feature.presentation.TaskListViewModel
import com.icaarusdev.crud.feature.presentation.TaskListViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val newWordActivityRequestCode = 1
    private val taskViewModel: TaskListViewModel by viewModels {
        TaskListViewModelFactory((application as SampleApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val taskAdapter = TaskListAdapter()
        binding.recyclerview.apply{
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(context)
        }

        taskViewModel.allTasks.observe(this) { tasks ->
            tasks.let { taskAdapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewTaskActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(AddNewTaskActivity.EXTRA_REPLY)?.let { reply ->
                val word = Task(0,reply,"")
                taskViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}