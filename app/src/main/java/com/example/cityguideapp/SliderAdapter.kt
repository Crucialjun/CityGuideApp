package com.example.cityguideapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter

class SliderAdapter(private val context: Context) : PagerAdapter() {

    val images  = arrayOf(
        R.drawable.search_place,
        R.drawable.make_a_call,
        R.drawable.add_missing_place,
        R.drawable.sit_back_and_relax)



    val headings = arrayOf(
        R.string.first_slide_title,
        R.string.second_slide_title,
        R.string.third_slide_title,
        R.string.fourth_slide_title
    )

    val descriptions = arrayOf(
        R.string.first_slide_desc,
        R.string.second_slide_desc,
        R.string.third_slide_desc,
        R.string.fourth_slide_desc
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.slides_layout,container,false)

        val imageView = layoutInflater.findViewById<ImageView>(R.id.img_slider)
        val textTitle = layoutInflater.findViewById<TextView>(R.id.txt_slider_heading)
        val txtDescription = layoutInflater.findViewById<TextView>(R.id.txt_slider_description)

        imageView.setImageResource(images[position])
        textTitle.setText(headings[position])
        txtDescription.setText(descriptions[position])

        container.addView(layoutInflater)

        return layoutInflater
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}