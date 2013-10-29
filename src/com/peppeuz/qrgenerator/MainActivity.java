package com.peppeuz.qrgenerator;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity implements OnClickListener{
	
	Button creaQr;
	EditText testoQr;
	SmartImageView qrImage;
	
	final static String urlGoogleChart = "http://chart.apis.google.com/chart?chs=200x200&cht=qr&chl=";
	String urlMySite;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		creaQr = (Button) findViewById(R.id.creaqr);
		testoQr = (EditText) findViewById(R.id.testoqr);
		qrImage = (SmartImageView) findViewById(R.id.qrimage);
		creaQr.setOnClickListener(this);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }else
	    {
	    Toast.makeText(getApplicationContext(), "Non sei connesso ad internet!", Toast.LENGTH_SHORT).show();	
	    return false;
	    }
	}
	
	public boolean controlloStringa(String url)
	{
		if(url.trim().length()>0){
			return true;
		}
		else{
			Toast.makeText(getApplicationContext(), "Non hai digitato nulla", Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}

	public void creaCodiceQr(String testo){
		if (isOnline()&&controlloStringa(testo)){
			qrImage.setImageUrl(urlGoogleChart+urlMySite);
		}
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view==creaQr){
			urlMySite = testoQr.getText().toString();
			creaCodiceQr(urlMySite);
		}
	}
	

}
