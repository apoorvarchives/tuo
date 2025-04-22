package np.com.myapp

import android.widget.TextView
import android.widget.ImageView
import android.widget.ProgressBar
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import np.com.myapp.R
import np.com.myapp.Course

class CourseAdapter(
    private val context: Context,
    private val courseList: List<Course>,
    private val onItemClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]

        holder.itemView.findViewById<ImageView>(R.id.CourseImage).setImageResource(course.thumbnailResId)
        holder.itemView.findViewById<TextView>(R.id.CourseTitle).text = course.title
        holder.itemView.findViewById<TextView>(R.id.CourseDescription).text = course.description
        holder.itemView.findViewById<ProgressBar>(R.id.CourseProgress).progress = course.progress


        holder.itemView.setOnClickListener {
            onItemClick(course)
        }
    }

    override fun getItemCount(): Int = courseList.size
}