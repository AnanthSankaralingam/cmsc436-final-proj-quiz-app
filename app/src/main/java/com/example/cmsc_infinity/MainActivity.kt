package com.example.cmsc_infinity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity() {

    private lateinit var adView : AdView
    private lateinit var lastScoreTextView : TextView
    private lateinit var start131Button : Button
    private lateinit var start132Button : Button
    private lateinit var start216Button : Button
    private lateinit var viewLeaderboardButton: Button
    private lateinit var darkModeSwitch: Switch
    private var isInitializing = true

    override fun onCreate(savedInstanceState: Bundle?) {

        //dark mode
        val prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
        val savedDark = prefs.getBoolean("DARK_MODE", false)

        AppCompatDelegate.setDefaultNightMode(
            if (savedDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main)

        darkModeSwitch = findViewById(R.id.darkModeSwitch)
        darkModeSwitch.isChecked = savedDark

        isInitializing = true

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isInitializing) return@setOnCheckedChangeListener

            prefs.edit().putBoolean("DARK_MODE", isChecked).apply()

            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        darkModeSwitch.post { isInitializing = false }

        // ad stuff
        adView = AdView( this )
        var adSize : AdSize = AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT )
        adView.setAdSize( adSize )
        //google sample ad banner
        var adUnitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId
        var adRequest = AdRequest.Builder().build()
        var adLayout : LinearLayout = findViewById<LinearLayout>( R.id.ad_view )
        adLayout.addView( adView )
        // request the ad to be served by Google
        adView.loadAd( adRequest )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val last = prefs.getInt("LAST_SCORE", -1)

        lastScoreTextView = findViewById(R.id.lastScoreText)

        if (last != -1) {
            lastScoreTextView.text = "Last Score: $last"
        } else {
            lastScoreTextView.text = "Last Score: 0"
        }

        // onclicks for buttons. route to quiz activity with intent mentioning what course data to load
        start131Button = findViewById(R.id.start131Button)
        start131Button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("courseSelected", "CMSC131")
            startActivity(intent)
        }

        start132Button = findViewById(R.id.start132Button)
        start132Button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("courseSelected", "CMSC132")
            startActivity(intent)
        }

        start216Button = findViewById(R.id.start216Button)
        start216Button.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("courseSelected", "CMSC216")
            startActivity(intent)
        }
        viewLeaderboardButton = findViewById(R.id.viewLeaderboardButton)
        viewLeaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            startActivity(intent)
        }

    }
}