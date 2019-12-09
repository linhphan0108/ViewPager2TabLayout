package com.example.viewpager2tablayout

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager(){
        viewPager.adapter = Adapter()
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "TAB ONE"}
                    1 -> { tab.text = "TAB TWO"}
                }
            }).attach()
    }

    private fun setupTabLayout(){
        val tabs = tabLayout.getChildAt(0) as ViewGroup
        val margin = (getScreenSizeWidth() * 0.5).toInt()


        val fistTab = tabs.getChildAt(0)
        val layoutParams = fistTab.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.marginStart = margin
        }else{
            layoutParams.leftMargin = margin
        }
//            layoutParams.width = 10.dpToPx()


        val lastTab = tabs.getChildAt(tabs.childCount -1)
        val lastLayoutParams = lastTab.layoutParams as LinearLayout.LayoutParams
        lastLayoutParams.weight = 0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            lastLayoutParams.marginEnd = margin
        }else{
            lastLayoutParams.rightMargin= margin
        }
//            layoutParams.width = 10.dpToPx()
        tabLayout.requestLayout()

    }

    private fun getScreenSizeWidth(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        return displayMetrics.widthPixels
    }

    class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 2
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val color = when(position){
                0 -> Color.BLUE
                1 -> Color.GREEN
                else -> Color.BLACK
            }
            val title = "tab $position"
            holder.bindView(title, color)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(title: String, color: Int){
            itemView.setBackgroundColor(color)
            itemView.txt.text = title
        }
    }
}
