package com.example.springbootqrcode;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

/**
 * @author "Tojaliyev Asliddin"
 * @since 17/10/22 20:29 (Monday)
 * SpringBoot-QRCode/IntelliJ IDEA
 */
@Controller
public class QRCodeController {
    @GetMapping("/")
    public String getQRCode(Model model){
        String pdp="https://pdp.uz";
        String github="https://github.com/uz-java";

        final String QR_CODE_IMAGE_PATH="./src/main/resources/static/img/QRCode.png";

        byte[] image=new byte[0];
        try {
            image=QRCodeGenerator.getQRCodeImage(pdp,250,250);
            QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);
        }catch (WriterException | IOException e){
            e.printStackTrace();
        }

        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("pdp",pdp);
        model.addAttribute("github",github);
        model.addAttribute("qrcode",qrcode);
        return "qrcode";
    }
}
