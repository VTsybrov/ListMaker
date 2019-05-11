package com.cyberoff.listmaker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemRecyclerView: RecyclerView
    lateinit var addTaskButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name
        listItemRecyclerView = findViewById<RecyclerView>(R.id.list_items_recyclerview)
        listItemRecyclerView.layoutManager = LinearLayoutManager(this)
        listItemRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

        private fun showCreateTaskDialog(){
            val taskEditText = EditText(this)
            taskEditText.inputType = InputType.TYPE_CLASS_TEXT
            AlertDialog.Builder(this).setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task,{dialog, ->
                    val task = taskEditText.text.toString()
                })
    }
}
