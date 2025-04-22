package np.com.myapp



import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 2500 // 2.5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Load animation
        val logo = findViewById<ImageView>(R.id.logoImage)
        val tagline = findViewById<TextView>(R.id.taglineText)

        val anim = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        logo.startAnimation(anim)
        tagline.startAnimation(anim)

        // Navigate after delay
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}
