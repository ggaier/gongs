package com.github.ggaier.nestedrecyclerview

import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.dip

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        singers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL).also {
                val shapeDrawable = ShapeDrawable()
                shapeDrawable.intrinsicHeight = dip(8)
                it.setDrawable(shapeDrawable)
            })
            adapter = SingersAdapter()
        }
    }

    private class SingersAdapter : BaseQuickAdapter<Singer, BaseViewHolder>(R.layout.item_singer){

        override fun convert(helper: BaseViewHolder?, item: Singer?) {
            TODO("not implemented")
        }

    }
}
