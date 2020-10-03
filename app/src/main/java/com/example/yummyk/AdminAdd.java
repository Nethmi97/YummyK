package com.example.yummyk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAdd extends AppCompatActivity {

    private String categoryName, ingredientName;
    private Button addIngredientBtn;
    private EditText inputIngredient;
    private String saveCurrentDate, saveCurrentTime;
    private String ingredientKey;
    private DatabaseReference ingredientRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        categoryName = getIntent().getExtras().get("category").toString();
        ingredientRef = FirebaseDatabase.getInstance().getReference().child("Ingredients");

        addIngredientBtn = (Button) findViewById(R.id.add_ingredient_btn);
        inputIngredient = (EditText) findViewById(R.id.ingredientName);
        loadingBar = new ProgressDialog(this);


        addIngredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateIngredient();
            }
        });
    }

    private void ValidateIngredient(){
        ingredientName = inputIngredient.getText().toString();

        if(TextUtils.isEmpty(ingredientName)){
            Toast.makeText(this,"Please insert new ingredient.",Toast.LENGTH_SHORT).show();
        }else{
            StoreIngredient();
        }
    }
    private void StoreIngredient() {
        loadingBar.setTitle("Adding new Ingredient");
        loadingBar.setMessage("Please wait while we are adding new ingredient");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        ingredientKey = saveCurrentDate + saveCurrentTime;
        SaveIngredient();

    }

    private void SaveIngredient() {
        HashMap<String,Object> ingredientAdd = new HashMap<>();
        ingredientAdd.put("ingredientid", ingredientKey);
        ingredientAdd.put("date", saveCurrentDate);
        ingredientAdd.put("time", saveCurrentTime);
        ingredientAdd.put("category", categoryName);
        ingredientAdd.put("ingredientName", ingredientName);

        ingredientRef.child(ingredientKey).updateChildren(ingredientAdd)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(AdminAdd.this, IngredientCategoryAdd.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AdminAdd.this, "Ingredient added succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminAdd.this,"Error: "+message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}