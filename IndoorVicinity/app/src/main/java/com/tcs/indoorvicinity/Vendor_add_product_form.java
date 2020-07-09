package com.tcs.indoorvicinity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Vendor_add_product_form extends AppCompatActivity {
    ImageView product_img;
    private static final int PICK_IMAGE = 1;
    Uri imageuri;
    EditText e1,e2,e3,e4;
    Button addnew;
    String p_name,p_category,p_brand,p_discount,save_cuurent_date,getSave_cuurent_time;
    String productRamdomkey,downloadImageUrl;
    private StorageReference ProductImageRef;
    DatabaseReference ProductRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_product_form);
        ProductImageRef= FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductRef= FirebaseDatabase.getInstance().getReference().child("Products");
        product_img=findViewById(R.id.add_product_image);
        e1=findViewById(R.id.form_name_of_product);
        e2=findViewById(R.id.form_categ_of_product);
        e3=findViewById(R.id.form_brand_of_product);
        e4=findViewById(R.id.form_discount_of_product);
        addnew=findViewById(R.id.btn_add_product);
        product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);*/
             OpenGalley();
            }
        });
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validateproductdata();
            }
        });



    }
    void OpenGalley()
    {
        Intent gallery=new Intent();
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, PICK_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            imageuri=data.getData();
            try {
                //Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                //product_img.setImageBitmap(bitmap);
                product_img.setImageURI(imageuri);

            }
            catch (Exception e)
            {
                e.printStackTrace();

            }

        }
    }
    private void Validateproductdata()

    {
        p_name=e1.getText().toString().trim();
        p_category=e2.getText().toString().trim();
        p_brand=e3.getText().toString().trim();
        p_discount=e4.getText().toString().trim();
        if(imageuri==null && TextUtils.isEmpty(p_name) && TextUtils.isEmpty(p_category) && TextUtils.isEmpty(p_brand) && TextUtils.isEmpty(p_discount))
        {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show();
        }
        else {
            storeproductinfo();
        }
    }
    private void storeproductinfo()
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentdate=new SimpleDateFormat("MM dd yyyy");
        save_cuurent_date=currentdate.format(calendar.getTime());
        SimpleDateFormat currenttime=new SimpleDateFormat("HH:mm:ss a");
        save_cuurent_date=currenttime.format(calendar.getTime());
        productRamdomkey=save_cuurent_date+getSave_cuurent_time;
        final StorageReference filepath=ProductImageRef.child(imageuri.getLastPathSegment()+productRamdomkey+".jpg");
        final UploadTask uploadTask=filepath.putFile(imageuri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String temp1=e.toString();
                Toast.makeText(Vendor_add_product_form.this, "Error: "+temp1, Toast.LENGTH_SHORT).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Vendor_add_product_form.this, "Image Added Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> urlTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImageUrl=filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            downloadImageUrl=task.getResult().toString();
                            Toast.makeText(Vendor_add_product_form.this, "Getting Product Image URl", Toast.LENGTH_SHORT).show();
                            saveproductinfo();
                        }

                    }
                });

            }
        });




    }
    private void saveproductinfo()
    {
        HashMap<String,Object>productMap=new HashMap<>();
        productMap.put("pid",productRamdomkey);
        productMap.put("date",save_cuurent_date);
        productMap.put("time",getSave_cuurent_time);
        productMap.put("Product_Name",p_name);
        productMap.put("Product_Image",product_img);
        productMap.put("Product_Category",p_category);
        productMap.put("Product_Brand",p_brand);
        productMap.put("Product_Discount",p_discount);
        ProductRef.child(productRamdomkey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Vendor_add_product_form.this, "Product Added successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String temp1=task.getException().toString();
                    Toast.makeText(Vendor_add_product_form.this, "Error: "+temp1, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
