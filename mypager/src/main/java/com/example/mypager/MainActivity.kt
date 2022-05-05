package com.example.mypager

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mypager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    // 뷰 페이저 어댑터
    class MyFragmentPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
        val fragments: List<Fragment>
        init {
            fragments = listOf(FirstFrag(), OneFragment(), TwoFragment())
        }

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

//    val tabTextList = arrayListOf("암냠이", "바보")
//    val tabIconList = arrayListOf(R.drawable.object1, R.drawable.object2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        // ActionBarDrawerToggle 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // 뷰 페이저에 어댑터 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter

            /*
//         RecyclerView.Adapter 간단한.. 이미지나 텍스트?
        binding.viewpager.adapter = ViewPagerAdapter(getItemList()) // 어댑터 생성

        // FragmentStateAdapter, 보통 각 페이지가 복잡하게 작성되기때문에 프래그먼트 사용
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 뷰페이저와 탭 레이아웃 결합
        TabLayoutMediator(binding.mytab, binding.viewpager) { tab, position ->
            tab.text = tabTextList[position]
            tab.setIcon(tabIconList[position])
        }.attach()
             */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        // MenuItem 객체를 얻고 그 안에 포함된 ActionView 객체 획득
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 변경 이벤트
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간 이벤트
                Log.d("kkmmuu", "search Text : $query")
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 이벤트가 토글 버튼에서 발생하면
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

     // RecyclerView.Adapter 간단한.. 이미지나 텍스트?
//    private fun getItemList() : ArrayList<Int>{
//        return arrayListOf<Int>(R.drawable.object1,
//            R.drawable.object2,
//            R.drawable.object3,
//            R.drawable.object4)
//    }
}