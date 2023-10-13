package com.example.testfindequipinsamenet

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.testfindequipinsamenet.databinding.ActivityMainBinding
import com.example.testfindequipinsamenet.model.MainActivityViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "TestFindEquipInSameNet_MainActivity"

    private val NET_WORK_TYPE_NONE = "none"
    private val NET_WORK_TYPE_WIFI = "wifi"
    private val NET_WORK_TYPE_NORMAL = "normal"

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application).create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.activityMainButtonFindEquip.setOnClickListener(this)

        if (checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.ACCESS_WIFI_STATE), 0)
        }
        if(checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.ACCESS_NETWORK_STATE), 0)
        }


    }

    override fun onClick(v: View?) {
        var wifiManager: WifiManager = getSystemService(WIFI_SERVICE) as WifiManager
//        if (wifiManager.connectionInfo.wifiStandard)
//        Log.i(TAG, "onCreate: the wifiStandard is " + wifiManager.connectionInfo.wifiStandard)
        getNetWorkType()
    }

    fun getNetWorkType(): String{
        var netWorkType: String = NET_WORK_TYPE_NONE
        var connManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        for (network in connManager.allNetworks){
            Log.i(TAG, "getNetWorkType:  the transportinfo is " +  connManager.getLinkProperties(network)?.routes + " and the net type is " + connManager.activeNetworkInfo?.typeName)
        }
        return netWorkType
    }
}
