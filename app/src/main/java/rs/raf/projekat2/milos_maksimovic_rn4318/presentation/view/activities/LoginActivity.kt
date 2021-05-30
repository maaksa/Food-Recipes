package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityLoginBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.UserContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

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
            if (binding.pinEt.text.toString() == pin) {

                val user = User(
                    binding.nameEt.text.toString(),
                    binding.pinEt.text.toString()
                )
                userViewModel.addUser(user)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_LONG).show()

                binding.pinEt.text.clear()
            }
        }


    }
}