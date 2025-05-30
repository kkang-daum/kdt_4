package com.example.ch3.mission2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch3.R
import com.example.ch3.databinding.FragmentListBinding
import com.example.ch3.mission2.model.Item
import com.example.ch3.mission2.recyclerview.ItemAdapter
import com.example.ch3.mission2.room.Search
import com.example.ch3.mission2.viewmodel.NewsViewModel
import com.example.ch3.mission2.viewmodel.SearchViewModel
import kotlinx.coroutines.launch


class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var searchView: SearchView
    val viewModel: NewsViewModel by viewModels()
    val datas = mutableListOf<Item>()
    lateinit var itemAdapter: ItemAdapter

    val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        //actionbar 내용.. toolbar 에...
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)



//        itemAdapter = ItemAdapter(activity as Context, datas)
        val navController = findNavController()
        itemAdapter = ItemAdapter(activity as Context, datas) { url ->
            val direction = ListFragmentDirections.actionListFragmentToWebFragment(url)
            navController.navigate(direction)
        }


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
                        //newsapi 에서 network 으로 데이터 획득해서 화면에 뿌리고..
                        getData(query ?: "travel")

                        //검색어를 저장하고 싶다..
                        query?.run {
                            searchViewModel.insertSearch(Search(0, query))
                        }

                        return false
                    }
                })

                val searchTxtList = mutableListOf<String>()

                //fragment 를 위한 코루틴 스코프는 두개 제공한다..
//                lifecycleScope - fragment 가 종료 될때까지..

                //fragment 가 살아 있다고 하더라도.. fragment 가 출력한 뷰가 제거 될때..까지...
                viewLifecycleOwner.lifecycleScope.launch {
                    //flow 데이터 발행 구독.. UI 에 방해되지 않게.. 코루틴으로 구독..
                    searchViewModel.searchFlow.collect {
                        it.forEach {
                            searchTxtList.add(it.searchTxt)
                        }
                        //과거 ListView 에서 많이 사용했던 adapter 인데.. ListView 는 더이상 안쓰이지만
                        //항목에 문자열 하나 출력하는 용도로 ArrayAdapter 는 여전히 많이 사용..
                        val suggestionsAdapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,//항목 layout xml
                            searchTxtList
                        )

                        val searchAutoComplete = searchView.findViewById<AutoCompleteTextView>(
                                androidx.appcompat.R.id.search_src_text
                        )
                        searchAutoComplete.threshold = 2//2글자 입력부터 추천단어 띄우자..
                        searchAutoComplete.setAdapter(suggestionsAdapter)

                        //추천단어를 하나 선택했을 때..
                        searchAutoComplete.setOnItemClickListener { parent, _, position, _ ->
                            val selectedItem = parent.getItemAtPosition(position) as String
                            searchAutoComplete.setText(selectedItem)
                            searchView.setQuery(selectedItem, true)
                        }
                    }
                }
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