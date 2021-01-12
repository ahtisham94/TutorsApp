package com.example.tutorsapp.network;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Ricardo Iramar dos Santos on 14/08/2015.
 */
public class KeyPinStore {

    private static KeyPinStore instance = null;
    private SSLContext sslContext = SSLContext.getInstance("TLS");

    public static synchronized KeyPinStore getInstance(Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        // if (instance == null) {
        instance = new KeyPinStore(context, false);
        // }
        return instance;
    }

    private KeyPinStore(Context context, boolean isSSLPining) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {


        //boolean bypassSSL = false;
        if (isSSLPining) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            InputStream caInput = null;
//                caInput = new BufferedInputStream(context.getAssets().open("cert.cer"));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            sslContext.init(null, tmf.getTrustManagers(), null);
        }
        else {
            TrustManager[] trustManagers = new TrustManager[]{new _FakeX509TrustManager()};
            HttpsURLConnection.setDefaultHostnameVerifier(new NoHostName());
            sslContext.init(null, trustManagers, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }
}

    public SSLContext getContext() {
        return sslContext;
    }

public static class _FakeX509TrustManager implements javax.net.ssl.X509TrustManager {
    private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return (_AcceptedIssuers);
    }
}

public class NoHostName implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
}
