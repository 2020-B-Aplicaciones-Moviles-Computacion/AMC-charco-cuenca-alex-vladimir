
package com.example.proyecto_2bim.AdaptadoresRecycleViews

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.proyecto_2bim.R
import com.example.proyecto_2bim.Search
import org.w3c.dom.Text

class AdapterMangaSearch(
    private val listaMangas :List<String>,
    private val contexto: Search,
    private val recycleView:androidx.recyclerview.widget.RecyclerView
):androidx.recyclerview.widget.
RecyclerView.Adapter<AdapterMangaSearch.MyViewHolder>()
{
    inner class MyViewHolder(view: View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
            val imgManga:ImageView
            val titleManga:TextView
            val authorManga:TextView
            val statusManga:TextView
            val genreManga:TextView
            val descriptionManga:TextView
            val containerManga:ConstraintLayout

        init{
            imgManga=view.findViewById(R.id.iv_comic)
            titleManga=view.findViewById(R.id.tv_mangaTitle)
            authorManga=view.findViewById(R.id.tv_mangaAutor)
            statusManga=view.findViewById(R.id.tv_mangaStatus)
            genreManga=view.findViewById(R.id.tv_mangaGenres)
            descriptionManga=view.findViewById(R.id.tv_mangaDescription)
            containerManga=view.findViewById(R.id.rv_search_manga_result)

            containerManga.setOnClickListener{
                Toast.makeText(contexto, "Entering Manga", Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onCreateViewHolder(
        //Aqui definiremos que interfaz vamos a utilizar, dependiendeo de esto la pasaremos
        parent: ViewGroup,
        viewType: Int)
            : AdapterMangaSearch.MyViewHolder
    {
        val itemView=LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.comic_search_results,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaMangas.size

    }

    override fun onBindViewHolder(
        holder: AdapterMangaSearch.MyViewHolder,
        position: Int
    ) {
        //Cuando se cargue la interfaz, podremos empezar a llenar los datos
        Toast.makeText(contexto, "Mangas Loaded", Toast.LENGTH_SHORT).show()

    }

}