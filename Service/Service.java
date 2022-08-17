package com.company.Service;

import com.company.Controller.Car;
import com.company.Controller.RequestDTO;
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
    public void setEnum(ResponseDTO user, RequestDTO json){
        for (Car x :json.getCarlist()){
            switch (x.getValue()){
                case "F":
                    user.setCarEnum(CarEnum.Ford);
                    break;
                case "P":
                    user.setCarEnum(CarEnum.Porshe);
                    break;
                default:
                    user.setCarEnum(CarEnum.Bicycle);
                    break;
            }
        }
    }
    public ResponseDTO create(RequestDTO json){

        ResponseDTO user = new ResponseDTO();

        user.setName(json.getName());
        user.setId(json.getId());
        user.setTool(json.getTool());

        setEnum(user,json);
        log("Created" ,user);

        datalist.add(user);

        return user;
    }

    public ResponseDTO read(Integer id){
        for (ResponseDTO user: datalist){
            if (user.getId().equals(id)){
                log("Searched",user);
                return user;
            }
        }
        return null;
    }


}//EOF
