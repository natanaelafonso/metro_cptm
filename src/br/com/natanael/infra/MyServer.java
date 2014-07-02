package br.com.natanael.infra;

import android.app.Application;
import android.os.Build;
import br.com.natanael.R;

public class MyServer {
    private String uri;

    public MyServer(Application application){
        //if (taNoEmulador()) {
         //   uri = "http://10.0.2.2:8080/%s";
        //} else {
			uri = application.getResources().getString(R.string.server_uri);
        //}
    }

    public String uriFor(String value) {
        return String.format(uri, value);
    }

    private boolean taNoEmulador() {
        return Build.PRODUCT.equals("google_sdk")
                || Build.PRODUCT.equals("sdk");
//		return true;
    }

}
