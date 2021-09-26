package kg.geek.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kg.geek.myapplication.adapters.RecyclerAdapter
import kg.geek.myapplication.databinding.ActivityRecyclerBinding
import kg.geek.myapplication.important_data.Keys
import java.util.*

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var adapter: RecyclerAdapter
    private lateinit var list: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()

    }

    private fun initList() {
        adapter = RecyclerAdapter()
        list = intent.getStringArrayListExtra(Keys.TEXT_KEY)!!
        adapter.setList(list)
        binding.rvText.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        binding.rvText.adapter = adapter
    }
}