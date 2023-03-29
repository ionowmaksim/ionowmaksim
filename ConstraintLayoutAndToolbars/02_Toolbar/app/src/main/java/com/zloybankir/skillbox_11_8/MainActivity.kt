package com.zloybankir.skillbox_11_8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.zloybankir.skillbox_11_8.databinding.ActivityMainBinding
import android.view.MenuItem as MenuItem

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val books = listOf(
        "«Гибель Богов»",
        "«Воин Великой Тьмы»",
        "«Земля без радости»",
        "«Алмазный меч. Деревянный меч»",
        "«Рождение мага»"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        setLongString()
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun setLongString() {
        binding.searchResultTextView.text =
            "long string start\n".padEnd(2000,'0') + "\nlong string end"
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            showToast("Navigation button clicked")
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_1 -> {
                    showToast("Action 1 clicked")
                    true
                }
                R.id.action_2 -> {
                    showToast("Action 2 clicked")
                    true
                }
                R.id.action_3 -> {
                    showToast("Action 3 clicked")
                    true
                }
                else -> false
            }
        }

        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                showToast("Search open")
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                setLongString()
                showToast("Search closed")
                return true
            }

        })

        (searchItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                books.filter { it.contains(newText ?: "", true) }
                    .joinToString("\n")
                    .let { binding.searchResultTextView.text = it }
                return true
            }

        })
    }
}