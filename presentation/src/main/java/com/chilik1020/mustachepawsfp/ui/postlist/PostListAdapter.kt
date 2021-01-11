package com.chilik1020.mustachepawsfp.ui.postlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.chilik1020.framework.utils.MUSTACHE_BASE_URL_POST_IMAGE
import com.chilik1020.framework.utils.PREFERENCE_FILE_NAME
import com.chilik1020.framework.utils.PREFERENCE_TOKEN_KEY
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.ItemPostBinding
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.chilik1020.mustachepawsfp.utils.timeFromStamp


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
                val link = "${MUSTACHE_BASE_URL_POST_IMAGE}${post.imageLink}"
                Log.d(LOG_TAG, link)
                val token = binding.root.context.getSharedPreferences(
                    PREFERENCE_FILE_NAME,
                    Context.MODE_PRIVATE
                ).getString(
                    PREFERENCE_TOKEN_KEY, ""
                ) ?: ""
                Log.d(LOG_TAG, token)
                val glideWithHeaders = GlideUrl(
                    link,
                    LazyHeaders.Builder()
                        .addHeader("Authorization", token)
                        .build()
                )

                Glide.with(root.context).load(glideWithHeaders).into(ivPostImage)

                Glide.with(root.context).load(R.drawable.default_user_avatar).circleCrop()
                    .into(ivCreatorAvatar)
                tvTypeOfAnimal.text = post.typeOfAnimal
                etvTypeOfAssist.text = post.typeOfAssist
                tvPlace.text = post.location.description
                tvCreatedAt.text = post.createdAt.timeFromStamp()
                tvCreatorUsername.text = post.creatorUsername
//                tvPostStatus.text = if (post.closed) "[Закрыто]" else "[Актуально]"
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