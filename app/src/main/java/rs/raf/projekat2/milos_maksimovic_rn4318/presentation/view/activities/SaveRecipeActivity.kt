package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.R
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivitySaveRecipeBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivitySingleFoodBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodRecipesContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodRecipesViewModel
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class SaveRecipeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivitySaveRecipeBinding
    private lateinit var foodRecipe: FoodRecipe
    private lateinit var date: String
    private lateinit var categoryFood: String

    var category = arrayOf("Breakfast", "Lunch", "Dinner")
    var spinner: Spinner? = null

    var formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initUi()
        initListeners()
        initSpinner()
    }

    private fun initUi() {
        foodRecipe = intent.getSerializableExtra("foodRecipe") as FoodRecipe

        binding.titleTv.text = foodRecipe.categoryName

    }

    private fun initListeners() {
        binding.datePickerButton.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datepicker = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, month)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    date = formatDate.format(selectDate.time)
                    binding.datePickerButton.text = date
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )
            datepicker.show()
        }

        binding.addImgIv.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Camera")
            builder.setMessage("Do you want to open your camera?")
            builder.setPositiveButton("Open") { dialogInterface: DialogInterface, i: Int ->
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    //pokrecemo activity for result
                    getPicture.launch(takePictureIntent)
                } catch (e: ActivityNotFoundException) {//posto ne mozemo da garantujemo da nas telfon ima tu app koju pokrecemo kao implicitni intent
                    Toast.makeText(this, "Camera app not found!", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                Glide.with(this).load(foodRecipe.categoryImgUrl).into(binding.addImgIv)
            }
            builder.show()
        }
    }

    private fun initSpinner() {
        spinner = binding.spinner
        spinner!!.setOnItemSelectedListener(this)

        val aa = ArrayAdapter(this, R.layout.simple_spinner_item, category)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)
    }

    private val getPicture =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //dobijamo rezultat od intenta od kog ocekujemo da vrati
            if (it.resultCode == RESULT_OK) {
                val bundle = it.data?.extras
                val finalPhoto: Bitmap =
                    bundle?.get("data") as Bitmap //vraca Any "data" kljuc pa kastujemo u Bitmap
                val path: String? = saveToInternalStorage(finalPhoto, "slika")
                if (path != null) {
                    loadImageFromStorage(path, "slika")
                }
            }
        }

    private fun saveToInternalStorage(bitmapImage: Bitmap, fileName: String): String? {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val myPath = File(directory, "$fileName.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(myPath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return directory.absolutePath
    }

    private fun loadImageFromStorage(path: String, fileName: String) {
        try {
            val f = File(path, "$fileName.jpg")
            val b = BitmapFactory.decodeStream(FileInputStream(f))
            Glide.with(this).load(b).placeholder(R.drawable.ic_delete)
                .into(binding.addImgIv)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        categoryFood = category[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}

