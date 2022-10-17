package com.example.springbootqrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author "Tojaliyev Asliddin"
 * @since 17/10/22 20:29 (Monday)
 * SpringBoot-QRCode/IntelliJ IDEA
 */
public class QRCodeGenerator {
    public static void generateQRCodeImage(String text,int width,int height,String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,width,height);
        Path path= FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
    }

    public static byte[] getQRCodeImage(String text,int width,int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,width,height);

        ByteArrayOutputStream pngOutputStream=new ByteArrayOutputStream();
        MatrixToImageConfig config=new MatrixToImageConfig(0xD2191902, 0xFFFFC041);
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream,config);
        return pngOutputStream.toByteArray();
    }
}
