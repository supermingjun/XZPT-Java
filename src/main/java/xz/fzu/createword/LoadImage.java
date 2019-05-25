package xz.fzu.createword;

import Decoder.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 	图片加载
 * @author LITM
 *@since 2019年5月22日
 */
public class LoadImage {
	public static String loadImage(String imagePath) {
		InputStream in = null;
		byte[] data = null;
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
