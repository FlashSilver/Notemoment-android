package com.uci;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by zhangtianren on 1/11/16.
 */
public class get {
    String add;
    public void main(String [] args){
        try {
            add=InetAddress.getByName("127.0.0.1").toString();
            System.out.println(add);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
