package com.example.ch1.section2_viewpager

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ch1.OneFragment
import com.example.ch1.R
import com.example.ch1.TwoFragment
import com.example.ch1.databinding.ActivityTest21Binding
import com.example.ch1.databinding.ViewpagerItemBinding

class Test2_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest21Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //일반 뷰로 항목을 구성하는 경우...
//        binding.main.adapter = ViewPagerAdapter()

        //fragment 로 항목을 구성하는 경우..
        binding.main.adapter = MyFragmentStateAdapter(this)
        binding.main.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}

//일반 뷰로 항목을 구성하는 경우...
//RecyclerView 의 Adapter 그대로 이용..
class PagerViewHolder(val binding: ViewpagerItemBinding): RecyclerView.ViewHolder(binding.root)

class ViewPagerAdapter: RecyclerView.Adapter<PagerViewHolder>(){
    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(ViewpagerItemBinding.inflate(LayoutInflater.from(
            parent.context
        ), parent, false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.binding.run {
            when(position){
                0 -> {
                    textView.text = "One"
                    textView.setBackgroundColor(Color.RED)
                }
                1 -> {
                    textView.text = "Two"
                    textView.setBackgroundColor(Color.BLUE)
                }
                2 -> {
                    textView.text = "Three"
                    textView.setBackgroundColor(Color.GREEN)
                }
            }
        }

    }
}

//ViewPager 의 항목을 Fragment 로 준비하는 경우.........................
class MyFragmentStateAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
    val fragments: List<Fragment>
    init {
        fragments = listOf(OneFragment(), TwoFragment())
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    //각 항목의 fragment 를 결정하기 위해서 자동 호출..
    //position 에 해당되는 fragment 만 리턴시키면 알아서 제어..
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}