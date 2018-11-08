package com.github.ggaier.gongs.ui.mycollection

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ggaier.gongs.BaseActivity
import com.github.ggaier.gongs.BuildConfig
import com.github.ggaier.gongs.R
import com.github.ggaier.gongs.databinding.ActivityMainBinding
import com.github.ggaier.gongs.util.obtainViewModel

class MainActivity : BaseActivity() {

    private lateinit var model: MyCollectionViewModel
    val refreshListener = SwipeRefreshLayout.OnRefreshListener { model.getCollection(BuildConfig.COLLECTION_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = obtainViewModel(MyCollectionViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = model
        binding.activity = this
        binding.singers.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.singers.adapter = ArtistAdapter()
        model.getCollection(BuildConfig.COLLECTION_ID)
    }


}
