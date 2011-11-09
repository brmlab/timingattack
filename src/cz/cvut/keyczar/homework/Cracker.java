/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.keyczar.homework;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author pborky
 */
public class Cracker {

	public static final String MESSAGE = "Hello world!";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Runnable messageVerifier;
        if (args.length < 4) {
            System.out.println("Usage:");
            System.out.println("cracker <host> <port> <message> <limitIterations> [<signatureByte0> [<signatureByte1> [ ... <signatureByte24>]]]");
            System.out.println("\t<host>, <port> - u know");
            System.out.println("\t<message> - what to send");
            System.out.println("\t<limitIterations> - max. number of iterations per one byte");
            System.out.println("\t<signatureByteX> - is hex of the signature`s byte");
            System.out.println("\tnote: you must provide at least 5 bytes of signature");
            System.exit(-1);
        }
        String host = args[0]; //
        int port = Integer.parseInt(args[1]); //
        String msg = args[2]; // message
        int iMax = Integer.parseInt(args[3]); // max. iterations

        byte[] sig = null;

        if (args.length>3) {
            sig = new byte[args.length-4];
            for (int i = 4; i < args.length; i++) {
                sig[i - 4] = (byte)Integer.parseInt(args[i],16);
            }
        }
        char[] m = msg.isEmpty()?MESSAGE.toCharArray():msg.toCharArray();
        messageVerifier = createMessageGenerator(host, port, iMax, sig, m);
        messageVerifier.run();
    }


    private static Runnable createMessageGenerator(String host, int port, int iMax, byte[] signature, char[] msg) {
		try {
            Socket sock;
            sock = new Socket(host, port);
            System.out.println("Connected");

            InputStream is = sock.getInputStream();
            OutputStream os = sock.getOutputStream();

			return new HmacCracker(os, is, iMax, signature, msg);
		}
		catch (Throwable t) {
			System.err.println("Can't load Generator: " + t.getMessage());
            throw new RuntimeException(t);
		}
    }

}
