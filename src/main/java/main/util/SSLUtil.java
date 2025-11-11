package main.util;

import javax.net.ssl.*;
import java.io.*;
import java.security.*;
import java.security.cert.*;

/**
 * SSL/TLS utility for secure connections
 */
public class SSLUtil {
    private static final String KEYSTORE_FILE = "studyconnect.jks";
    private static final String KEYSTORE_PASSWORD = "studyconnect123";
    private static final String KEY_PASSWORD = "studyconnect123";
    private static KeyStore keyStore;
    private static KeyStore trustStore;
    
    static {
        try {
            initializeKeyStores();
        } catch (Exception e) {
            System.err.println("Failed to initialize SSL: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Initialize keystores from file
     */
    private static void initializeKeyStores() throws Exception {
        // Load keystore
        keyStore = KeyStore.getInstance("JKS");
        File keystoreFile = new File(KEYSTORE_FILE);
        
        if (!keystoreFile.exists()) {
            throw new FileNotFoundException("Keystore file not found: " + KEYSTORE_FILE + 
                "\nPlease run: keytool -genkeypair -alias studyconnect -keyalg RSA -keysize 2048 " +
                "-validity 365 -keystore studyconnect.jks -storepass studyconnect123 " +
                "-keypass studyconnect123 -dname \"CN=localhost, OU=StudyConnect, O=University, L=City, ST=State, C=US\"");
        }
        
        try (FileInputStream fis = new FileInputStream(keystoreFile)) {
            keyStore.load(fis, KEYSTORE_PASSWORD.toCharArray());
        }
        
        // Use same keystore as truststore for P2P
        trustStore = keyStore;
    }
    
    /**
     * Create SSL Server Socket Factory
     */
    public static SSLServerSocketFactory getServerSocketFactory() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        
        // Initialize key manager with our keystore
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, KEY_PASSWORD.toCharArray());
        
        // Initialize trust manager with our truststore
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getServerSocketFactory();
    }
    
    /**
     * Create SSL Socket Factory
     */
    public static SSLSocketFactory getSocketFactory() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        
        // For client connections, trust all certificates (P2P scenario)
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { 
                    return new X509Certificate[0]; 
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) 
                    throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) 
                    throws CertificateException {}
            }
        };
        
        sslContext.init(null, trustAllCerts, new SecureRandom());
        
        return sslContext.getSocketFactory();
    }
    
    /**
     * Check if SSL is available
     */
    public static boolean isSSLAvailable() {
        try {
            SSLContext.getInstance("TLS");
            return keyStore != null;
        } catch (Exception e) {
            return false;
        }
    }
}
