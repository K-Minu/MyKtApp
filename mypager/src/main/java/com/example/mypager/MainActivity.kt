package com.example.mypager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mypager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val tabTextList = arrayListOf("암냠이", "바보")
    val tabIconList = arrayListOf(R.drawable.object1, R.drawable.object2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView.Adapter 간단한.. 이미지나 텍스트?
//        binding.viewpager.adapter = ViewPagerAdapter(getItemList()) // 어댑터 생성

        // FragmentStateAdapter, 보통 각 페이지가 복잡하게 작성되기때문에 프래그먼트 사용
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 뷰페이저와 탭 레이아웃 결합
        TabLayoutMediator(binding.mytab, binding.viewpager) { tab, position ->
            tab.text = tabTextList[position]
//            tab.setIcon(tabIconList[position])
        }.attach()

    }

     // RecyclerView.Adapter 간단한.. 이미지나 텍스트?
//    private fun getItemList() : ArrayList<Int>{
//        return arrayListOf<Int>(R.drawable.object1,
//            R.drawable.object2,
//            R.drawable.object3,
//            R.drawable.object4)
//    }
}