package com.example.merfbulletin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button

class ButtonListAdapter(private val context: Context, private val buttons: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return buttons.size
    }

    override fun getItem(position: Int): Any {
        return buttons[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_button, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val buttonText = getItem(position) as String
        viewHolder.button.text = buttonText

        viewHolder.button.setOnClickListener {
            // Handle button click
        }

        return view
    }

    private class ViewHolder(view: View) {
        val button: Button = view.findViewById(R.id.button_item)
    }
}