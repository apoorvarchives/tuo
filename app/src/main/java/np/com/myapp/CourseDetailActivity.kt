package np.com.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView
import android.widget.ProgressBar

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import np.com.myapp.Course
import np.com.myapp.CourseAdapter
class CourseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val title = intent.getStringExtra("title")
        val thumbnail = intent.getIntExtra("thumbnail", 0)
        val description = intent.getStringExtra("description")
        val author = intent.getStringExtra("author")
        val progress = intent.getIntExtra("progress", 0)

        // Bind to UI
        findViewById<TextView>(R.id.courseTitle).text = title
        findViewById<ImageView>(R.id.courseImage).setImageResource(thumbnail)
        findViewById<TextView>(R.id.courseDesc).text = description
        findViewById<TextView>(R.id.courseAuthor).text = "by $author"
        findViewById<ProgressBar>(R.id.courseProgress).progress = progress
    }
}