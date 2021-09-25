package kg.geek.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kg.geek.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

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
    }

    private fun checkText(data: Intent?) {
        if (data != null) {
            val text = data.getStringExtra(TEXT_KEY)
            binding.etInputText.setText(text)
        }
    }

    private fun startSecondActivity() {
        if (binding.etInputText.text.isNullOrEmpty())
            Toast.makeText(this, getString(R.string.enter_field), Toast.LENGTH_SHORT).show()
        else send()
    }

    private fun send() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(TEXT_KEY, binding.etInputText.text.toString())
        resultLauncher.launch(intent)
    }

    companion object {
        private const val TEXT_KEY = "textFromSecondActivity"
    }
}