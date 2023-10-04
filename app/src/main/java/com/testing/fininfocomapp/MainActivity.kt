package com.testing.fininfocomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    val userList : ArrayList<User> = arrayListOf()
    lateinit var userAdapter : UserAdapter
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

   /*     userList.addAll(listOf(
            User("John Doe", 30, "New York"),
            User("Jane Smith", 25, "Los Angeles"),

        ))*/

        realm = Realm.getDefaultInstance()


        val userDataList = listOf(
            User("Kakashi", 28, "Los Angeles"),
            User("Itachi", 24, "Hyderabad"),
            User("Naruto", 26, "Tokyo"),


        )

        realm.beginTransaction()
        for (userData in userDataList) {
            val user = realm.createObject(UserRealmModel::class.java)
            user.name = userData.name
            user.age = userData.age
            user.city = userData.city
        }
        realm.commitTransaction()
        initAdapter()

       /* val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter*/
    }

    private fun initAdapter() {
        val userList1 = realm.where(UserRealmModel::class.java).findAll()

        for (userRealmModel in userList1) {
            val user = User(
                userRealmModel.name,
                userRealmModel.age,
                userRealmModel.city
            )
            userList.add(user)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        userAdapter = UserAdapter(userList)
        recyclerView.adapter = userAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sort_name -> {
                userList.sortBy { it.name }
                userAdapter.notifyDataSetChanged()

                Toast.makeText(this,"Name",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_sort_age -> {
                userList.sortBy { it.age }
                userAdapter.notifyDataSetChanged()
                Toast.makeText(this,"Age",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_sort_city -> {
                userList.sortBy { it.city }
                userAdapter.notifyDataSetChanged()
                Toast.makeText(this,"City",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}