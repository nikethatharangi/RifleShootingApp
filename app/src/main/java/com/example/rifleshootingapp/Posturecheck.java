package com.example.rifleshootingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rifleshootingapp.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;

public class Posturecheck extends AppCompatActivity {

    private Button btnSelectImg, btnPredict,btnCapture;
    TextView txtResult;
    ImageView imageViewcapture;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posturecheck);

        getPermission();

        btnCapture = findViewById(R.id.btnCapture);
        btnPredict = findViewById(R.id.btnPredict);
        btnSelectImg = findViewById(R.id.btnSelect);
        txtResult = findViewById(R.id.txtResult);
        imageViewcapture = findViewById(R.id.imageViewCapture);

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,10);

            }
        });

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,12);

            }
        });

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Model model = Model.newInstance(Posturecheck.this);

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 244, 244, true); // Or use 'true' if you want smoothing

                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                    // Runs model inference and gets result.
                    Model.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    //txtResult.setText(getMax(outputFeature0.getFloatArray())+"");
                    // Releases model resources if no longer used.

                    int maxIndex = getMax(outputFeature0.getFloatArray());

                    // Determine posture correctness based on the maximum index
                    if (maxIndex == 0) {
                        txtResult.setText("Correct");
                    } else {
                        txtResult.setText("Wrong");
                    }

                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }


            }
        });
    }

    int getMax(float[] arr){
        int max = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] > arr[max]) max=i;
        }
        return max;
    }
    void getPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Posturecheck.this,new String[]{Manifest.permission.CAMERA},11);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResult){
        if(requestCode==11){
            if(grantResult.length>0){
                if(grantResult[0]!=PackageManager.PERMISSION_GRANTED){
                    this.getPermission();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode==10){
            if (data!=null){
                Uri uri = data.getData();
                try{
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    imageViewcapture.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        } else if (requestCode == 12) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageViewcapture.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}