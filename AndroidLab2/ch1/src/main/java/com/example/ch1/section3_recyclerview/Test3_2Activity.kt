package com.example.ch1.section3_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest32Binding
import com.example.ch1.databinding.RecyclerItemUpdateDeleteBinding

class Test3_2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest32Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = mutableListOf<String>()
        for(i in 1..10){
            data.add("${i * 100}")
        }
        binding.main.adapter = MyAdapter2(data)
        binding.main.layoutManager = LinearLayoutManager(this)
        binding.main.addItemDecoration(DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL))
    }
}

class MyViewHolder2(val binding: RecyclerItemUpdateDeleteBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter2(val data: MutableList<String>): RecyclerView.Adapter<MyViewHolder2>(){
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        return MyViewHolder2(RecyclerItemUpdateDeleteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val binding = holder.binding
        binding.itemData.text = data[position]

        //test1......notifyXXXX() 함수 이용해서.. 변경 사항 적용.....
        binding.updateBtn.setOnClickListener {
            //데이터 변경..
            var newData = data[position].toInt()
            newData++
            data[position] = newData.toString()
            //변경사항 반영... 특정 항목의 위치를 지정해서.. 그 위치의 항목만 다시 구성되게..
            notifyItemChanged(position)
        }

        binding.deleteBtn.setOnClickListener {
            //데이터 삭제..
            data.removeAt(position)
            //삭제 알림...
            notifyItemRemoved(position)
            //위 코드로 해당 항목이 삭제가 되지만.. 그 아래에 있는 항목의 index 값이 변경 되었음을 알려주지 않으면
            //아래 항목 update, delete 시에 다른 항목이 update, delete 될 수 있거나..
            //out of bound exception 가능성이 있다..
            //아래 항목의 index 위치가 변경되었음을 다음의 코드로 알려줘야 한다..
            notifyItemRangeChanged(position, data.size-position)
        }
    }
}