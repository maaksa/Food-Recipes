package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okio.ByteString.Companion.toByteString
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var sharedPreferences: SharedPreferences =
            getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val sharedNameValue = sharedPreferences.getString("name", null)

        if (sharedNameValue == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}