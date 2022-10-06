package com.app.trendinggithubrepositoriesapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.trendinggithubrepositoriesapp.MyApplication
import com.app.trendinggithubrepositoriesapp.R
import com.app.trendinggithubrepositoriesapp.viewmodel.MainViewModelFactory
import com.app.trendinggithubrepositoriesapp.viewmodel.RepositoryViewModel
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {
    lateinit var viewModel: RepositoryViewModel
    private lateinit var textViewRepoName: TextView
    private lateinit var textViewRepoDesc: TextView
    private lateinit var textViewLanguage: TextView
    private lateinit var textViewStarCount: TextView
    private lateinit var textViewForkCount: TextView

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        (application as MyApplication).applicationComponent.inject(this)

        findIds()


        val repoId = intent.getStringExtra("id").toString()

        viewModel = ViewModelProvider(this, viewModelFactory)[RepositoryViewModel::class.java]

        viewModel.getRepoDataById(repoId)?.observe(this, Observer {
            if(it!=null){
                textViewRepoName.text = "${it.repositoryName}/${it.username}"
                textViewRepoDesc.text = it.description
                textViewLanguage.text = it.language
                textViewStarCount.text = it.totalStars.toString()
                textViewForkCount.text = it.forks.toString()
            }
        })
    }

    private fun findIds() {
        textViewRepoName = findViewById(R.id.textViewRepoName)
        textViewRepoDesc = findViewById(R.id.textViewRepoDescription)
        textViewLanguage = findViewById(R.id.textViewRepoLanguage)
        textViewStarCount = findViewById(R.id.textViewRepoStarCount)
        textViewForkCount = findViewById(R.id.textViewRepoForkCount)
    }
}