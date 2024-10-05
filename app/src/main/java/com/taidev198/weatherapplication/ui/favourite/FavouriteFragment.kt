package com.taidev198.weatherapplication.ui.favourite

import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.taidev198.weatherapplication.R
import com.taidev198.weatherapplication.base.BaseFragment
import com.taidev198.weatherapplication.databinding.FragmentFavouriteBinding
import com.taidev198.weatherapplication.ui.FavouriteViewModel
import com.taidev198.weatherapplication.ui.SharedViewModel
import com.taidev198.weatherapplication.utils.listener.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment() : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate),
    OnItemClickListener {
    override val viewModel: FavouriteViewModel by viewModel()
    override lateinit var sharedViewModel: SharedViewModel
    private lateinit var favoriteAdapter: FavouriteAdapter

    override fun initView() {
        sharedViewModel = activityViewModel<SharedViewModel>().value
        favoriteAdapter = FavouriteAdapter(this)
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }

        binding.toolBar.findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            goBackFragment()
        }
    }

    override fun initData() {
        viewModel.getAllFavouriteLocations()
    }

    override fun bindData() {
        viewModel.favouriteLocations.observe(viewLifecycleOwner) { favouriteList ->
            favouriteList?.let {
                favoriteAdapter.updateData(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Log.v("FavouriteFragment", "Error: $errorMessage")
        }
    }

    override fun onItemClickListener(
        view: View,
        position: Int,
    ) {
        val favouriteLocation = viewModel.favouriteLocations.value?.get(position)
        favouriteLocation?.id?.let { viewModel.removeFavouriteItem(it) }
    }
    companion object {
        fun newInstance() = FavouriteFragment()
    }
}