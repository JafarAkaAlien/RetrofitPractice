package com.leblebi.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.leblebi.retrofitpractice.databinding.ActivityMainBinding
import com.leblebi.retrofitpractice.databinding.ItemTodoBinding
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter:TodoAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        println("salam")
        setupRecyclerView()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                binding.progressBar.isVisible=true
                val response = try{
                    RetrofitObject.api.getTodos()
                }
                catch (e:IOException){
                    Log.e("MyTag" , e.toString())
                    return@repeatOnLifecycle
                }
                catch (e:HttpException){
                    Log.e("MyTag" , e.toString())
                    return@repeatOnLifecycle
                }
                if (response.isSuccessful && response.body()!=null)
                    todoAdapter.todos = response.body()!!
                binding.progressBar.isVisible=false
            }

        }


    }
    private fun setupRecyclerView() = binding.items.apply {
        todoAdapter = TodoAdaptor()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}