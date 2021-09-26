package kg.geek.myapplication.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kg.geek.myapplication.R
import kg.geek.myapplication.databinding.ActivityMainBinding
import kg.geek.myapplication.important_data.Keys
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var list: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = getString(R.string.main_activity_title)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK)
                    checkText(result.data)
            }

        binding.btnStartSecondActivity.setOnClickListener {
            startSecondActivity()
        }

        binding.btnStartRvActivity.setOnClickListener {
            startRvActivity()
        }

    }

    private fun startRvActivity() {
        val intent = Intent(this, RecyclerActivity::class.java)
        intent.putExtra(Keys.TEXT_KEY, list)
        resultLauncher.launch(intent)
    }

    private fun checkText(data: Intent?) {
        if (data != null) {
            val text = data.getStringExtra(Keys.TEXT_KEY)
            binding.etInputText.setText(text)
            list.add(text.toString())
        }
    }

    private fun startSecondActivity() {
        if (binding.etInputText.text.isNullOrEmpty())
            Toast.makeText(this, getString(R.string.enter_field), Toast.LENGTH_SHORT).show()
        else send()
    }

    private fun send() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(Keys.TEXT_KEY, binding.etInputText.text.toString())
        resultLauncher.launch(intent)
        list.add(binding.etInputText.text.toString())
    }

}