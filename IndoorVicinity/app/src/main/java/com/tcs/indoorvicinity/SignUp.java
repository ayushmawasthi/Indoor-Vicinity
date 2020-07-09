package com.tcs.indoorvicinity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    Button b1;
    TextView email;
    EditText e1,e2,e3,e4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        b1=findViewById(R.id.btn_sign_up);
        email=findViewById(R.id.vendor_email_retriev);
        e1=findViewById(R.id.vendor_name);
        e2=findViewById(R.id.vendor_phone_number);
        e3=findViewById(R.id.vendor_shop_name);
        e4=findViewById(R.id.vendor_password);

        String email1=getIntent().getStringExtra("email");
        email.setText(email1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_account();

            }
        });
    }
    void create_account()
    {
        String name,phone,shop_name,password,email2;
        name=e1.getText().toString().trim();
        email2=e2.getText().toString().trim();
        shop_name=e3.getText().toString().trim();
        password=e4.getText().toString().trim();
        phone=email.getText().toString().trim();
        if(TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(shop_name) && TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please fill out fields carefully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            validate(name,email2,password,phone,shop_name);


        }
    }

    private void validate(final String name, final String email2, final String password,final String phone,final String shop_name) {
        final DatabaseReference Routeref;
        Routeref= FirebaseDatabase.getInstance().getReference();
        Routeref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object> userdatamap=new HashMap<>();
                    userdatamap.put("wmail",email2);
                    userdatamap.put("password",password);
                    userdatamap.put("name",name);
                    userdatamap.put("phone",phone);
                    userdatamap.put("shopname",shop_name);
                    Routeref.child("Users").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this, "Congrats", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(SignUp.this,com.tcs.indoorvicinity.Login.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(SignUp.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else
                {
                    Toast.makeText(SignUp.this, "Phone Number already exists", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignUp.this,com.tcs.indoorvicinity.MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
