package com.example.crossinlinerecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crossinlinerecyclerview.fragments.FirstFragment
import com.example.crossinlinerecyclerview.fragments.FourthFragment
import com.example.crossinlinerecyclerview.fragments.SecondFragment
import com.example.crossinlinerecyclerview.fragments.ThirdFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recylerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val list = listOf("1", "2", "3", "4")
        /**
         * Kodu decompile edersek fonksiyonun nesnesinin oluşturulduğunu, inline yapmamıza rağmen görebiliriz.
         * Tam doğru kullanımı bu değil ama örnek olması açısından crossinline'ı böyle yaptık.
         *
         */
        recyclerView.adapter = ListAdapter(list, { textView ->
            Toast.makeText(this@MainActivity, "Tiklandi", Toast.LENGTH_SHORT).show()
            val text = textView.text.toString()
            goFragment(text)
        })
    }

    private fun goFragment(number: String) {
        val fragment = when (number.toInt()) {
            1 -> FirstFragment()
            2 -> SecondFragment()
            3 -> ThirdFragment()
            4 -> FourthFragment()
            else -> null
        }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .addToBackStack(null)
                .commit()
        }
    }
}