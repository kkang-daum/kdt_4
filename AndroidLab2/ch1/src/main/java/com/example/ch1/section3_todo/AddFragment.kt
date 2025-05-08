package com.example.ch1.section3_todo

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch1.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AddFragment : Fragment(){

    lateinit var binding: FragmentAddBinding

    //fragment 화면을 출력하기 위해서 자동 호출.. 이 함수에서 리턴 시킨 뷰가 fragment 에 찍힌다..
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(LayoutInflater.from(activity), container, false)
        return binding.root
    }
    //onCreateView 가 호출된 후에 자동 호출..
    //onCreateView 에서 리턴 시킨 뷰로 화면 출력하고.. 이 함수의 매개변수로 전달...
    //화면에 출력되고 있는 뷰 이용.. 데이터 출력하거나, 이벤트 등록하거나..
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //기본으로 현재 날짜를 화면에 출력...
        binding.addDateView.text = SimpleDateFormat("yyyy-MM-dd").format(Date())

        //유저가 날짜를 클릭했을때.. DatePickerDialog 로 날짜 입력받는다..
        binding.addDateView.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(activity as Context, { view, year, monthOfYear, dayOfMonth ->
                //유저가 날짜를 선택한 후 이벤트...
                binding.addDateView.text = "$year-${monthOfYear + 1}-$dayOfMonth"
            }, year, month, day).show()
        }

        binding.addBtn.setOnClickListener {
            if(!binding.addTitleEditView.text?.toString().equals("") &&
                !binding.addContentEditView.text?.toString().equals("")){
                //db insert 데이터 구성...
                val contentValues = ContentValues().apply {
                   put("title", binding.addTitleEditView.text.toString())
                   put("content", binding.addContentEditView.text.toString())
                   put("date", SimpleDateFormat("yyyy-MM-dd")
                       .parse(binding.addDateView.text.toString()).time)
                    put("completed", 0)
                }
                //db 저장...
                insertTodo(activity as Context, contentValues)
                //화면을 이전 fragment 로 전환..(ListFragment)
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }
}