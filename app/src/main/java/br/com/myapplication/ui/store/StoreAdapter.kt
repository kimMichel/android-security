package br.com.myapplication.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.myapplication.databinding.ItemStoreBinding
import br.com.myapplication.models.Password

class StoreAdapter(
    private val passwords: MutableList<Password>,
    private val passwordDecodeClickListener: (position: Int, decodeText: String) -> Unit,
    private val deletePasswordClickListener: (position: Int) -> Unit
): RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    inner class StoreViewHolder(private val binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(password: Password, position: Int) = with(binding) {
            passwordVisible.text = password.password
            btnDecode.setOnClickListener {
                passwordDecodeClickListener(position, password.password)
                notifyItemChanged(position)
            }
            btnDelete.setOnClickListener {
                deletePasswordClickListener(position)
                notifyItemRemoved(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int = passwords.size

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(passwords[position], position)
    }

}