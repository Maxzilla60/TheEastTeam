package mkyong.onekey;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by 11500555 on 17/03/2017.
 */
public class GenerateSymmetricKey {
	private SecretKeySpec secretKey;

	public GenerateSymmetricKey(int length, String algorithm) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecureRandom secureRandom = new SecureRandom();
		byte [] key = new byte [length];
		secureRandom.nextBytes(key);
		this.secretKey = new SecretKeySpec(key, algorithm);
	}
	
	public SecretKeySpec getKey(){
		return this.secretKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		GenerateSymmetricKey genSK = new GenerateSymmetricKey(16, "AES");
		genSK.writeToFile("src/main/Files/OneKey/secretKey", genSK.getKey().getEncoded());
	}
}