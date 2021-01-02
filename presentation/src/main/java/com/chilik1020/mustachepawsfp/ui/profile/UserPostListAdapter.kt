package com.chilik1020.mustachepawsfp.ui.profile

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.chilik1020.framework.utils.BASE_URL_POST_IMAGE
import com.chilik1020.framework.utils.PREFERENCE_FILE_NAME
import com.chilik1020.framework.utils.PREFERENCE_TOKEN_KEY
import com.chilik1020.mustachepawsfp.databinding.ItemUserPostBinding
import com.chilik1020.mustachepawsfp.models.PostPresentationModel
import com.chilik1020.mustachepawsfp.utils.LOG_TAG

class UserPostListAdapter : RecyclerView.Adapter<UserPostListAdapter.UserPostViewHolder>() {

    private var list: List<PostPresentationModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        val binding =
            ItemUserPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserPostViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setData(items: List<PostPresentationModel>) {
        this.list = items
        notifyDataSetChanged()
    }


    class UserPostViewHolder(private val binding: ItemUserPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostPresentationModel) {
            with(binding) {
                val link = "$BASE_URL_POST_IMAGE${post.imageLink}"
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
                Glide.with(root.context).load(link).into(ivPostImage)
            }
        }
    }
}