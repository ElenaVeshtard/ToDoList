package  com.example.test.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class CheckListHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.imagePost)
    val textView: TextView = view.findViewById(R.id.textTitle)
    val textViewSub: TextView = view.findViewById(R.id.textSubtitle)
}