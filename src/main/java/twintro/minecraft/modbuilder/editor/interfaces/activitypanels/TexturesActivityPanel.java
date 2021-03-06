package twintro.minecraft.modbuilder.editor.interfaces.activitypanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twintro.minecraft.modbuilder.editor.generator.ResourcePackIO;
import twintro.minecraft.modbuilder.editor.interfaces.choosewindows.ObjectRunnable;
import twintro.minecraft.modbuilder.editor.interfaces.editors.OldTextureEditor;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.ActivityButton;
import twintro.minecraft.modbuilder.editor.resources.TextureObject;

public class TexturesActivityPanel extends ActivityPanel {
	OldTextureEditor editor;
	//TextureEditor editor;
	
	private final ObjectRunnable runnable = new ObjectRunnable() {
		@Override
		public void run(Object obj) {
			saveImage((TextureObject) obj);
		}
	};
	
	public TexturesActivityPanel(String header, String button) {
		super(header, button);
		editor = new OldTextureEditor(runnable);
		//editor = new TextureEditor();
	}

	@Override
	protected void add() {
		String name = nameDialog("Texture");
		if (isValidName(name)){
			name = name.replaceAll(" ", "_");
			editor.open(name, ResourcePackIO.toBufferedImage(new ImageIcon().getImage(), 16, 16));
		}
	}
	
	private void importImage(){
		editor.loadImage();
	}
	
	@Override
	protected void edit() {
		String value = ((String) list.getSelectedValue());
		editor.open(value.replaceAll(" ", "_"), ResourcePackIO.toBufferedImage(ResourcePackIO.resizeImage(elements.get(value), 16, 16).getImage()));
	}
	
	@Override
	protected void delete() {
		String value = (String) list.getSelectedValue();
		int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + value + ".png?\r\n"
				+ "References to this object will not be updated, which might cause problems.", 
				"Warning", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION){
			removeElement(value);
			value = value.replaceAll(" ", "_");
			
			ResourcePackIO.deleteFile("assets/modbuilder/textures/" + value + ".png");
		}
	}
	
	@Override
	public String updateList(){
		elements.clear();
		File folder = new File(ResourcePackIO.getURL("assets/modbuilder/textures/"));
		if (folder.exists()){
			for (File file : folder.listFiles()){
				if (file.getAbsolutePath().endsWith(".png")){
					ImageIcon img = new ImageIcon(file.getAbsolutePath());
					String name = file.getName().substring(0, file.getName().length() - 4);
					addElement(name, img);
				}
			}
		}
		return null;
	}
	
	@Override
	protected void createButtonPanel(JPanel buttonPanel, String button) {
		JButton importButton = new ActivityButton("Import Image");
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				importImage();
			}
		});
		buttonPanel.add(importButton);
		super.createButtonPanel(buttonPanel, button);
	}
	
	private void saveImage(TextureObject texture){
		ResourcePackIO.addTexture(ResourcePackIO.toBufferedImage(texture.image.getImage()), 
				"assets/modbuilder/textures/" + texture.name + ".png");
		addElement(texture.name, texture.image);
	}
}
