package com.example.bluetoothtest

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bluetoothtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mBluetoothAdapter by lazy { (getSystemService(BLUETOOTH_SERVICE) as BluetoothManager).adapter }
    private val mBleSupported by lazy { packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { turnOnBluetooth() }
    }


    private fun turnOnBluetooth() {
        // 블루투스를 지원하는지 여부
        if (!mBleSupported) return
        // 블루투스 권한이 허용되어 있는지 확인
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            // 블루투스가 꺼져 있으면 켤 것인지 물어보기
            if (!mBluetoothAdapter.isEnabled) startActivity(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        // 블루투스 권한이 없으면 요청
        } else ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH), 0)
    }


    override fun onResume() {
        super.onResume()
        turnOnBluetooth()
    }
}