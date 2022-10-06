package com.app.trendinggithubrepositoriesapp.adapter


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.trendinggithubrepositoriesapp.R
import com.app.trendinggithubrepositoriesapp.model.RepositoryModel
import com.app.trendinggithubrepositoriesapp.ui.DetailsActivity
import com.bumptech.glide.Glide

class RepoAdapter(
    private val context: Context,
    private val list: ArrayList<RepositoryModel>
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoAdapter.ViewHolder, position: Int) {
        holder.textViewRepoName.text = "${list[position].repositoryName}/${list[position].username}"
        holder.textViewRepoDesc.text = list[position].description
        holder.textViewLanguage.text = list[position].language
        holder.textViewStarCount.text = list[position].totalStars.toString()
        holder.textViewForkCount.text = list[position].forks.toString()
        if (list[position].languageColor != null){
            holder.imageViewColor.setColorFilter(Color.parseColor(list[position].languageColor))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", list[position].rank.toString())
            context.startActivity(intent )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewRepoName: TextView = itemView.findViewById(R.id.textViewRepoName)
        val textViewRepoDesc: TextView = itemView.findViewById(R.id.textViewRepoDesc)
        val textViewLanguage: TextView = itemView.findViewById(R.id.textViewLanguage)
        val textViewStarCount: TextView = itemView.findViewById(R.id.textViewStarCount)
        val textViewForkCount: TextView = itemView.findViewById(R.id.textViewForkCount)
        val imageViewColor: ImageView = itemView.findViewById(R.id.ImageViewColor)

    }
}