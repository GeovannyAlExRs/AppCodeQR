package com.ec.code.qr.web.app.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class ServiceGeneratorCodeQR {
	
	public String generatorQRCode(String code, int width, int height) throws WriterException, IOException {

		String nameCodeQR = code + ".png";
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("codeqr");
		builder.append(File.separator);
		builder.append(nameCodeQR);
		
		Path path = Paths.get(builder.toString());
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, width, height);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
		
		return "CODE QR GENERADO CON EXITO EN EL PATH " + path;
	}
	
	public String readCodeQR(String code) throws IOException, NotFoundException {
		
		String nameCodeQR = code + ".png";
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("codeqr");
		builder.append(File.separator);
		builder.append(nameCodeQR);
		
		Path path = Paths.get(builder.toString());
		
		BufferedImage bufferedImage = ImageIO.read(new File(path.toString()));
		
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
		
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		
		Result result = new MultiFormatReader().decode(bitmap);
		
		return result.getText();
	}
}
