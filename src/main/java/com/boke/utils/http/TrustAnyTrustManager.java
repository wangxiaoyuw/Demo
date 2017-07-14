package com.boke.utils.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by wangzy on 2017/5/18.
 */

public class TrustAnyTrustManager implements X509TrustManager{

    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
