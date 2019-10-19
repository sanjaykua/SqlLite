package com.sanjaykua.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sanjaykua.sqllite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DataBasehelper dataBasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        dataBasehelper=new DataBasehelper(MainActivity.this);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInseted=dataBasehelper.insertData(
                        binding.Name.getText().toString(),
                        binding.phone.getText().toString(),
                        binding.email.getText().toString(),
                        binding.pass.getText().toString()
                );

                if (isInseted==true){
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=dataBasehelper.getAllData();

                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();
                }else {
                    StringBuffer stringBuffer=new StringBuffer();

                    while (res.moveToNext()){
                        stringBuffer.append("ID"+res.getString(0)+"\n");
                        stringBuffer.append("Name"+res.getString(1)+"\n");
                        stringBuffer.append("Mobile"+res.getString(2)+"\n");
                        stringBuffer.append("Mail"+res.getString(3)+"\n");
                        stringBuffer.append("Pass"+res.getString(4)+"\n");
                    }
                    showData("Data",stringBuffer.toString());
                }

            }
        });

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update=dataBasehelper.updateData(
                        binding.id.getText().toString(),
                        binding.Name.getText().toString(),
                        binding.phone.getText().toString(),
                        binding.email.getText().toString(),
                        binding.pass.getText().toString()
                );

                if (update==true){
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Not Updated",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete=dataBasehelper.delateData(binding.id.getText().toString());

                if (delete>0){
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Not deleted",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showData(String data, String toString) {

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(data);
        builder.setMessage(toString);
        builder.show();
    }
}
