package com.example.ch1.section3_recyclerview

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ch1.R
import com.example.ch1.databinding.ActivityTest31Binding
import com.example.ch1.databinding.RecyclerItemBinding

class Test3_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityTest31Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = mutableListOf<String>()
        for(i in 1..10){
            data.add("Item $i")
        }

        binding.main.adapter = MyAdapter(data)

        //linear..............세로방향 나열..........
//        binding.main.layoutManager = LinearLayoutManager(this)

        //linear, 가로방향.....
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //gridlayout
//        val layoutManager = GridLayoutManager(this, 2)

        //gridlayout, vertical, true - 아래부터 항목 나열..
//        val layoutManager = GridLayoutManager(this, 2,
//            GridLayoutManager.VERTICAL, true)

        //gridlayout, horizontal, false
//        val layoutManager = GridLayoutManager(this, 2,
//            GridLayoutManager.HORIZONTAL, false)

        //staggered.....................
//        data.add("aaaaaaaaaa aaaaaaaaaaaaa, aaaa, aaaaaaaaaaaaaa, aaaaaaaaaaaa, aaaaaaaaaaaaaa, aaaaaaaaaaaa, aaaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaa")
//        data.add("bbbbbbbbbb bbbbbbbbbbb")
//        data.add("ccccccccc")
//        data.add("dddddddddddddd ddddddddddddddd dddddddddddddddd ddddddddddddddddd ddddddddddddddddd ddddddddddddddd ddddddddddddddddd ddddddddddddddddddd")
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val layoutManager = LinearLayoutManager(this)
        binding.main.layoutManager = layoutManager
        binding.main.addItemDecoration(MyDecoration(this))



    }
}

class MyViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val data: MutableList<String>): RecyclerView.Adapter<MyViewHolder>(){
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.itemData.text = data[position]
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    //항목이 찍히기 전에 최초에 한번.. 바탕 드로잉이 필요할 때...
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.stadium),
            0f, 0f, null)
    }

    //항목이 모두 찍힌 후 최후에 한번.. 항목 위에 추가 드로잉이 필요한 경우..
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //이미지를 recyclerview 의 center 위치에 그리고 싶다.. 좌표계산..
        //뷰의 크기 계산..
        val width = parent.width
        val height = parent.height

        //드로잉 하고자 하는 이미지 사이즈..
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources,
            R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight

        //이미지가 center에 그려지게.. 이미지가 그려질 left, top 좌표값 계산..
        val left = width/2 - drWidth?.div(2) as Int
        val top = height/2 - drHeight?.div(2) as Int

        //이미지 그리기...
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
            left.toFloat(), top.toFloat(), null)
    }
    //항목 하나당 한번씩 호출.. 항목에 추가 꾸미기 작업이 필요할 때...
    //outRect : 항목이 출력되는 사각형 정보..
    //view : 항목에 출력되는 뷰 객체..
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //매개변수로 몇번째 항목을 꾸미기 위해서 호출된 것인지, 전달되지 않는다.. 필요하다면 획득 가능..
        //1 을 더하는 것은 알고리즘의 편의성 때문..
        val index = parent.getChildAdapterPosition(view) + 1
        if(index % 3 == 0)
            outRect.set(10, 10, 10, 60)
        else
            outRect.set(10, 10, 10, 0)

        view.setBackgroundColor(Color.LTGRAY)
        ViewCompat.setElevation(view, 20.0f)
    }
}















