package m.androbito.mfm;

//import com.android.Samsung.Note.SecurityPackage.MyPersistingService.syncTaskupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract.Colors;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ContactActivity extends Activity {
	
	protected static final int CAMERA_REQUEST = 0;
	protected static final int GALLERY_PICTURE = 1;
	private Intent pictureActionIntent = null;
	Bitmap bitmap;
	ImageView facebook; 
	ImageView youtube;
	ImageView twitter;
	ImageView imagepicker;
	String selectedImagePath;
	Button send;
	private Uri outputFileUri;
	EditText nom,prenom,email,description;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        imagepicker = (ImageView) findViewById(R.id.imageView5);
        imagepicker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startDialog();  
				
			}
		});

        facebook = (ImageView) findViewById(R.id.imageView1);
		facebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://www.facebook.com/RADIOMFM.Officiel");

		        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		        startActivity(intent);
				
			}
		});
		
		youtube = (ImageView) findViewById(R.id.imageView2);
		youtube.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://www.youtube.com/channel/UCFGC3iySyVURobB8-49Iz6A");

		        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		        startActivity(intent);
				
			}
		});
		twitter = (ImageView) findViewById(R.id.imageView3);
		twitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("https://twitter.com/mfm_officiel");

		        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		        startActivity(intent);
				
			}
		});
    
    send = (Button) findViewById(R.id.button1);
	send.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			nom = (EditText) findViewById(R.id.editText1);
			prenom = (EditText) findViewById(R.id.editText2);
			email = (EditText) findViewById(R.id.editText3);
			description = (EditText) findViewById(R.id.editText4);
			MySMSAsyncTask async = new MySMSAsyncTask();
			if(nom.getText().toString().equals("")||prenom.getText().toString().equals("")||description.getText().toString().equals("") ){
				nom.setBackgroundColor(Color.RED);
				prenom.setBackgroundColor(Color.RED);
				email.setBackgroundColor(Color.RED);
				description.setBackgroundColor(Color.RED);
			}else{
			async.execute(nom.getText().toString(),prenom.getText().toString(),email.getText().toString(),description.getText().toString());
			nom.setBackgroundColor(Color.WHITE);
			prenom.setBackgroundColor(Color.WHITE);
			email.setBackgroundColor(Color.WHITE);
			description.setBackgroundColor(Color.WHITE);
			}
			
		}
	});
	
    }
	// ///////////////////////////////////////////////////////////////////////////////////:
       ///////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////////////////
	// starting the synctask method

	private class MySMSAsyncTask extends AsyncTask<String, Integer, Double> {

		@Override
		protected Double doInBackground(String... params) {
			// TODO Auto-generated method stub
			//postData(params);
			try {
				postdatawithimage(params);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		private void postdatawithimage(String[] params) throws ClientProtocolException, IOException {
			// TODO Auto-generated method stub
			HttpEntity resEntity;

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://mfmradio.ma/appmobile/mfmwebservice/uploads/");
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            Log.v("selected image path",selectedImagePath) ;
            File file= new File(selectedImagePath);
            FileBody bin = new FileBody(file);

            try {
            	reqEntity.addPart("page", new StringBody("5"));
				reqEntity.addPart("nom", new StringBody(params[0]));
				reqEntity.addPart("prenom", new StringBody(params[1]));
				reqEntity.addPart("email", new StringBody(params[2]));
				reqEntity.addPart("description", new StringBody(params[3]));
				reqEntity.addPart("picture", bin);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           

            post.setEntity(reqEntity);
                HttpResponse response = httpClient.execute(post);
            resEntity = response.getEntity();
            String response_str = EntityUtils.toString(resEntity);
            Gson gson = new Gson(); 
            gson.toJson(response_str);
             if (resEntity != null) {
                 Log.i("RESPONSE",response_str);
                 runOnUiThread(new Runnable(){
                        public void run() {
                             try {
                                Toast.makeText(getApplicationContext(),"Upload Complete. Check the server uploads directory.", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                           }
                    });
             }

		
		}

		protected void onPostExecute(Double result) {
			// pb.setVisibility(View.GONE);
			Log.v("status", "Message sent  to server");
			// Toast.makeText(getApplicationContext(), "command sent",
			// Toast.LENGTH_LONG).show();
		}

		protected void onProgressUpdate(Integer... progress) {
			// pb.setProgress(progress[0]);
		}

		public void postData(String... params) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://mfmradio.ma/appmobile/mfmwebservice/");

			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("page", "5"));
				nameValuePairs.add(new BasicNameValuePair("nom", params[0]
						.toString()));
				nameValuePairs.add(new BasicNameValuePair("prenom", params[1]
						.toString()));
				nameValuePairs.add(new BasicNameValuePair("email", params[2]
						.toString()));
				nameValuePairs.add(new BasicNameValuePair("description", params[3]
						.toString()));
				
				
				// nameValuePairs.add(new BasicNameValuePair("liscencenumber",
				// params[6]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				int presonsestatus = 0;
				presonsestatus = response.getStatusLine().getStatusCode();
				Log.v("presonsestatus", "" + presonsestatus);

				switch (presonsestatus) {
				case 200:
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String responseBody = EntityUtils.toString(entity);
						Log.v("responsebody", "" + responseBody.toString());
					} else {
						Log.v("responsebody", "response vide");

					}
					break;
				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////
	private void startDialog() {
	    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
	    myAlertDialog.setTitle("Transfez votre Image");
	    myAlertDialog.setMessage("Voulez vous mettre votre image?");

	    myAlertDialog.setPositiveButton("Gallery",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            Intent.ACTION_GET_CONTENT, null);
	                    pictureActionIntent.setType("image/*");
	                    pictureActionIntent.putExtra("return-data", true);
	                    startActivityForResult(pictureActionIntent,
	                            GALLERY_PICTURE);
	                }
	            });

	    myAlertDialog.setNegativeButton("Camera",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                    startActivityForResult(pictureActionIntent,
	                            CAMERA_REQUEST);

	                }
	            });
	    myAlertDialog.show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == GALLERY_PICTURE) {
	        if (resultCode == RESULT_OK) {
	            if (data != null) {
	                // our BitmapDrawable for the thumbnail
	                BitmapDrawable bmpDrawable = null;
	                // try to retrieve the image using the data from the intent
	                Cursor cursor = getContentResolver().query(data.getData(),
	                        null, null, null, null);
	                if (cursor != null) {

	                    cursor.moveToFirst();

	                    int idx = cursor.getColumnIndex(ImageColumns.DATA);
	                    String fileSrc = cursor.getString(idx);
	                    bitmap = BitmapFactory.decodeFile(fileSrc); // load
	                                                                        // preview
	                                                                        // image
	                    bitmap = Bitmap.createScaledBitmap(bitmap,
	                            100, 100, false);
	                    // bmpDrawable = new BitmapDrawable(bitmapPreview);
	                    imagepicker.setImageBitmap(bitmap);
	                } else {

	                    bmpDrawable = new BitmapDrawable(getResources(), data
	                            .getData().getPath());
	                    imagepicker.setImageDrawable(bmpDrawable);
	                }

	            } else {
	                Toast.makeText(getApplicationContext(), "Cancelled",
	                        Toast.LENGTH_SHORT).show();
	            }
	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    } else if (requestCode == CAMERA_REQUEST) {
	        if (resultCode == RESULT_OK) {
	            if (data.hasExtra("data")) {

	                // retrieve the bitmap from the intent
	                bitmap = (Bitmap) data.getExtras().get("data");


	 Cursor cursor = getContentResolver()
	                        .query(Media.EXTERNAL_CONTENT_URI,
	                                new String[] {
	                                        Media.DATA,
	                                        Media.DATE_ADDED,
	                                        MediaStore.Images.ImageColumns.ORIENTATION },
	                                Media.DATE_ADDED, null, "date_added ASC");
	                if (cursor != null && cursor.moveToFirst()) {
	                    do {
	                        Uri uri = Uri.parse(cursor.getString(cursor
	                                .getColumnIndex(Media.DATA)));
	                        selectedImagePath = uri.toString();
	                    } while (cursor.moveToNext());
	                    cursor.close();
	                }

	                Log.e("path of the image from camera ====> ",
	                        selectedImagePath);


	                bitmap = Bitmap.createScaledBitmap(bitmap, 100,
	                        100, false);
	                // update the image view with the bitmap
	                imagepicker.setImageBitmap(bitmap);
	            } else if (data.getExtras() == null) {

	                Toast.makeText(getApplicationContext(),
	                        "No extras to retrieve!", Toast.LENGTH_SHORT)
	                        .show();

	                BitmapDrawable thumbnail = new BitmapDrawable(
	                        getResources(), data.getData().getPath());

	                // update the image view with the newly created drawable
	                imagepicker.setImageDrawable(thumbnail);

	            }

	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    }

	}


	// /////////////////////////////////////////////////////////////////////////////////////
	/////downstamp the picture
	
	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
               || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }

	
	//////////////////////////////////////////////////////////////////////////////////////////
	//get path of URI
	
	private String getRealPathFromURI(Uri contentURI) {
	    String result;
	    Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
	    if (cursor == null) { // Source is Dropbox or other similar local file path
	        result = contentURI.getPath();
	    } else { 
	        cursor.moveToFirst(); 
	        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA); 
	        result = cursor.getString(idx);
	        cursor.close();
	    }
	    return result;
	}
	//////////////////////////////////////////////////////////////////////////////////////////

}