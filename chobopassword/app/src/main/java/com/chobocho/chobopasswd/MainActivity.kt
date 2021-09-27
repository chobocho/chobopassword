package com.chobocho.chobopasswd

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.chobocho.dbmanager.JsonPasswdDB
import com.chobocho.dbmanager.PasswdDBInf
import com.chobocho.dbmanager.PasswdManager
import com.chobocho.dbmanager.sqlLite3PasswdDB
import android.R.attr.button
import android.view.View
import android.R.attr.label

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context


class MainActivity : AppCompatActivity() {
    val TAG = "[ChoboPW] " + MainActivity::class.simpleName
    val passwdList: ArrayList<String> = ArrayList()

    lateinit var passwdManager: PasswdManager
    lateinit var passwdDBInf : PasswdDBInf
    lateinit var searchView: SearchView
    lateinit var passwdListView: ListView
    lateinit var passwdInfo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPasswdManager()

        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initPasswdManager() {
        passwdDBInf = JsonPasswdDB(this)
        // passwdDBInf = sqlLite3PasswdDB(this)
        passwdManager = PasswdManager(passwdDBInf)
        updatePasswordList("")
    }

    fun updatePasswordList(query: String) {
        passwdList.clear()
        passwdManager.getItemList(query)!!.stream().forEach(passwdList::add)
        passwdList.sort()
    }


    fun initUI() {
        searchView = findViewById(R.id.passwdSearchView)

        passwdListView = findViewById(R.id.passwdListView)

        val adpater: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, passwdList)

        passwdListView.setAdapter(adpater)

        passwdListView.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, l ->
            val title = adapterView.getItemAtPosition(position) as String
            displaySelectedText(title)
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                updatePasswordList(query!!)
                adpater.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                updatePasswordList(newText!!)
                adpater.notifyDataSetChanged()
                return false
            }
        })

        passwdInfo = findViewById(R.id.passwdInfo)
        passwdInfo.isEnabled = false

        val passwdInfoEditSwitch = findViewById<Switch>(R.id.switchEdit)
        passwdInfoEditSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            passwdInfo.isEnabled = isChecked
        }
        
        val copyBtn = findViewById<Button>(R.id.btnCopy)
        copyBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("passwd", passwdInfo.text)
                clipboard.setPrimaryClip(clip)
            }
        })
    }

    fun displaySelectedText(title: String) {
        Log.i(TAG, "displaySelectedText" + title)
        passwdInfo.setText(passwdManager.search(title))
    }

    override fun onPause() {
        super.onPause()
        passwdInfo.setText("")
    }

}