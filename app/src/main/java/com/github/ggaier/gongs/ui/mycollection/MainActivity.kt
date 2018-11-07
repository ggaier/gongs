package com.github.ggaier.gongs.ui.mycollection

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.ggaier.gongs.BaseActivity
import com.github.ggaier.gongs.R
import com.github.ggaier.gongs.databinding.ActivityMainBinding
import com.github.ggaier.gongs.util.obtainViewModel
import org.jetbrains.anko.dip

class MainActivity : BaseActivity() {

    private lateinit var model: MyCollectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.singers.addItemDecoration(DividerItemDecoration(this@MainActivity,
                DividerItemDecoration.VERTICAL).also {
            val shapeDrawable = ShapeDrawable()
            shapeDrawable.intrinsicHeight = dip(8)
            it.setDrawable(shapeDrawable)
        })
        binding.singers.adapter = ArtistsAdapter()
        model = obtainViewModel(MyCollectionViewModel::class.java)
        model.getCollection()
    }

}
