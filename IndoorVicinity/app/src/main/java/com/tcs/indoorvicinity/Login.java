package com.tcs.indoorvicinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tcs.indoorvicinity.Model.Users;

public class Login extends AppCompatActivity {
    Button b1;
    EditText phnum,pass;
    String value;
    private String ParantDbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1=findViewById(R.id.login_next);
        phnum=findViewById(R.id.vendor_email);
        pass=findViewById(R.id.login_vendor_password);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();


            }
        });
    }

    private void LoginUser() {
        String phone=phnum.getText().toString().trim();
        String passw=pass.getText().toString().trim();
        if(TextUtils.isEmpty(phone)  && TextUtils.isEmpty(passw))
        {
            Toast.makeText(this, "Please fill out fields carefully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            allowaccess(phone,passw);


        }
    }

    private void allowaccess(final String phone, final String passw) {
        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(ParantDbName).child(phone).exists())
                {
                    Users usersdata=snapshot.child(ParantDbName).child(phone).getValue(Users.class);
                    if(usersdata.getPhone().equals(phone))
                    {
                        if(usersdata.getPassword().equals(passw))
                        {
                             Intent i=new Intent(Login.this,com.tcs.indoorvicinity.Vendor_add_item.class);
                             startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Wrong Passwqord", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
                else
                {
                    Toast.makeText(Login.this, "Account Do not exists", Toast.LENGTH_SHORT).show();
                    value=phnum.getText().toString().trim();
                    Intent i=new Intent(Login.this,com.tcs.indoorvicinity.SignUp.class);
                    i.putExtra("email",value);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
