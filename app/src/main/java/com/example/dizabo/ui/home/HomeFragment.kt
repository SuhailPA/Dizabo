package com.example.dizabo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dizabo.R
import com.example.dizabo.data.getalldata.Data
import com.example.dizabo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initUI()
        return binding?.root
    }

    private fun initUI() {
        homeAdapter = HomeAdapter()
        binding?.homeItems?.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        homeViewModel.homeItems.observe(viewLifecycleOwner) {
            homeAdapter.differ.submitList(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}