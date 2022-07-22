package com.publish.cxyencoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectRW {

	public void saveObjectToFile(String outFile, Object object) {
		FileOutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			os = new FileOutputStream(outFile);
			oos = new ObjectOutputStream(os);
			oos.writeObject(object);
			oos.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public Object ReadObjectFileToObject(String fileUrl) {
		ObjectInputStream oin = null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(fileUrl);
			oin = new ObjectInputStream(fin);

			return oin.readObject();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return null;

	}

	public Object ReadInStreamObjectFileToObject(InputStream ins) {
		ObjectInputStream oin = null;

		try {

			oin = new ObjectInputStream(ins);

			return oin.readObject();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
