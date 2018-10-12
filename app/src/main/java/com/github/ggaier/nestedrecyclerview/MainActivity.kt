package com.github.ggaier.nestedrecyclerview

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.ggaier.nestedrecyclerview.databinding.ActivityMainBinding
import org.jetbrains.anko.dip

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.singers.addItemDecoration(DividerItemDecoration(this@MainActivity,
                DividerItemDecoration.VERTICAL).also {
            val shapeDrawable = ShapeDrawable()
            shapeDrawable.intrinsicHeight = dip(8)
            it.setDrawable(shapeDrawable)
        })
        binding.singers.adapter = SingersAdapter()
    }
}
