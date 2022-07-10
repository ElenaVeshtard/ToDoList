package  com.example.test.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.test.R
import com.example.test.data.utils.ImageUtils
import com.example.test.domain.CheckListModel

class CheckListAdapter(
    private var data: List<CheckListModel>,
    private val onItemClick: (View, CheckListModel) -> Unit
) : RecyclerView.Adapter<CheckListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_check_list, parent, false)
        return CheckListHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListHolder, position: Int) {
        val item = data[position]
        holder.image.apply {
            load(item.image)
            scaleType = ImageView.ScaleType.CENTER_CROP
            ImageUtils.setRoundRect(this)
            holder.image.elevation = 25f
        }

        holder.textView.text = item.title
        holder.textViewSub.text = item.subtitle
        holder.itemView.transitionName = item.transitionId()
        holder.itemView.setOnClickListener {
            onItemClick(it, item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


