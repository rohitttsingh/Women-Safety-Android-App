package com.module.emunicipalcorporation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.module.emunicipalcorporation.databinding.ActivityDashboardBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class VehicleParkingIssueActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int REQUEST_LOCATION = 1;
    androidx.appcompat.widget.Toolbar toolbar;

    LocationManager locationManager;
    String latitude, longitude;
    FusedLocationProviderClient fusedLocationProviderClient;


    String cameraPermission[];
    String storagePermission[];

    Uri image_uri;

    Button sendBtn,show;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;
    EditText mResultEt;
    ImageView mPreviewIv;
    public static final String CHANNEL_ID = "channel1";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_parking_issue);

        mResultEt = findViewById(R.id.resultET);
        mPreviewIv = findViewById(R.id.imageV);
        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        show = (Button) findViewById(R.id.getlocation);
        txtphoneNo = (EditText) findViewById(R.id.editText);
        txtMessage = (EditText) findViewById(R.id.editText2);

        toolbar =  findViewById(R.id.toolbar);
        getSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        });

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(VehicleParkingIssueActivity.this);


        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);




        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(VehicleParkingIssueActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(VehicleParkingIssueActivity.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    getCurrentLocation();


                }
            }

        });

        sendBtn.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS ))
        {
            //sendBtn.setEnabled(true);
        }
        else{

            ActivityCompat.requestPermissions(VehicleParkingIssueActivity.this,new String[] {Manifest.permission.SEND_SMS}
                    ,MY_PERMISSIONS_REQUEST_SEND_SMS);
        }


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNo = txtphoneNo.getText().toString();
                message = "Problem :" + txtMessage.getText().toString() +
                        "\nVehicle Number: \n" + mResultEt.getText().toString() +
                        "\n\n Location is :  https://maps.google.com/?q=" + latitude +","+ longitude;

                sendSMSMessage(phoneNo,message);

            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showImageImportDialog();
                return true;
            }
        });
        

    }

    private void getSupportActionBar(Toolbar toolbar) {
        toolbar.inflateMenu(R.menu.addimage);

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            fusedLocationProviderClient.getLastLocation().
                    addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location!=null){
                                latitude=String.valueOf(location.getLatitude());
                                longitude=String.valueOf(location.getLongitude());
                                if (latitude.length()!=0&&longitude.length()!=0)
                                {
                                    sendBtn.setEnabled(true);
                                    Toast.makeText(getApplicationContext(),"Got The Location!!",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {

                                latitude=null;
                                longitude=null;
                            }


                        }
                    });
        }

    }


    protected void sendSMSMessage(String phoneNo,String message) {



        if (phoneNo==null || phoneNo.length()==0|| message==null||message.length()==0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,message,null,null);
            Toast.makeText(getApplicationContext(),"Problem Registered Successfully",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(getApplicationContext(),"Message Failed",Toast.LENGTH_LONG).show();

    }
    public  boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(VehicleParkingIssueActivity.this,permission);
        return  (check == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccpeted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccpeted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccpeted && writeStorageAccpeted) {
                        pickCamera();
                    } else {
                        Toast.makeText(VehicleParkingIssueActivity.this, "Permission failed", Toast.LENGTH_SHORT).show();

                    }
                }
                break;

            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean writeStorageAccpeted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (writeStorageAccpeted) {
                        pickGallery();
                    } else {
                        Toast.makeText(VehicleParkingIssueActivity.this, "Permission Failed", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            showImageImportDialog();
        }

        if (id == R.id.exit) {
            Toast.makeText(VehicleParkingIssueActivity.this, "Exit Successfully", Toast.LENGTH_SHORT).show();
            finish();
            System.exit(1);
        }


        return super.onOptionsItemSelected(item);
    }

    private void showImageImportDialog() {
        String[] items = {"Camera", "Gallery"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {

                        requestCameraPermission();
                    } else {
                        pickCamera();
                    }
                }
                if (which == 1) {
                    if (!checkStoragePermission()) {

                        requestStoragePermission();
                    } else {
                        pickGallery();
                    }
                }
            }
        });
        dialog.create().show();
    }
    private void pickGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }
    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image to text");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraInent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraInent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraInent, IMAGE_PICK_CAMERA_CODE);
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);

    }
    private boolean checkStoragePermission() {
        boolean results = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return results;
    }
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }
    private boolean checkCameraPermission() {
        boolean results = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean results1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return results && results1;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)  {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData()).
                        setGuidelines(CropImageView.Guidelines.ON).
                        start(this);

            }
            if (requestCode==IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(image_uri).
                        setGuidelines(CropImageView.Guidelines.ON).
                        start(this);

            }
        }
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri =result.getUri();
                mPreviewIv.setImageURI(resultUri);

                BitmapDrawable bitmapDrawable=(BitmapDrawable) mPreviewIv.getDrawable();
                TextRecognizer recognizer= new TextRecognizer.Builder(getApplicationContext()).build();

                if (!recognizer.isOperational()){
                    Toast.makeText(VehicleParkingIssueActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else{
                    Frame frame= new Frame.Builder().setBitmap(bitmapDrawable.getBitmap()).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb =  new StringBuilder();

                    for (int i = 0;i<items.size();i++){
                        TextBlock myitems=items.valueAt(i);
                        sb.append(myitems.getValue());
                        sb.append("\n");
                    }
                    mResultEt.setText(sb.toString());
                }

            }
            else if (resultCode== CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
                Toast.makeText(VehicleParkingIssueActivity.this,""+error,Toast.LENGTH_SHORT).show();

            }
        }
    }
}