package controlador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doglist.R
import modelo.Breed

interface OnBreedClickListener {
    fun onBreedClick(breed: Breed)
}


class BreedAdapter(private val clickListener: OnBreedClickListener) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    private var breeds: List<Breed> = emptyList()

    fun setBreeds(breeds: List<Breed>) {
        this.breeds = breeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    class BreedViewHolder(itemView: View, private val clickListener: OnBreedClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(breed: Breed) {
            itemView.findViewById<TextView>(R.id.textViewBreedName).text = breed.name

            // Puedes agregar un clic en el elemento aquí para manejar la selección de la raza.
            itemView.setOnClickListener {
                clickListener.onBreedClick(breed)
            }
        }
    }
}
