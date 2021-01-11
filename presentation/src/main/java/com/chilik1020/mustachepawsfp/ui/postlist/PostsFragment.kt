package com.chilik1020.mustachepawsfp.ui.postlist

import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.chilik1020.mustachepawsfp.R
import com.chilik1020.mustachepawsfp.databinding.FragmentPostsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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

    private val tabIcons = arrayOf(R.drawable.ic_list_black, R.drawable.ic_map_black)

    private var isFabExpanded = false
    private lateinit var animationFabOpen: Animation
    private lateinit var animationFabClose: Animation

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

    override fun onPause() {
        super.onPause()
        if (isFabExpanded) {
            collapseFabMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_toolbar_postlist_frag, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.miMyProfilePostListMenu -> {
                navigateToProfileFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun initViews() {
        animationFabOpen = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open_animation)
        animationFabClose =
            AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close_animation)

        with(binding) {
            fabGoToPostCreate.setOnClickListener {
                if (isFabExpanded) {
                    collapseFabMenu()
                } else {
                    expandFabMenu()
                }
            }
            fabCameraImage.setOnClickListener {
                collapseFabMenu()
                navigateToPostCreate()
            }
            fabGalleryImage.setOnClickListener {
                collapseFabMenu()
                navigateToPostCreate()
            }
        }


        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbarPostListFragment)
        }
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)
        viewModel.viewState.observe(viewLifecycleOwner) {
            render(it)
            if (listFrag.isAdded) listFrag.render(it)
            if (mapFrag.isAdded) mapFrag.render(it)
        }
        viewModel.fetchPosts()

        pagerAdapter = PostsViewPagerAdapter(this)
        pagerAdapter.setFragments(mutableListOf<Fragment>().apply {
            add(listFrag)
            add(mapFrag)
        })
        viewPager = binding.pagerPosts
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayoutPosts, binding.pagerPosts)
        { tab: TabLayout.Tab, position: Int -> tab.setIcon(tabIcons[position]) }.attach()

        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchPosts() }
    }

    private fun render(state: PostsViewState) {
        when (state) {
            PostsViewState.Loading -> {
            }
            is PostsViewState.Success -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            is PostsViewState.Error -> {
                showSnackBarMessage(state.msg)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }


    private fun navigateToPostCreate() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_postList_to_ImageCapture)
    }

    private fun navigateToProfileFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_postList_to_profile)
    }

    private fun showSnackBarMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun expandFabMenu() {
        rotateMainFab(45.0f)
        with(binding) {
            fabCameraImage.startAnimation(animationFabOpen)
            fabGalleryImage.startAnimation(animationFabOpen)
            fabCameraImage.isClickable = true
            fabGalleryImage.isClickable = true
        }
        isFabExpanded = true
    }

    private fun collapseFabMenu() {
        rotateMainFab(0.0f)
        with(binding) {
            fabCameraImage.startAnimation(animationFabClose)
            fabGalleryImage.startAnimation(animationFabClose)
            fabCameraImage.isClickable = false
            fabGalleryImage.isClickable = false
        }
        isFabExpanded = false
    }

    private fun rotateMainFab(angle: Float) {
        ViewCompat.animate(binding.fabGoToPostCreate)
            .rotation(angle)
            .withLayer()
            .setDuration(300)
            .setInterpolator(OvershootInterpolator(10.0F))
            .start()
    }

}