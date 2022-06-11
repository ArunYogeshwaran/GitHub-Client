package com.ayogeshwaran.githubclient.closedpr

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.common.DateUtils.getFormattedDate
import com.ayogeshwaran.githubclient.databinding.ClosedPrListItemBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class ClosedPrListAdapter(val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = ArrayList<UIClosedPullRequest>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateListData(
        dataList: List<UIClosedPullRequest>
    ) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding =
            ClosedPrListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ClosedPrItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ClosedPrItemViewHolder) {
            holder.bind(dataList[position])
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ClosedPrItemViewHolder(private val binding: ClosedPrListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(uiClosedPullRequest: UIClosedPullRequest) {
            binding.prTitle.text = uiClosedPullRequest.title
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            date.parse(uiClosedPullRequest.createdDate)?.let {
                binding.prSubtitle.text = binding.root.context.getString(
                    R.string.pr_subtitle,
                    uiClosedPullRequest.userName,
                    getFormattedDate(it)
                )
            }
            (date.parse(uiClosedPullRequest.closedDate))?.let {
                binding.prClosedDate.text = getFormattedDate(it)
            }
            Glide.with(binding.root.context).load(uiClosedPullRequest.userImageUrl)
                .into(binding.prUserIcon)
        }
    }
}