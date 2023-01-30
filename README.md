




## QR Code Generator for Spring boot

This app is very easy to use, you just click the clone and enter the required text and it will generate a qr code for you.

QR codes are a special type of barcode created by Denso Wave Incorporated. QR codes encode text and numbers using a pattern of pixels that is designed to be easy for optical scanners to read.The README.md you are reading is designed to teach programmers how to encode information in a QR code.

<p float="left">
<img style="display:inline-block" src="https://raw.githubusercontent.com/kozakdenys/qr-code-styling/master/src/assets/facebook_example_new.png" width="240" />
<img style="display:inline-block" src="https://raw.githubusercontent.com/kozakdenys/qr-code-styling/master/src/assets/qr_code_example.png" width="240" />
<img style="display:inline-block" src="https://raw.githubusercontent.com/kozakdenys/qr-code-styling/master/src/assets/telegram_example_new.png" width="240" />
</p>

Examples
--------

The code below is in Java, but the other language ports are designed with essentially the same API naming and behavior.

```java
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

```

```java
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

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

```





## Dependency for QRCode Spring boot

```
        
         <dependency>
            <groupId>com.google.zxing</groupId>
            <artifactId>core</artifactId>
            <version>3.5.0</version>
        </dependency>
        <dependency>
            <groupId>com.google.zxing</groupId>
            <artifactId>javase</artifactId>
            <version>3.5.0</version>
        </dependency>
        
```
 <a href="https://mvnrepository.com/search?q=com.google.zxing" target="_blank">Dependency (com.google.zxing) link</a>
