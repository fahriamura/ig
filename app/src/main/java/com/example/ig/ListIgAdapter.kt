import android.view.LayoutInflater
import android.view.ViewDebug.IntToString
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ig.databinding.DicogramCardBinding
import com.example.ig.Database.ItemsItem
import java.util.Locale

class ListDemonAdapter(private val onItemClickCallback: OnItemClickCallback) : ListAdapter<ItemsItem, ListDemonAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var originalList: List<ItemsItem> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DicogramCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(currentItem, position)
        }
    }

    inner class MyViewHolder(private val binding: DicogramCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemsItem){
            binding.tvListName.text = "${item.login}"
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .into(binding.imgListPhoto)
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem, position: Int)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }


}
