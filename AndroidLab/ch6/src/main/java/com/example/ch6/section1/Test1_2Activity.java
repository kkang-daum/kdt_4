package com.example.ch6.section1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ch6.R;
import com.example.ch6.databinding.ActivityTest12Binding;
import com.example.ch6.databinding.ItemRecyclerBinding;

import java.util.ArrayList;

public class Test1_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityTest12Binding binding = ActivityTest12Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> list = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            list.add("Item "+ i);
        }

        binding.main.setAdapter(new MyAdapter(list));
        //adapter 가 만들어놓은 항목을 배치하는 역할자.. 세로방행..
        binding.main.setLayoutManager(new LinearLayoutManager(this));
        //원래 항목 꾸미는 Decoration 은 개발자가 클래스로 만들어 적용하는 것인데..
        //단순 항목 사이의 구분선 정도는 제공한다..
        binding.main.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}


//항목을 구성하기 위해 필요한 뷰를 가지는 역할자...
class MyViewHolder extends RecyclerView.ViewHolder {
    ItemRecyclerBinding binding;
    MyViewHolder(ItemRecyclerBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}

//ViewHolder 의 뷰 객체를 이용해서.. 각 항목을 구성(데이터 출력, 이벤트 등록)하는 역할자...
class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<String> datas;//activity 로부터 받을 항목 구성 집합 데이터..

    MyAdapter(ArrayList<String> data){
        this.datas = data;
    }
    //항목 갯수를 판단하기 위해서 자동 호출..
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //항목을 구성하기 위해 뷰를 가지는 뷰홀더를 결정하기 위해서.. 자동 호출..
    //이 함수에서 사용하고자 하는 뷰홀더를 생성해서 리턴시키면 된다..

    //뷰 홀더를 결정하기 위해서 이 함수가 자동 호출... 항목 갯수가 100개라면 100번 호출?
    //그러면 뷰홀더 객체가  100개 생성?
    //==>그렇지 않다.. 한 화면에 같이 나올만큼만 호출하고.. 나머지는 재사용 한다.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerBinding binding
                = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false);
        return new MyViewHolder(binding);
    }
    //첫번째 매개변수.. onCreateViewHolder() 에서 리턴시킨 뷰 홀더를 전달해서.. 이 뷰홀더에 작업하라..
    //onCreateViewHolder() 에 의해 새로 생성된 객체일 수도 있고.. 이전에 다른 항목에서 사용된 객체일수도 있고.
    //두번째 매개변수.. 항목 index..
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.itemData.setText(datas.get(position));
    }
}