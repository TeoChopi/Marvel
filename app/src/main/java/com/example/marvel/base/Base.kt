package com.example.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class Base {

    abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

        lateinit var binding: V

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = getViewBinding()
            setContentView(binding.root)

            init()
        }

        protected abstract fun getViewBinding(): V
        abstract fun init()
    }

    abstract class BaseFragment<V : ViewBinding?>(layout: Int) : Fragment(layout) {

        private var _binding: V? = null
        val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            _binding = getViewBinding()
            return binding?.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            init()
        }

        protected abstract fun getViewBinding(): V
        abstract fun init()

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}