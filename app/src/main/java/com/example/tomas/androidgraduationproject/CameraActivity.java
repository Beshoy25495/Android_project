package com.example.tomas.androidgraduationproject;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import org.opencv.android.Utils;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button camera, gallery;
    private TextView textView;


    //keep track of camera capture intent
    static final int CAMERA_CAPTURE = 1;
    //keep track of cropping intent
    final int PIC_CROP = 3;
    //keep track of gallery intent
    final int PICK_IMAGE_REQUEST = 2;
    //captured picture uri
    private Uri picUri;

    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

      //  imageView = (ImageView) findViewById(R.id.imageView);
        camera = (Button) findViewById(R.id.camera);
        gallery = (Button) findViewById(R.id.gallery);
        textView = (TextView) findViewById(R.id.textView4);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        textView.setVisibility(View.INVISIBLE);
        gallery.setVisibility(View.INVISIBLE);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                try {
                    // gallery.setVisibility(View.VISIBLE);
                    // camera.setVisibility(View.INVISIBLE);
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/picture.jpg";
                    File imageFile = new File(imageFilePath);
                    picUri = Uri.fromFile(imageFile); // convert path to Uri
                    takePictureIntent.putExtra( MediaStore.EXTRA_OUTPUT,  picUri );
                    startActivityForResult(takePictureIntent, CAMERA_CAPTURE);


                } catch(ActivityNotFoundException anfe){
                    //display an error message
                    String errorMessage = "Whoops - your device doesn't support capturing images!";
                    Toast.makeText(CameraActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.gallery:


                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Start the Intent
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                break;

            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE){
                //get the Uri for the captured image
                Uri uri = picUri;
                //carry out the crop operation
                performCrop();
                Log.d("picUri", uri.toString());

            }

            else if(requestCode == PICK_IMAGE_REQUEST){
                picUri = data.getData();
                Log.d("uriGallery", picUri.toString());
                performCrop();
            }

            //user is returning from cropping the image
            else if(requestCode == PIC_CROP){
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = (Bitmap) extras.get("data");

                /*
                processing..convert
                 */


//
//                Mat mat = new Mat();
//                Utils.bitmapToMat(thePic, mat);
//
//                Mat gray = new Mat();
//                Imgproc.cvtColor(mat, gray, Imgproc.COLOR_RGB2GRAY);
//                Mat edges = new Mat();
//                // Detecting the edges
//                Imgproc.Canny(gray, edges, 60, 60*3);
//
//                // Creating an empty matrix to store the result
//                Mat morph = new Mat();
//
//                // Creating kernel matrix
//                Mat kernel = Mat.ones(5,5, CvType.CV_32F);
//
//                // Applying Blur effect on the Image
//                Imgproc.morphologyEx(edges, morph, Imgproc.MORPH_BLACKHAT, kernel);
//
//
//                Utils.matToBitmap(morph , thePic);
//


                //display the returned cropped image
                imageView.setImageBitmap(thePic);
            }

        }
    }

    private void performCrop(){
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
