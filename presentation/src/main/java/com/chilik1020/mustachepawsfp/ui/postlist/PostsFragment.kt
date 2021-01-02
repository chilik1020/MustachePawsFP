package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostsBinding
import com.chilik1020.mustachepawsfp.utils.LOG_TAG
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var pagerAdapter: PostsViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private val listFrag = PostListFragment()
    private val mapFrag = PostMapFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_toolbar_postlist_frag, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.miMyProfile -> {
                navigateToProfileFragment()
                true
            }

            R.id.miLogOut -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun initViews() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarPostListFragment)
        }
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)
        viewModel.viewState.observe(viewLifecycleOwner) {
            listFrag.render(it)
            mapFrag.render(it)
        }


        binding.fabGoToPostCreate.setOnClickListener { navigateToPostCreate() }
        binding.ivReFetchPosts.setOnClickListener { viewModel.fetchPosts() }

        pagerAdapter = PostsViewPagerAdapter(this)
        pagerAdapter.setFragments(mutableListOf<Fragment>().apply {
            add(listFrag)
            add(mapFrag)
        })
        viewPager = binding.pagerPosts
        viewPager.adapter = pagerAdapter
    }

    private fun navigateToPostCreate() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_postList_to_PostCreateGraph)
    }

    private fun navigateToProfileFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_postList_to_profile)
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}