package com.alkemy.ong.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class DecodedMultipartFile implements MultipartFile {
    private final byte[] imgContent;
    private final String extension;
    private final UUID idFile = UUID.randomUUID();

    public DecodedMultipartFile(String imgCode) {

        this.imgContent = Base64.getDecoder().decode((imgCode.split(",")[0]));
        this.extension = imgCode.split(";")[0].split("/")[1];

    }

    @Override
    public String getName() {return   idFile.toString();
    }

    @Override
    public String getOriginalFilename() {return idFile + extension;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream()  {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imgContent);
        }
    }


}