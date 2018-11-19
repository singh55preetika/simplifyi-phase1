package com.simplifyi.simplify_phase1.Activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.simplifyi.simplify_phase1.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;

public class UserDetailsActivity extends AppCompatActivity {

 private Button btnContinue;
  public EditText user;
    String s;
int z=0;
    EditText inputUserDOb;
    Calendar myCalendar;
    private int mYear, mMonth, mDate;
    Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>^*()%!-]");
    CircleImageView profileImage;
    RelativeLayout txtAlubm, txtCamera;
    private static final int CAMERA_REQUEST = 1888;
    Intent GalIntent,CropIntent;
    Uri uri;
    public byte[] imageInByte;
    RadioButton x;
    RadioGroup y;
int counter1=0,counter11=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetailsactivity);
        requestCameraPermission();
        init();
        y=(RadioGroup)findViewById(R.id.radioGroup);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);


    }
    public void count(View view)
    {
      z=1;
    }


    public void init()
    {

        user = (EditText) findViewById(R.id.username);
        inputUserDOb = findViewById(R.id.edit_dob);
        inputUserDOb.setOnClickListener(SelectDobClick);

        btnContinue =(Button)findViewById(R.id.btn_continue);
btnContinue.setEnabled(false);
btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
        myCalendar = Calendar.getInstance();
        s = user.getText().toString();
if(s.equals(" "))
{
    z=0;
}
        profileImage = findViewById(R.id.profile_img);
        profileImage.setOnClickListener(selectImage);
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                btnContinue.setEnabled(false);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                btnContinue.setEnabled(true);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (user.length() == 0 ) {

                    btnContinue.setEnabled(false);
                    btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                    counter1=0;
                }
                else
                {
                    btnContinue.setBackgroundResource(R.drawable.button_background_orange);
                    counter1=1;

                }
            }
        });
        inputUserDOb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                btnContinue.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                btnContinue.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (inputUserDOb.length() == 0 ) {

                    btnContinue.setEnabled(false);
                    btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                }
                else
                {
                    btnContinue.setBackgroundResource(R.drawable.button_background_orange);

                    btnContinue.setEnabled(true);
                    counter11=1;
                }
            }
        });

    }


    View.OnClickListener selectImage = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            final Dialog dialog = new Dialog(UserDetailsActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.profile_image_dialog);
            dialog.show();

            txtCamera = (RelativeLayout) dialog.findViewById(R.id.camera);
            txtAlubm = (RelativeLayout) dialog.findViewById(R.id.album);
            txtCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClickImageFromCamera();
                    dialog.dismiss();
                }
            });
            txtAlubm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetImageFromGallery();
                    dialog.dismiss();
                }
            });

            RelativeLayout cancel = (RelativeLayout) dialog.findViewById(R.id.btn_no);
            cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.cancel();
                }
            });

        }
    };

    public void nextpage(View view)
    {

if(counter1==1&&counter11==1&&z==1) {
    Intent intent = new Intent(UserDetailsActivity.this, Selecttabs.class);
    startActivity(intent);
}
    }

    public void ClickImageFromCamera() {

        Intent cameraIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);

    }

    public void GetImageFromGallery() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            GalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);
        } else {
            GalIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

        }

    }

    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        super.onActivityResult(requestcode,resultcode,data);
        if(requestcode==0 && resultcode == RESULT_OK)
        {
            ImageCropFunction();
        }
        if(requestcode == CAMERA_REQUEST)
        {
            if (data != null)
            {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap yourImage = bundle.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageInByte = stream.toByteArray();
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(imageInByte);
                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                    theImage = getResizedBitmap1(theImage,300);
                    profileImage.setImageBitmap(theImage);


                }
            }

        }

        else if(requestcode ==2)
        {
            if(data!=null){
                uri = data.getData();
                decodeUri(uri);
            }

        }
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {

            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {
            e.printStackTrace();

        }
    }


    public Bitmap getResizedBitmap1(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
//
//        float bitmapRatio = (float)width / (float) height;
//        if (bitmapRatio > 1) {
//            width = maxSize;
//            height = (int) (width / bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }
        return Bitmap.createScaledBitmap(image, width * 3, height * 3, true);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
//
//        float bitmapRatio = (float)width / (float) height;
//        if (bitmapRatio > 1) {
//            width = maxSize;
//            height = (int) (width / bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();


            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);


            final int REQUIRED_SIZE = 1024;


            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageInByte = stream.toByteArray();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(imageInByte);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            theImage = getResizedBitmap(theImage,200);
            profileImage.setImageBitmap(theImage);



        } catch (FileNotFoundException e) {

        }  finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {

                }
        }
    }



    View.OnClickListener SelectDobClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDate = c.get(Calendar.DAY_OF_MONTH);

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(inputUserDOb.getWindowToken(), 0);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UserDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dateOfMonth) {

                    String mn, dy;
                    int mn1 = 0, dy1;
                    if (monthOfYear < 10)
                    {
                        mn1 = Integer.parseInt(String.valueOf(monthOfYear));
                        Log.e("mn1", "" + mn1);
                        if (mn1 < 9)
                        {
                            mn1 = mn1 + 1;
                            mn = "0" + mn1;
                        }
                        else
                        {
                            dy1 = mn1 + 1;
                            mn = String.valueOf(dy1);
                        }

                    }
                    else {

                        mn = String.valueOf(monthOfYear + 1);
                        Log.e("month", " " + mn);
                    }
                    if (dateOfMonth < 10)
                        dy = "0" + dateOfMonth;
                    else
                        dy = String.valueOf(dateOfMonth);

                    inputUserDOb.setText(dy + "-" + mn + "-" + year);
                    Log.e("festival date", " " + year + " " + mn + " " + dy);


                }
            }, mYear, mMonth, mDate);

            datePickerDialog.show();
        }
    };


    /////////requesting camera and storage permissions
    public void requestCameraPermission()
    {
        Permissions.check(this, new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                "Camera and storage permissions are required because...", new Permissions.Options()
                        .setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {

                        Log.e("grant permissions","Permissions are granted");

                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        Toast.makeText(getApplicationContext(),"Permissions are necessary,Please grant the permissions",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getPackageName(), null));
                        startActivity(intent);
                        Log.e("abcdefdg", Arrays.toString(deniedPermissions.toArray()));
                    }

                    @Override
                    public boolean onBlocked(Context context, ArrayList<String> blockedList) {
                        Toast.makeText(context, "Camera+Storage blocked:\n" + Arrays.toString(blockedList.toArray()),
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public void onJustBlocked(Context context, ArrayList<String> justBlockedList, ArrayList<String> deniedPermissions) {
                        Toast.makeText(context, "Camera+Storage just blocked:\n" + Arrays.toString(deniedPermissions.toArray()),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }



}

