package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityLoginBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityMainBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val pin = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.loginBtn.setOnClickListener {
            var sharedPreferences: SharedPreferences =
                getSharedPreferences(packageName, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            if (binding.pinEt.text.toString() == pin) {
                editor.apply {
                    putString("name", binding.nameEt.text.toString())
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_LONG).show()
                editor.apply {
                    putString("name", null)
                }
                binding.pinEt.text.clear()
            }
        }
    }


}