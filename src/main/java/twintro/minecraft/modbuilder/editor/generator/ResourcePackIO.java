package twintro.minecraft.modbuilder.editor.generator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResourcePackIO {
	private static String resourcePackFolderDir;
	
	public static void setResourcePackFolder(String dir){
		resourcePackFolderDir = dir;
	}
	
	public static String getURL(String dir){
		return resourcePackFolderDir + dir;
	}
	
	public static void addTexture(Image img, String dir){
		try {
		    RenderedImage bi = (RenderedImage) img;
		    File outputfile = new File(resourcePackFolderDir + dir);
		    if (!outputfile.getParentFile().exists()){
				outputfile.getParentFile().mkdirs();
			}
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void deleteFile(String dir){
		File file = new File(resourcePackFolderDir + dir);
		if (file.exists()) file.delete();
	}
	
	public static void createFile(Object obj, String dir){
		File file = new File(resourcePackFolderDir + dir);
		createFile(obj, file);
	}
	
	public static void createFile(Object obj, File file){
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		
		try{
			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
				GsonBuilder builder = new GsonBuilder();
				builder.setPrettyPrinting();
				Gson gson = builder.create();
				gson.toJson(obj, writer);
			} finally {
				if (writer != null) writer.close();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void export(String dir){
		try{
			ZipOutputStream zos = null;
			try{
				zos = new ZipOutputStream(new FileOutputStream(dir));
				addFile(zos, new File(resourcePackFolderDir));
			} finally {
				zos.close();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private static void addFile(ZipOutputStream zos, File file){
		try{
			BufferedInputStream stream = null;
			try{
				String name = file.getPath().replace("\\", "/").replace(resourcePackFolderDir, "");
				
				if (file.isDirectory()){
					if (!name.isEmpty()){
						if (!name.endsWith("/"))
							name += "/";
						ZipEntry entry = new ZipEntry(name);
						entry.setTime(file.lastModified());
						zos.putNextEntry(entry);
						zos.closeEntry();
					}
					for (File nestedFile : file.listFiles()){
						addFile(zos, nestedFile);
					}
					return;
				}
				
				ZipEntry entry = new ZipEntry(name);
				entry.setTime(file.lastModified());
				zos.putNextEntry(entry);
				stream = new BufferedInputStream(new FileInputStream(file));
				
				byte[] buffer = new byte[1024];
				while (true){
					int count = stream.read(buffer);
					if (count == -1)
						break;
					zos.write(buffer, 0, count);
				}
				zos.closeEntry();
			} finally {
				if (stream != null) stream.close();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static ImageIcon getImage(String name){
		return resizeImage(new ImageIcon(ResourcePackIO.getURL("assets/modbuilder/textures/" + name + ".png")), 64, 64);
	}
	
	public static ImageIcon resizeImage(ImageIcon icon, int width, int height){
		Image img = icon.getImage();
		BufferedImage bi = toBufferedImage(img, width, height);
		return new ImageIcon(bi);
	}
	
	public static BufferedImage toBufferedImage(Image img){
		return toBufferedImage(img, img.getWidth(null), img.getHeight(null));
	}
	
	public static BufferedImage toBufferedImage(Image img, int width, int height){
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, width, height, null);
		return bi;
	}
}
