package kg.geek.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geek.myapplication.databinding.ItemRvBinding
import kotlin.collections.ArrayList

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var list: ArrayList<String> = ArrayList()

    fun setList(list: ArrayList<String>) {
        this.list = list
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(s: String) {
            binding.tvItem.text = s
        }
    }
}