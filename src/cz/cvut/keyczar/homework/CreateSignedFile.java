package cz.cvut.keyczar.homework;

import cz.cvut.keyczar.Signer;

import java.io.FileOutputStream;

public class CreateSignedFile {

	public static void main(String[] arguments) throws Throwable {
        if (arguments.length<2) {
            System.out.println("Usage:");
            System.out.println("caller <message> <keyDir> <outFile>");
            System.exit(-1);
        }
        String message = arguments[0];
        String keyDir = arguments[1];
        String outFile = arguments[2];
		Signer signer = new Signer(keyDir);
		byte[] sig = signer.sign(message.getBytes());
		FileOutputStream fos = new FileOutputStream(outFile);
        for (byte b: sig) {
            System.out.print(Integer.toHexString(b&0xff));
            System.out.print(' ');
        }
        System.out.println();

        fos.write(message.getBytes());
        fos.write(0);
		fos.write(sig);
	}

}
