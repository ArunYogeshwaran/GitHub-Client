package com.ayogeshwaran.githubclient.closedpr

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ayogeshwaran.githubclient.R
import com.ayogeshwaran.githubclient.common.DateUtils.ISO_TIME_FORMAT
import com.ayogeshwaran.githubclient.common.DateUtils.getFormattedDate
import com.ayogeshwaran.githubclient.databinding.ClosedPrListItemBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class ClosedPrListAdapter(fragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var prList = ArrayList<UIClosedPullRequest>()
    private var onPrItemClickedListener: OnPrItemClickedListener? = null

    init {
        onPrItemClickedListener = fragment as? OnPrItemClickedListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateListData(
        dataList: List<UIClosedPullRequest>
    ) {
        this.prList.clear()
        this.prList.addAll(dataList)
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
            holder.bind(prList[position])
        }
    }

    override fun getItemCount(): Int {
        return prList.size
    }

    inner class ClosedPrItemViewHolder(private val binding: ClosedPrListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(uiClosedPullRequest: UIClosedPullRequest) {
            binding.prTitle.text = uiClosedPullRequest.title
            setSubtitle(uiClosedPullRequest)
            Glide.with(binding.root.context).load(uiClosedPullRequest.userImageUrl)
                .into(binding.prUserIcon)
            binding.root.setOnClickListener {
                onPrItemClickedListener?.onItemClicked(uiClosedPullRequest)
            }
        }

        private fun setSubtitle(uiClosedPullRequest: UIClosedPullRequest) {
            val date = SimpleDateFormat(ISO_TIME_FORMAT, Locale.getDefault())
            date.parse(uiClosedPullRequest.createdDate)?.let {
                binding.prSubtitle.text = binding.root.context.getString(
                    R.string.pr_subtitle,
                    uiClosedPullRequest.userName,
                    getFormattedDate(it)
                )
            }
            date.parse(uiClosedPullRequest.closedDate)?.let {
                binding.prClosedDate.text = getFormattedDate(it)
            }
        }
    }

    interface OnPrItemClickedListener {
        fun onItemClicked(uiClosedPullRequest: UIClosedPullRequest)
    }
}