package org.griddynamics.tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static final String orderUUID = "app=edi_adapter_converter wingtipsTrace=8faeae6709355291 INFO  OrderCreateClient - action=EDIOrderSent originalFilename=Integration_test_Contract customerName=0005084863 orderUUID=d34149d8-88ab-4791-bb0a-46c96e034200 poNum=Test_TS5155079515 lineCount=3";
    public static final String email = "test 2667843 (test_email@griddynamics.com) test 67483 some string";
    public static final String orders = "app=edi_adapter_splitter wingtipsTrace=225debfbe6e5fac7 poiFileName=Integration_test_Contract INFO  LogUtils - POI file name: [Integration_test_Contract], total number of orders successfully processed: [2]";

    public static boolean isContain(){
        Pattern pattern = Pattern.compile("orderUUID", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(orderUUID);
        return matcher.find();
    }

    public static String getOrderUUID(){
        Pattern pattern = Pattern.compile("orderUUID=([a-z0-9\\-]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(orderUUID);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;

    }

    public static String getEmail(){
        Pattern pattern = Pattern.compile("[a-z0-9\\_]+@([a-z0-9]+\\.)+[a-z]{2,4}");
        Matcher matcher = pattern.matcher(email);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }

    public static String howManyOrders(){
        Pattern pattern = Pattern.compile("total number of orders successfully processed:\\s*\\[([0-9]+)\\]");
        Matcher matcher = pattern.matcher(orders);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(isContain());
        System.out.println(getOrderUUID());
        System.out.println(getEmail());
        System.out.println(howManyOrders());
    }
}
