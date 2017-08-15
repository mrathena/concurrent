package com.mrathena.concurrent.basic;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO {

	public static void nioRead(String file) {
		try {
			FileInputStream in = new FileInputStream(file);
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			channel.read(buffer);
			byte[] b = buffer.array();
			System.out.println(new String(b));
			channel.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}