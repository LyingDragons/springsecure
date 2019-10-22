package com.mj;

import com.mj.utils.MD5Util;

public class MJ {

    public static void main(String[] args) {
        String encode = MD5Util.encode((String) "123456");
        System.out.println(encode);
    }


}
