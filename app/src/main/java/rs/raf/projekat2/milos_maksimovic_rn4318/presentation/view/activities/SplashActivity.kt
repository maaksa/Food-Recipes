package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import okio.ByteString.Companion.toByteString
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.UserContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.UserState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.UserViewModel
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()


    }

    private fun initObservers() {
        userViewModel.userState.observe(this, Observer {
            renderStateCategory(it)
        })

        userViewModel.getByPin()

    }

    private fun renderStateCategory(state: UserState) {
        when (state) {
            is UserState.Success -> {
                if (state.userCnt == 0) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            is UserState.Error -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            is UserState.DataFetched -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
//                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG)
//                    .show()
            }
            is UserState.Loading -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

}