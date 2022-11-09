package com.example.api.utils;

import java.sql.Blob;

public class UtilsData {
	public static byte[] returnBlobData(Blob blob) throws Exception {
		return blob.getBytes(0, (int) blob.length());
	}
}
