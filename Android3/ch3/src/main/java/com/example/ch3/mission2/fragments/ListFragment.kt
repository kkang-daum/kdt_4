package com.example.ch3.mission2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch3.R
import com.example.ch3.databinding.FragmentListBinding
import com.example.ch3.mission1.model.Item
import com.example.ch3.mission1.recyclerview.ItemAdapter
import com.example.ch3.mission1.viewmodel.NewsViewModel

class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var searchView: SearchView
    val viewModel: NewsViewModel by viewModels()
    val datas = mutableListOf<Item>()
    lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        //actionbar 내용.. toolbar 에...
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        itemAdapter = ItemAdapter(activity as Context, datas)
        binding.recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = itemAdapter
            getData("travel")
        }

        return binding.root
    }
    private fun getData(query: String){
        //B/L 이 실행되어야 한다.. viewmodel 에 일만 시키자..
        viewModel.getNews(query).observe(viewLifecycleOwner){
            //데이터 전달 된 상황...
            datas.clear()
            datas.addAll(it)
            itemAdapter.notifyDataSetChanged()
        }
    }

    //onCreateView() 가 호출이 되어서.. fragment 의 화면이 결정이 된 후.. 자동 호출...
    //매개변수 (view) 가 onCreateView 에러 리턴시킨 객체..
    //view 에 대한 디테일 작업을 보통 이 함수에서 구현..
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //activity 에서 작성했던 메뉴 구성을 fragment 에서 한다..
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            //메뉴 구성..
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_menu, menu)
                searchView = menu.findItem(R.id.menu_search).actionView as SearchView
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        getData(query ?: "travel")
                        return false
                    }
                })
            }
            //메뉴 이벤트..
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        //메뉴 구성이 fragment 에서 한거지만.. activity 내용이다..
        //fragment 에서 구성한 메뉴가 언제 나와야 하는지에 대한 정보를 제어할 수 있다..
        //viewLifecycleOwner - Fragment 가 View 를 가지고 있는 경우에만 메뉴가 활성화 되라..(화면이 출력되는 동안)
        //Lifecycle.State.RESUMED - 언제.. RESUME 상황에서 뿌리면 된다..
    }
}