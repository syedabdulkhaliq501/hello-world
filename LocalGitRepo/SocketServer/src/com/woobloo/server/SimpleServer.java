package com.woobloo.server;

import java.net.*;
import java.io.*;

public class SimpleServer {
	public static void main(String args[]) throws IOException {
		// Register service on port 1254//
		ServerSocket s = new ServerSocket(1254);
		// Wait and accept a connection//
		Socket s1 = s.accept();
		// Get a communication stream associated withthe socket//
		OutputStream s1out = s1.getOutputStream();
		DataOutputStream dos = new DataOutputStream(s1out);
		// Send a string//
		dos.writeUTF("Hi there server");
		// Close the connection, but not the server socket//
		dos.close();
		s1out.close();
		s1.close();
	}
}