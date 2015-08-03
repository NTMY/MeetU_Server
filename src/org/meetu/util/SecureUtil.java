package org.meetu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecureUtil {
	private static BASE64Encoder base64Encoder = new BASE64Encoder();

	private static BASE64Decoder base64Decoder = new BASE64Decoder();

	/**
	 * @param input
	 *            string
	 * @return output string
	 */
	public static String BASE64Encode(String in) {
		String out = null;
		byte[] bytes = in.getBytes();
		out = base64Encoder.encode(bytes);
		return out;
	}

	/**
	 * @param bytes
	 *            bytes
	 * @return output string
	 */
	public static String BASE64Encode(byte[] bytes) {
		String out = null;
		out = base64Encoder.encode(bytes);
		return out;
	}

	/**
	 * @param input
	 *            string
	 * @return output string
	 * @throws IOException
	 */
	public static String BASE64Decode(String in) throws IOException {
		String out = null;
		byte[] bytes = base64Decoder.decodeBuffer(in);
		out = new String(bytes);
		return out;
	}

	/**
	 * @param input
	 *            string
	 * @return output string
	 * @throws IOException
	 */
	public static byte[] BASE64DecodeBytes(String in) throws IOException {
		byte[] bytes = base64Decoder.decodeBuffer(in);
		return bytes;
	}

	/**
	 * 对象转byte[]
	 * */
	public static byte[] ObjectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * byte[]转对象
	 * */
	public static Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}

	/***************************************************************************
	 * getters and setters
	 **************************************************************************/
	public BASE64Encoder getBase64Encoder() {
		return base64Encoder;
	}

	public void setBase64Encoder(BASE64Encoder base64Encoder) {
		this.base64Encoder = base64Encoder;
	}

	public BASE64Decoder getBase64Decoder() {
		return base64Decoder;
	}

	public void setBase64Decoder(BASE64Decoder base64Decoder) {
		this.base64Decoder = base64Decoder;
	}
}
