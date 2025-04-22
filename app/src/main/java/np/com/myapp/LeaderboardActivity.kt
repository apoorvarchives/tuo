package np.com.myapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView = findViewById<RecyclerView>(R.id.leaderboardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample leaderboard data
        val userList = listOf(
            UserRank("Akanksha", 1200, 1, "🔥"),
            UserRank("Rahul", 1150, 2, "🚀"),
            UserRank("Neha", 1100, 3, "🌟"),
            UserRank("You", 1090, 4, "👑")
        )

        recyclerView.adapter = LeaderboardAdapter(userList)
    }
}
