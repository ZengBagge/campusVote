package com.vote.common;

import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器（用于https请求）
 */
public class MyTrustManager implements X509TrustManager {


	@Override
	public void checkClientTrusted(java.security.cert.X509Certificate[] arg0,
			String arg1) throws java.security.cert.CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] arg0,
			String arg1) throws java.security.cert.CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
}
