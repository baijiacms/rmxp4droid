package com.rmxp4droid.cxysfx.core.decoder.cxydecoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ObjectRW {


	
	public Object ReadObjectFileToObject(String fileUrl) {
		ObjectInputStream oin = null;
		FileInputStream fin = null;
		try {
	

			fin = new FileInputStream(fileUrl);
			
			FECode fce=new FECode();

			oin = new ObjectInputStream(fce.decrypt(fin, "CJIUYTRFGHNMKIOL"));
			
			return oin.readObject();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("a game error");
		} finally {
			if (oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("a game error");
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("a game error");
				}
			}

		}
		return null;

	}

}
