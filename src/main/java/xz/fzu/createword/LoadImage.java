package xz.fzu.createword;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import Decoder.BASE64Encoder;

public class LoadImage {
	public static String loadImage(String imagePath) {
		InputStream in = null;
		byte data[] = null;
		try {
			in = new FileInputStream(imagePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 BASE64Encoder encoder = new  BASE64Encoder();
		 return encoder.encode(data);
	}
}
