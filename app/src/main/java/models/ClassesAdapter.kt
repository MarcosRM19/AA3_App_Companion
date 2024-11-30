package models

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.aa3_app_companion.R
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlin.concurrent.thread

class ClassesAdapter(private val classes: List<Classes>, private val listener: OnButtonClickListener): RecyclerView.Adapter<ClassesAdapter.ClassesViewHolder>() {
    interface OnButtonClickListener {

        fun onButtonClick(classes: Classes)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassesAdapter.ClassesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.classes_layout_manager, parent, false)

        return ClassesViewHolder(view);
    }

    override fun onBindViewHolder(holder: ClassesAdapter.ClassesViewHolder, position: Int) {
        val classe = classes[position]
        holder.bind(classe)

        holder.classesButton.setOnClickListener {
            listener.onButtonClick(classe)
        }
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    class ClassesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val className: TextView = itemView.findViewById(R.id.title)
        private val classDescription: TextView = itemView.findViewById(R.id.information)
        private val classImage: ImageView = itemView.findViewById(R.id.classImage)
        val classesButton: Button = itemView.findViewById(R.id.classButton)

        fun bind(classes: Classes) {
            className.text = classes.name
            classDescription.text = classes.description
            loadImageFromUrl(classes.image, classImage)
        }
        private fun loadImageFromUrl(imageUrl: String, imageView: ImageView) {
            Thread {
                try {
                    val url = URL(imageUrl)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connect()

                    val inputStream: InputStream = connection.inputStream
                    val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)

                    Handler(Looper.getMainLooper()).post {
                        imageView.setImageBitmap(bitmap)
                    }

                    connection.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.start()
        }
    }
}



