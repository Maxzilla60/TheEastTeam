package com.mkyong.hybrid.decrypt;

import com.mkyong.writers.InputOutputController;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 11500555 on 23/03/2017.
 */
public class DecryptData {
	private Cipher cipher;

	public DecryptData(File encryptedFileReceived, File decryptedFile, SecretKeySpec secretKey, String algorithm) throws IOException, GeneralSecurityException {
		this.cipher = Cipher.getInstance(algorithm);
		decryptFile(InputOutputController.getFileInBytes(encryptedFileReceived), decryptedFile, secretKey);
	}
	
	public void decryptFile(byte[] input, File output, SecretKeySpec key) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		InputOutputController.writeToFile(output, this.cipher.doFinal(input));
    }
}
