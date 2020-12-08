package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String manTurn="";
        String comTurn="";
        HashSet<String> asd = new HashSet<>();
        ArrayList<String> pom = new ArrayList<>();
        //

        //
        String passwordToHash = "BD9B5544739FCE7359C267E734E380A4";
        System.out.println();
        byte[] key = getKey();

        String securePassword = get_SHA_256_SecurePassword(passwordToHash, key);
        System.out.println("HMAC: " + securePassword);
        System.out.println();
        for (String s : args) {
            if (asd.add(s)) {
            }
            else {
                System.out.println("Не Повторятесь!");
            }
            pom.add(s);
        }
        if (!(asd.size() <= 3 || asd.size() % 2 == 0)) {
            System.out.println("Можете играть");
            String m_g = manMove(manTurn);
            String c_g = compMove(comTurn);
            games(pom,m_g,c_g);
            System.out.println("HMAC key" + passwordToHash);
        }
        else System.out.println("Введите правильно и аккуратно"+"\n" +"java -jar gamr.jar камень бумага ножницы ящерица спок ");

    }
    public static String manMove(String man){
        System.out.print("меню" +"\n"+  " 1 - Камень"+"\n"+  " 2 - Ножницы " +"\n"+ " 3 - Бумага"+"\n"+ " 4 - Ящерица "+"\n"+ " 5 - Спок "+"\n"+  " 0 - Exit."+"\n");
        System.out.print("Enter your Move: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        switch (number) {
            case 1:  man = "камень";
                break;
            case 2:  man = "ножницы";
                break;
            case 3:  man = "бумага";
                break;
            case 4:  man = "ящерица";
                break;
            case 5:  man = "спок";
                break;
            case 0:  man="Exit";
                break;
            default: man = "Не знаем такого";
                break;
        }
        System.out.println("Your Move: "+ man);
        return man;
    }
    public static String compMove(String comp){
        int x = (int) (Math.random() *4);
        switch (x) {
            case 0:  comp = "камень";
                break;
            case 1:  comp = "ножницы";
                break;
            case 2:  comp = "бумага";
                break;
            case 3:  comp = "ящерица";
                break;
            case 4:  comp = "спок";
                break;
            default: comp = "Не знаем такого";
                break;
        }
        System.out.println("Computer Move: "  + comp);
        return comp;
    }
    public static void games(ArrayList<String> arr,String m1, String m2 ){
        if (arr.contains(m1) && arr.contains(m2)) {
//            System.out.println(m1 + " [" + arr.indexOf(m1) + "]");
//
//            System.out.println(m2 + " [" + arr.indexOf(m2) + "]");
        } else System.out.println("Error");
        if (arr.indexOf(m1) == 0 && arr.indexOf(m2) == 4) {
            System.out.println("You Lose");
        } else if (arr.indexOf(m1) == arr.indexOf(m2)) {

            System.out.println("Tie");
        } else if (arr.indexOf(m1) > arr.indexOf(m2)) {
            System.out.println("You win");
        } else if (arr.indexOf(m1) < arr.indexOf(m2)) {
            System.out.println("You lose");
        }
    }
    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] key) throws NoSuchAlgorithmException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static byte[] getKey()
    {
        SecureRandom sr = new SecureRandom();
        byte[] key = new byte[16];
        sr.nextBytes(key);

        return key;
    }
}
