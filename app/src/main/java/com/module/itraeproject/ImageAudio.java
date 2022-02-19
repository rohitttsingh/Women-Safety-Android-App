package com.module.itraeproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.ncorti.slidetoact.SlideToActView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Array;

public class ImageAudio extends AppCompatActivity {
    ImageButton camera , mic;
    ImageView click_image_id1;
    VideoView click_video;
    private Uri mImageUri;

    private DatabaseReference mDatabaseRef;
    private StorageReference mStrorageRef;
    private static final int pic_id = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_audio);

        camera =(ImageButton) findViewById(R.id.img);
        mic=(ImageButton)findViewById(R.id.mic);
        click_image_id1 = (ImageView)findViewById(R.id.click_image1);
        click_video=findViewById(R.id.click_image2);

        mStrorageRef= FirebaseStorage.getInstance().getReference("Upload");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Upload");


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
                Toast.makeText(ImageAudio.this,"Opening Camera ...",Toast.LENGTH_SHORT).show();

            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    Intent video_intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    startActivityForResult(video_intent,156);
                Toast.makeText(ImageAudio.this,"Opening Video ...",Toast.LENGTH_SHORT).show();

            }
        });

        SlideToActView sta = (SlideToActView) findViewById(R.id.example);
        sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                Toast.makeText(ImageAudio.this,"Your Images And Video Has Been Uploaded...",Toast.LENGTH_LONG).show();

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==pic_id)
        {

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        Uri mImageUri=data.getData();
        Picasso.get().load(mImageUri).into(click_image_id1);
        click_image_id1.setImageBitmap(photo);
        click_image_id1.setImageURI(mImageUri);
        uploadFile(mImageUri);

        }
        else if (requestCode==156)
        {
            click_video.setVideoURI(data.getData());
            click_video.start();

        }
    }
    private String getFileExtension(Uri uri)
{
    ContentResolver cR=getContentResolver();
    MimeTypeMap mime=MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));
}
    private void uploadFile(Uri mImageUri) {
        if (mImageUri!=null) {

            Toast.makeText(this, "Successful",Toast.LENGTH_LONG).show();
            StorageReference fileReference = mStrorageRef.child(System.currentTimeMillis() + "."+
                    getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Upload upload=new Upload(taskSnapshot.getUploadSessionUri().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ImageAudio.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());

                }
            });
        }
        else{
            Toast.makeText(this, "NO IMAGE",Toast.LENGTH_LONG).show();
        }

    }

}