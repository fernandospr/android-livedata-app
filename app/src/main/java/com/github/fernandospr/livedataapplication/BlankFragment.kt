package com.github.fernandospr.livedataapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.fernandospr.livedataapplication.databinding.BlankFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.blank_fragment.*

class BlankFragment : Fragment() {
    
    private lateinit var binding: BlankFragmentBinding

    private lateinit var viewModel: BlankViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[BlankViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BlankFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_LONG).show()
        }
    }
}
