package com.chilik1020.mustachepawsfp.ui.postlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.Glide
import com.chilik1020.framework.utils.BASE_URL
import com.chilik1020.mustachepawsfp.databinding.ItemPostBinding
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import com.chilik1020.mustachepawsfp.utils.LOG_TAG

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private var list: List<PostPresentationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(items: List<PostPresentationModel>) {
        this.list = items
        notifyDataSetChanged()
    }


    class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostPresentationModel) {
            with(binding) {
                val link = "${BASE_URL}mustachepaws/posts/image/${post.imageLink}"
                Log.d(LOG_TAG, link)
                Glide.with(root.context).load(link).into(ivPostPhoto)

                Glide.with(root.context).load(link).circleCrop().into(ivCreatorAvatar)
                tvCreatorUsername.text = post.creatorUsername
                tvPostStatus.text = if (post.closed) "[Закрыто]" else "[Актуально]"
                etvPostDescription.text = post.description

                etvPostDescription.setInterpolator(OvershootInterpolator())
                etvPostDescription.setOnClickListener {
                    it as ExpandableTextView
                    if (it.isExpanded) {
                        it.collapse()
                    } else {
                        it.expand()
                    }
                }
            }
        }
    }
}