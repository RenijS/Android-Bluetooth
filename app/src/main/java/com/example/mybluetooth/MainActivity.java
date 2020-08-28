package com.example.mybluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView bluetoothTxt;
    Button onBtn, offBtn, listBtn;
    ListView listview;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothTxt = (TextView) findViewById(R.id.bluetoothTxt);
        onBtn = (Button) findViewById(R.id.onBtn);
        offBtn = (Button) findViewById(R.id.offBtn);
        listBtn = (Button) findViewById(R.id.listBtn);
        listview = (ListView) findViewById(R.id.lView);
        BA = BluetoothAdapter.getDefaultAdapter();
    }
//OnClickListener is not used because in xml we have used onClick in button
//For on and off button
    public void off(View view) {
        BA.disable();
        Toast.makeText(getApplicationContext(),"Turned off", Toast.LENGTH_LONG).show();
    }

    public void on(View view) {
        if (!BA.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn,0);
            Toast.makeText(getApplicationContext(),"Turned On", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Already Turned On", Toast.LENGTH_LONG).show();
        }
    }
 //Makes self visible for bluetooth
    public void discoverable(View view){
        Intent discover = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(discover, 0);
    }
 //gets list of paired devices
    public void list(View view) {
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        for (BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing paired devices", Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);
    }
}
