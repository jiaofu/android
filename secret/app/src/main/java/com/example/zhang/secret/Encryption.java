package com.example.zhang.secret;

public class Encryption {
    public static  final String key ="49";
    public static void main(String[] args)  {
        String content = "qipa"; //需要加密的字符
        byte[] result = encryption(content, key);

        System.out.println("1234加密后的值：" + new String(result));
        System.out.println("---------------");
        System.out.println("1234解密后的值：" +new String(decipher(new String(result), key)));
    }


    public static byte[] encryption(String content,String key){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();

        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }

        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = 0 ; i < contentBytes.length; i++){
            salt = (byte)(contentBytes[i] ^ dkey ^ salt);
            result[i] = salt;
        }
        return result;
    }

    public static byte[] decipher(String content,String key){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();

        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }

        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = contentBytes.length - 1 ; i >= 0 ; i--){
            if(i == 0){
                salt = 0;
            }else{
                salt = contentBytes[i - 1];
            }
            result[i] = (byte)(contentBytes[i] ^ dkey ^ salt);
        }
        return result;
    }

}
