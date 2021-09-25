package kg.geek.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kg.geek.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = getString(R.string.second_activity_title)

        checkData()

        binding.btnStartFirstActivity.setOnClickListener {
            startMainActivity()
        }
    }

    private fun checkData() {
        val text: String? = intent.getStringExtra(TEXT_KEY)
        binding.etInputText.setText(text)
    }

    private fun startMainActivity() {
        if (binding.etInputText.text.isNullOrEmpty())
            Toast.makeText(this, getString(R.string.enter_field), Toast.LENGTH_SHORT).show()
        else send()
    }

    private fun send() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(TEXT_KEY, binding.etInputText.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val TEXT_KEY = "textFromSecondActivity"
    }
}