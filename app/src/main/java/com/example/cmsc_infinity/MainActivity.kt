package com.example.cmsc_infinity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity() {

    private lateinit var adView : AdView
    private lateinit var lastScoreTextView : TextView
    private lateinit var start131Button : Button
    private lateinit var start132Button : Button
    private lateinit var start216Button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
        // TODO: use persistent local storage to set this value and the dark mode
        lastScoreTextView = findViewById(R.id.lastScoreText)

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
    }
}