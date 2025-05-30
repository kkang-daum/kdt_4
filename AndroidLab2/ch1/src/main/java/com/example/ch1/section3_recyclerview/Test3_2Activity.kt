package com.example.ch1.section3_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DiffUtil
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
//        binding.updateBtn.setOnClickListener {
//            //데이터 변경..
//            var newData = data[position].toInt()
//            newData++
//            data[position] = newData.toString()
//            //변경사항 반영... 특정 항목의 위치를 지정해서.. 그 위치의 항목만 다시 구성되게..
//            notifyItemChanged(position)
//        }
//
//        binding.deleteBtn.setOnClickListener {
//            //데이터 삭제..
//            data.removeAt(position)
//            //삭제 알림...
//            notifyItemRemoved(position)
//            //위 코드로 해당 항목이 삭제가 되지만.. 그 아래에 있는 항목의 index 값이 변경 되었음을 알려주지 않으면
//            //아래 항목 update, delete 시에 다른 항목이 update, delete 될 수 있거나..
//            //out of bound exception 가능성이 있다..
//            //아래 항목의 index 위치가 변경되었음을 다음의 코드로 알려줘야 한다..
//            notifyItemRangeChanged(position, data.size-position)
//        }

        //DiffUtil 을 이용해 변경, 삭제를 구현한 경우........................
        binding.updateBtn.setOnClickListener {
            if(position in 0 until data.size){
                val currentValue = data[position].toInt()
                val newValue = (currentValue + 1).toString()

                //복재본 생성...
                val newList = data.toMutableList()//기존 데이터랑 동일한 데이터를 가지는 복재본..
                newList[position] = newValue

                setData(newList)
            }
        }

        binding.deleteBtn.setOnClickListener {
            if(position in 0 until data.size){
                val newList = data.toMutableList()//복재본..
                newList.removeAt(position)

                setData(newList)
            }
        }

    }

    fun setData(newNumbers: MutableList<String>){
        val diffCallback = MyDiffCallback(data, newNumbers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newNumbers)
        diffResult.dispatchUpdatesTo(this)//계산된대로 항목을 업데이트 해라..
    }
}

//어떤 항목들이 변경, 삭제된 것인지 자체적으로 판단해서.. 해당 항목들을 업데이트 해주는데...
//변경된 항목의 index 를 우리가 계산하지 않아서 좋다.. 동시다발로.. 불규칙하게 여러 항목이 변경되는 경우 용이..
//어떤 상황이 항목이 변경된 것으로 볼것인지에 대한 판단을 위한 클래스는 우리가 만들어서 알려줘야 한다..
class MyDiffCallback(
    private val oldList: MutableList<String>,//<String> 항목의 데이터.. VO 클래스일 수도 있고..
    private val newList: MutableList<String>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //두 아이텀이 동일 아이템인지를 판단하는 로직..
        //일반적으로.. VO 클래스로 항목이 구성된다면.. 객체가 동일한지에 대한 비교로..
        //return oldList[oldItemPosition] == newList[newItemPosition]
        //단순 문자열로 처리해서.. 위치 확인으로 같은지를 비교..
        return oldItemPosition == newItemPosition &&
                oldItemPosition < oldList.size &&
                newItemPosition < newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //항목에 찍히는 데이터의 비교..
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}