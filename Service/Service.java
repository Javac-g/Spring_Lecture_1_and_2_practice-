package com.company.Service;

import com.fasterxml.jackson.core.io.DataOutputAsStream;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<ResponseDTO> datalist = new ArrayList<>();


    public String Print(String msg){
        return msg;
    }
    public void log(String type,ResponseDTO user){
        byte[] data = ("\nType: " + type + "\nName: " + user.getName() + "\nId: " + user.getId()).getBytes();
        String info = ("\nCar: " + user.getCarEnum() + "\nTool first: " + user.getTool().getFirst() + "\nTool second: " + user.getTool().getSecond());

        try(FileOutputStream fileOutputStream = new FileOutputStream("log.dat",true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)){

                byteArrayOutputStream.write(data);
                byteArrayOutputStream.writeTo(fileOutputStream);
                dataOutputStream.writeUTF(info);

        }catch (IOException e){

            e.printStackTrace();
        }
    }
}
