package com.example.api.utils;

import java.io.InputStream;
import java.sql.Blob;

public class UtilsData {
	public static byte[] returnBlobData(Blob blob) throws Exception {
		InputStream is = blob.getBinaryStream();
		byte[] bytes = blob.getBytes(0, (int) blob.length());
		is.close();

		return bytes;
	}
}
