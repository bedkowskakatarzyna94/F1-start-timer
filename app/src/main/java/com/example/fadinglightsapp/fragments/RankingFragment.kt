package com.example.fadinglightsapp.fragments

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.adapters.RankingAdapter
import com.example.fadinglightsapp.viewmodels.RankingViewModel
import kotlinx.android.synthetic.main.fragment_ranking.*
import java.nio.file.attribute.AclEntry

class RankingFragment : Fragment() {

    private val rankingVm: RankingViewModel by viewModels()
    private val adapter = RankingAdapter(listOf())
    val FINE_LOCATION_RQ = 101

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rankingRecyclerView.layoutManager = LinearLayoutManager(activity)
        rankingRecyclerView.adapter = adapter
        rankingVm.users.observe(viewLifecycleOwner) { users ->
            adapter.users = users
            adapter.notifyDataSetChanged()
        }
        buttonTaps()
    }

    private fun buttonTaps(){
        permissionButton.setOnClickListener{
            checkForPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION, "location", FINE_LOCATION_RQ)
        }
    }

    private fun checkForPermissions(permission: String, name: String, requestCode: Int){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                context?.let { ContextCompat.checkSelfPermission(it, permission) } == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(context, "$name permission granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
                else -> requestPermissions(arrayOf(permission), requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String){
            if(grantResults.isEmpty() || grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context,"$name permission refused", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(context,"$name permission granted", Toast.LENGTH_SHORT).show()
            }
        }
        when (requestCode){
            FINE_LOCATION_RQ -> innerCheck("location")
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int){
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK"){ dialog, which ->  
                requestPermissions(arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}
