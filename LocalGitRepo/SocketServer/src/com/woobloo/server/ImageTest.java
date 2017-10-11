package com.woobloo.server;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * @author mkyong
 *
 */
public class ImageTest {

	private static final int IMG_WIDTH = 200;
	private static final int IMG_HEIGHT = 200;

	public static void listFilesForFolder(final File folder) {
		String path = "";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				File f = new File("C:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\ConvertedIMAGES\\"
						+ fileEntry.getName());
				if (!f.exists()) {
					path = f.getAbsolutePath();
					f.mkdirs();
				}
				listFilesForFolder(fileEntry);
			} else {
				try {

					/*
					 * File file=new File(
					 * "C:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\IMAGES"
					 * ); boolean exists = file.exists(); // Check if the file
					 * exists boolean isDirectory = file.isDirectory();
					 * if(isDirectory){ file. }
					 */
					// BufferedImage originalImage = ImageIO.read(new
					// File("c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avata.jpg"));

					BufferedImage originalImage = ImageIO.read(new File(fileEntry.getAbsolutePath()));
					int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

					BufferedImage resizeImageJpg = resizeImage(originalImage, type);
					ImageIO.write(resizeImageJpg, "jpg", new File(fileEntry.getAbsolutePath() + "jpg.jpg"));

					BufferedImage resizeImagePng = resizeImage(originalImage, type);
					ImageIO.write(resizeImagePng, "png", new File(fileEntry.getAbsolutePath() + "png.jpg"));

					BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
					ImageIO.write(resizeImageHintJpg, "jpg", new File(fileEntry.getAbsolutePath() + "jpghint.jpg"));

					BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
					ImageIO.write(resizeImageHintPng, "png", new File(fileEntry.getAbsolutePath() + "pnghint.jpg"));

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) {/*

		File f = new File("C:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\ConvertedIMAGES");
		if (!f.exists()) {
			f.mkdirs();
		}

		final File folder = new File("C:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\IMAGES");
		listFilesForFolder(folder);
		
		 * try{
		 * 
		 * File file=new File(
		 * "C:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\IMAGES"
		 * ); boolean exists = file.exists(); // Check if the file exists
		 * boolean isDirectory = file.isDirectory(); if(isDirectory){ file. }
		 * BufferedImage originalImage = ImageIO.read(new File(
		 * "c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avata.jpg"
		 * )); int type = originalImage.getType() == 0?
		 * BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		 * 
		 * BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		 * ImageIO.write(resizeImageJpg, "jpg", new File(
		 * "c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avatajpg.jpg"
		 * ));
		 * 
		 * BufferedImage resizeImagePng = resizeImage(originalImage, type);
		 * ImageIO.write(resizeImagePng, "png", new File(
		 * "c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avatapng.jpg"
		 * ));
		 * 
		 * BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage,
		 * type); ImageIO.write(resizeImageHintJpg, "jpg", new File(
		 * "c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avatajpghint.jpg"
		 * ));
		 * 
		 * BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage,
		 * type); ImageIO.write(resizeImageHintPng, "png", new File(
		 * "c:\\Users\\skhaliq\\Spark\\user\\skhaliq@jnet176\\downloads\\avatapnghint.jpg"
		 * ));
		 * 
		 * }catch(IOException e){ System.out.println(e.getMessage()); }
		 

	*/
		
		
	System.out.println("djfhjdhfjsh");
	System.out.println("djfhjdhfjsh 222222222");
		System.out.println("djfhjdhfjsh 222222222333333333333");
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}
}
