import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aa3_app_companion.ChatActivity
import com.example.aa3_app_companion.R
import models.Friend

class FriendAdapter(private val items: List<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.friendImage)
        val name: TextView = view.findViewById(R.id.friendName)
        val button: Button = view.findViewById(R.id.friendButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.conversation_layout_manager, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.friendImage)
        holder.name.text = item.friendName

        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("imageResId", item.friendImage)
            intent.putExtra("name", item.friendName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}