package com.jym.exampleaddfull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.jym.exampleaddfull.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val vBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	private var mInterstitialAd: InterstitialAd? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(vBinding.root)
		loadAd()

		vBinding.showAdBtn.setOnClickListener {
			if (mInterstitialAd != null) {
				mInterstitialAd?.show(this)
			} else {
				Toast.makeText(applicationContext, "광고가 준비될 동안 기다려주세요", Toast.LENGTH_LONG).show()
			}
			loadAd()
		}



	} // onCreate End

	private fun loadAd(){
		val adRequest = AdRequest.Builder().build()
		InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
			override fun onAdFailedToLoad(adError: LoadAdError) {
				adError.let { Log.d("test", it.toString()) }
				mInterstitialAd = null
			}

			override fun onAdLoaded(interstitialAd: InterstitialAd) {
				Toast.makeText(applicationContext, "광고 준비되었습니다.", Toast.LENGTH_LONG).show()
				mInterstitialAd = interstitialAd
			}
		})
	}

}