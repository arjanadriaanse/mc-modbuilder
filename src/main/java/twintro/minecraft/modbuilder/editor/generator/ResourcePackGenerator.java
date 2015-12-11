package twintro.minecraft.modbuilder.editor.generator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import twintro.minecraft.modbuilder.editor.Editor;

public class ResourcePackGenerator {
	public static final String resourcePackFolderDir = 
			"C:/Users/M.P/AppData/Roaming/.minecraft/resourcepacks/" + Editor.modName + "/";
	public static final String outputDir = "C:/Users/M.P/Desktop/" + Editor.modName + ".zip";
	
	public static void createResourcePack(){
		//Create assets/modid/lang/en_US.lang
		//Create pack.mcmeta
	}
	
	public static void createFile(Object obj, String dir) throws IOException{
		File file = new File(resourcePackFolderDir + dir);
		if (!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileWriter writer = new FileWriter(file);
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		gson.toJson(obj, writer);
		
		writer.close();
	}
	
	public static void generate() throws IOException{
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputDir));
		
		addFile(zos, new File(resourcePackFolderDir));
		
		zos.close();
	}
	
	private static void addFile(ZipOutputStream zos, File file) throws IOException{
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
		}
		finally{
			if (stream != null){
				stream.close();
			}
		}
	}
}