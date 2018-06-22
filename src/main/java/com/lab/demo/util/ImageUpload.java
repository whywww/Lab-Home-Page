package com.lab.demo.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class ImageUpload {

    public void usrImageUpload(MultipartFile multipartFile, String photoName,
                               String directoryPath){
        if (multipartFile.isEmpty()){
            return;
        }

        //存图
        try{
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(directoryPath + photoName + ".jpg")));//保存图片到目录下
            out.write(multipartFile.getBytes());
            out.flush();
            out.close();
        }catch (IOException e){
            e.getLocalizedMessage();
            e.printStackTrace();
        }
    }
}
