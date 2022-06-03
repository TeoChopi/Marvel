package com.example.marvel.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvel.R
import com.example.marvel.base.Base
import com.example.marvel.databinding.FragmentDetailBinding
import com.example.marvel.repository.model.ResultsItem


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Base.BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val detailViewModel: DetailViewModel by viewModels()
    private var path: String? = null
    private var extension: String? = null

    override fun init() {
        arguments?.getString("id")?.let { it ->
            detailViewModel.getDetail(it).observe(this) { detail ->
                detail?.let {
                    //setup(detail.data?.results?.get(0))
                }
            }
        }
        arguments?.getString("name")?.let {
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it
            binding.txtName.text = it
        }
        arguments?.getString("description")?.let {
            binding.txtDescription.text = it
        }
        arguments?.getString("path")?.let {
            path = it
        }
        arguments?.getString("extension")?.let {
            extension = it
        }
        setImage(path.toString().plus(".").plus(extension))
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