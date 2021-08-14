package io.github.astrapi69.xml;

import java.security.PrivateKey;

public class TestBox implements Xmlable {

    public TestBox(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    private PrivateKey privateKey;
}
