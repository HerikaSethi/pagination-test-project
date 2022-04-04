package com.example.paginationtestproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationtestproject.api.ApiService
import com.example.paginationtestproject.model.PicResult
import com.example.paginationtestproject.model.PicResultItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: PicSumAdapter
    lateinit var progressBar: ProgressBar
    var page = 1
    var limit =10

    private var picsumdata = mutableListOf<PicResultItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.Rcv)
        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)


        adapter = PicSumAdapter(this,picsumdata as ArrayList<PicResultItem>)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        getdata(page, limit)

        //nestedScrollView.setOnScrollChangeListener()
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                //when we have reaced the last position then
                //increase the page size
                page++
                //and show the progress bar till loading takes plce
                progressBar.setVisibility(View.VISIBLE)
                //then again calling the method
                getdata(page, limit)
            }
        })

    }

    private fun getdata(page: Int, limit: Int) {
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val picResult = ApiService.picInstance.getPic(page,limit)
        picResult.enqueue(object :Callback<PicResult>{
            override fun onResponse(call: Call<PicResult>, response: Response<PicResult>) {
                val picResult = response.body()
                if(picResult!=null){

                    //if i m getting the response and body of response is not null
                    progressBar.visibility = View.GONE
                    picsumdata.addAll(picResult)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<PicResult>, t: Throwable) {
                Log.d("check", "onFailure: Unable to fetch data ",t)
            }
        })
    }
}