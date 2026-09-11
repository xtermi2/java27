package com.github.xtermi2.java27.jep527hibridkeyexchange;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class PostQuantumHybridKeyExchange {
    static void main() throws NoSuchAlgorithmException, IOException {
        try (SSLSocket tlsSock = (SSLSocket) (SSLContext.getDefault().getSocketFactory().createSocket())) {
            SSLParameters params = tlsSock.getSSLParameters();

            params.setNamedGroups(new String[]{
                    // Configure the socket to use two hybrid KEM schemes and
                    "SecP256r1MLKEM768", "X25519MLKEM768",
                    // two traditional schemes
                    "secp256r1", "x25519"});
            tlsSock.setSSLParameters(params);
        }
    }
}
