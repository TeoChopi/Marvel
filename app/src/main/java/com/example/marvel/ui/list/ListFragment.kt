package com.example.marvel.ui.list

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.base.Base
import com.example.marvel.databinding.FragmentListBinding
import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.ListResponse
import com.example.marvel.repository.model.ResultsItem
import com.example.marvel.utils.Common.gone
import com.example.marvel.utils.Common.visible

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Base.BaseFragment<FragmentListBinding>(R.layout.fragment_list),
    ListItemDelegate, ListViewModelDelegate {

    private val listViewModel: ListViewModel by viewModels()

    private val listAdapter: ListAdapter by lazy {
        val adapter = ListAdapter(this)
        adapter
    }

    override fun init() {
        listViewModel.delegate = this
        binding.recyclerList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerList.adapter = listAdapter
        getList()
    }

    private fun getList() {
        if (listAdapter.itemCount == 0) {
            binding.progressBar.visible()
            listViewModel.getList()
        } else binding.progressBar.gone()
    }

    override fun onSuccess(list: ListResponse?) {
        binding.progressBar.gone()
        list?.let {
            list.data?.let { listAdapter.setResume(it.results) }
        }
    }

    override fun onFailure(error: ApiError) {
        binding.progressBar.gone()
        Toast.makeText(requireContext(), error.body, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(item: ResultsItem) {
        findNavController().navigate(
            R.id.action_FirstFragment_to_DetailFragment,
            bundleOf(
                "id" to item.id.toString()
            )
        )
    }

    override fun getViewBinding() = FragmentListBinding.inflate(layoutInflater)
}
