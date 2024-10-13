package com.example.recycleviewproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCompany: RecyclerView
    private val list = ArrayList<Company>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCompany = findViewById(R.id.rcv_indpark)
        rvCompany.setHasFixedSize(true)

        list.addAll(getListCompany())
        showRecyclerList()
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

    private fun getListCompany(): ArrayList<Company> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataDeveloper = resources.getStringArray(R.array.data_developer)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCompany = ArrayList<Company>()
        for (i in dataName.indices) {
            val company = Company(dataName[i], dataDescription[i], dataDeveloper[i], dataPhoto.getResourceId(i, -1))
            listCompany.add(company)
        }
        return listCompany
    }

    private fun showRecyclerList() {
        rvCompany.layoutManager = LinearLayoutManager(this)
        val listCompanyAdapter = ListCompanyAdapter(list)
        rvCompany.adapter = listCompanyAdapter

        listCompanyAdapter.setOnItemClickCallback(object: ListCompanyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Company) {
                showSelectedCompany(data)
            }
        })
    }

    private fun showSelectedCompany(company : Company) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.DATA_NAME, company.name)
            putExtra(DetailActivity.DEVELOPER, company.developer)
            putExtra(DetailActivity.DESCRIPTION, company.description)
            putExtra(DetailActivity.IMAGE_FILE, company.photo)
        }
        startActivity(intent)
    }
}