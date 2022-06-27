package com.example.marvel.ui.detail

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvel.R
import com.example.marvel.base.Base
import com.example.marvel.databinding.FragmentDetailBinding
import com.example.marvel.repository.model.ApiError
import com.example.marvel.repository.model.DetailResponse
import com.example.marvel.repository.model.ResultsItem
import com.example.marvel.utils.Common.gone


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Base.BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail),
    DetailViewModelDelegate {

    private val detailViewModel: DetailViewModel by viewModels()

    override fun init() {
        detailViewModel.delegate = this
        arguments?.getString("id")?.let { it ->
            detailViewModel.getDetail(it)
        }
    }

    override fun onSuccess(detail: DetailResponse?) {
        binding.progressBarDetail.gone()
        detail?.let {
            setup(detail.data?.results?.get(0))
        }
    }

    override fun onFailure(error: ApiError) {
        binding.progressBarDetail.gone()
        Toast.makeText(requireContext(), error.body, Toast.LENGTH_LONG).show()
    }

    private fun setup(result: ResultsItem?) {
        setImage(result?.thumbnail?.path.toString().plus(".").plus(result?.thumbnail?.extension))
        binding.txtName.text = result?.name
        binding.txtDescription.text = result?.description
    }

    private fun setImage(url: String) {
        Glide.with(this).load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .format(DecodeFormat.PREFER_ARGB_8888)
            .placeholder(R.drawable.ic_faces)
            .error(R.drawable.ic_faces)
            .circleCrop()
            .into(binding.imageView)
    }

    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)
}