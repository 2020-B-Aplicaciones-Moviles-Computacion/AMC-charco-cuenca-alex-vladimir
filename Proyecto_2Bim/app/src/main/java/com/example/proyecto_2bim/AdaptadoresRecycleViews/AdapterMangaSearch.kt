
package com.example.proyecto_2bim.AdaptadoresRecycleViews


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.example.proyecto_2bim.ComicDetail
import com.example.proyecto_2bim.R
import com.example.proyecto_2bim.Search


class AdapterMangaSearch(
    private val listaMangas :List<String>,
    private val contexto: Search,
    private val recycleView:androidx.recyclerview.widget.RecyclerView
):androidx.recyclerview.widget.
RecyclerView.Adapter<AdapterMangaSearch.MyViewHolder>()
{
    //Code Manga
    val MANGA_CODE:Int=101

    inner class MyViewHolder(view: View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
            val imgManga: ImageView
            val titleManga: TextView
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
                Toast.makeText(contexto, "Entering Manga: ${titleManga.text}", Toast.LENGTH_SHORT).show()
               // irActividad(ComicDetail)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
            : AdapterMangaSearch.MyViewHolder
    {
        val itemView= LayoutInflater
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
        val content=listaMangas[position]
        holder.titleManga.setText(content)

    }

    //Activity Change



    //IR ACTIVIDAD BASE
    /*
    fun irActividad(
            clase:Class<*>,
            param: ArrayList<Pair<String, *>>?=null,
            codigo:Int? =null
    ){
        val intentEx= Intent(
                this,
                clase
        )

        if(param!=null){
            param.forEach {
                var nombreVar = it.first
                var valorVar=it.second

                var tipoDato=false

                tipoDato=it.second is String

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as String)
                }
                tipoDato=it.second is Int

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Int)
                }
                /*
                tipoDato=it.second is Parcelable

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Parcelable)
                }
*/
            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }
     */

}