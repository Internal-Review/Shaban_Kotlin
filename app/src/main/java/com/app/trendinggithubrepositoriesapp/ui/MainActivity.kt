package com.app.trendinggithubrepositoriesapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.app.trendinggithubrepositoriesapp.MyApplication
import com.app.trendinggithubrepositoriesapp.R
import com.app.trendinggithubrepositoriesapp.adapter.RepoAdapter
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel
import com.app.trendinggithubrepositoriesapp.service.MyWork
import com.app.trendinggithubrepositoriesapp.service.RunServiceOnBoot
import com.app.trendinggithubrepositoriesapp.utils.Constants
import com.app.trendinggithubrepositoriesapp.viewmodel.MainViewModelFactory
import com.app.trendinggithubrepositoriesapp.viewmodel.RepositoryViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoryViewModel
    lateinit var repoAdapter: RepoAdapter
    private val list = ArrayList<RepositoryModel>()



    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val recyclerView: RecyclerView
        get() = findViewById(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).applicationComponent.inject(this)
       val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        viewModel = ViewModelProvider(this, viewModelFactory)[RepositoryViewModel::class.java]

        val request = PeriodicWorkRequestBuilder<MyWork>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(request)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer {
                if (!Constants.isOnline(this)) {
                    Toast.makeText(this,"You are offline", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.apiCall()
                }
            })

        repoAdapter = RepoAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter


        progressBar.visibility = View.VISIBLE
        viewModel.repositoryLiveData.observe(this, Observer { it->
            it?.let {
                Log.d("Data", ""+it)
                list.addAll(it)
                repoAdapter.notifyDataSetChanged()
                progressBar.visibility = View.INVISIBLE

            }
        })


        // start service on boot
        val intent1 = Intent(this@MainActivity, RunServiceOnBoot::class.java)
        startService(intent1)
    }
}