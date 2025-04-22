package np.com.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the RecyclerView for courses
        recyclerView = findViewById(R.id.recyclerTrending)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Create a list of courses
        val courseList = listOf(
            Course("Android Basics", "Learn Android from scratch", R.drawable.android_image, 60),
            Course("Kotlin Crash Course", "Master Kotlin in 7 days", R.drawable.kotlin_image, 80),
            Course("Jetpack Compose", "Modern UI toolkit for Android", R.drawable.compose_image, 40),
            Course("Machine Learning", "Intro to ML with hands-on projects", R.drawable.ml_image, 50),
            Course("Data Structures & Algorithms", "Crack interviews with DSA", R.drawable.dsa_image, 70),
            Course("Flutter Development", "Cross-platform apps with Flutter", R.drawable.flutter_image, 65),
            Course("Web Development", "Full-stack web dev course", R.drawable.web_image, 85)
        )

        // Set up the adapter for the RecyclerView
        adapter = CourseAdapter(this, courseList) { course ->
            val intent = Intent(this, CourseDetailActivity::class.java).apply {
                putExtra("title", course.title)
                putExtra("description", course.description)
                putExtra("thumbnail", course.thumbnailResId)
                putExtra("progress", course.progress)
                putExtra("author", "John Doe")
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // Set up BottomNavigationView
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Already on Home, do nothing or refresh if needed
                    true
                }
                R.id.nav_profile -> {
                    // Navigate to Dashboard Activity
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_leaderboard -> {
                    // Launch the Leaderboard screen
                    val intent = Intent(this, LeaderboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
