package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
class BlankFragment2 : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TAG", "onAttach2" )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "onCreate2" )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated2" )
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("TAG", "onViewStateRestored2" )
    }
    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart2" )
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume2" )
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause2" )
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop2" )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TAG", "onSaveInstanceState2" )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TAG", "onDestroyView2" )
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy2" )
    }

}