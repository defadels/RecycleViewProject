package com.example.recycleviewproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var dataName : TextView
    private lateinit var dataDescription : TextView
    private lateinit var dataDeveloper : TextView
    private lateinit var dataPhoto : ImageView

    companion object {
        const val DATA_NAME = "company_name"
        const val DESCRIPTION = "description"
        const val DEVELOPER = "developer"
        const val IMAGE_FILE = "image"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_share -> {
                shareAlbum()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dataPhoto = findViewById(R.id.imageView)
        dataName = findViewById(R.id.company_name)
        dataDescription = findViewById(R.id.description)
        dataDeveloper = findViewById(R.id.developer)
        val dataNameText = intent.getStringExtra(DATA_NAME)
        val dataDeveloperText = intent.getStringExtra(DEVELOPER)
        val descriptionText = intent.getStringExtra(DESCRIPTION)
        val imageResource = intent.getIntExtra(IMAGE_FILE, 0)

        dataName.text = dataNameText
        dataDeveloper.text = dataDeveloperText
        dataDescription.text = descriptionText
        dataPhoto.setImageResource(imageResource)
        val btnShare:Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)

    }
    private fun shareAlbum(){
        val dataName = dataName.text.toString()
        val developer = dataDeveloper.text.toString()
        val desc = dataDescription.text.toString()

        val shareScript = "Name Company : $dataName\n" +
                "Developer : $developer\n" +
                "Description : $desc\n"
        // Buat intent untuk mengirim pesan ke WhatsApp
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareScript)
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }
}