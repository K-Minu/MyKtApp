package com.example.ch10_notification

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch10_notification.databinding.FragmentFirstBinding
import com.example.ch10_notification.databinding.ItemMainBinding

// 뷰 홀더는 각 항목을 구성하는 뷰 객체를 가짐
// 어댑터는 뷰 홀더에 있는 뷰 객체에 적절한 데이터를 삽입해 항목을 완성
// 레이아웃매니저는 어댑터가 만든 항목들을 어떻게 배치할지 경정해 리사이클러 뷰에 출력

// 뷰 홀더
class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int = datas.size
    // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 뷰 홀더의 뷰에 데이터를 출력하려고 자동으로 호출
        Log.d("kkmmuu", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding
        // 뷰에 데이터 출력
        binding.itemData.text = datas[position]
        // 뷰에 이벤트 추가
        when (position % 3){
            0 -> binding.itemData.setBackgroundColor(Color.RED)
            1 -> binding.itemData.setBackgroundColor(Color.GREEN)
            2 -> binding.itemData.setBackgroundColor(Color.BLUE)
        }
//        binding.itemRoot.setOnClickListener {
//            Log.d("kkmmuu", "item root click : $position")
//        }
    }
}

class FirstFrag : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        val datas = mutableListOf<String>()
        for(i in 1..10){
            datas.add("Item $i")
        }

        // 리사이클러 뷰에 LayoutManager, Adapter, ItemDecoration 적용
        val layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView.layoutManager=layoutManager
        val adapter= MyAdapter(datas)
        binding
//        binding.recyclerView.adapter=adapter
//        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))


        return binding.root
    }
}