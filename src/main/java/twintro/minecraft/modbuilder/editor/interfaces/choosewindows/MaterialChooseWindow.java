package twintro.minecraft.modbuilder.editor.interfaces.choosewindows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import twintro.minecraft.modbuilder.editor.Editor;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.IconDialog;
import twintro.minecraft.modbuilder.editor.resources.MaterialResources;

public class MaterialChooseWindow extends IconDialog {
	public static final int ITEMS_ONLY = 0;
	public static final int BLOCKS_ONLY = 1;
	public static final int ITEMS_BLOCKS = 2;
	public static final int ITEMS_NONE = 3;
	public static final int BLOCKS_NONE = 4;
	public static final int ITEMS_BLOCKS_NONE = 5;
	public static final int ITEMS_ONLY_METALESS = 6;
	public static final int BLOCKS_ONLY_METALESS = 7;
	public static final int ITEMS_BLOCKS_METALESS = 8;
	public static final int ITEMS_NONE_METALESS = 9;
	public static final int BLOCKS_NONE_METALESS = 10;
	public static final int ITEMS_BLOCKS_NONE_METALESS = 11;

	private static final String customItemsTooltip = "Choose an item made by you.";
	private static final String customBlocksTooltip = "Choose a block made by you.";
	private static final String vanillaItemsTooltip = "Choose an item from unmodded minecraft.";
	private static final String vanillaBlocksTooltip= "Choose a block from unmodded minecraft.";
	private static final String otherTooltip = "<html>Type in the name of any block or item.<br>"
			+ "You have to use its id, for example 'minecraft:stone' or 'minecraft:diamond_sword'.<br>"
			+ "Items made by you use 'modbuilder:[name]' as id format, where [name] is the name of your block or item.</html>";
	private static final String noneTooltip = "Use no block or item";
	
	private ObjectRunnable runnable;
	private JPanel mainPanel;
	private boolean meta;
	
	public MaterialChooseWindow(int type, ObjectRunnable runnable){
		this.runnable = runnable;

		boolean items = type % 3 != 1;
		boolean blocks = type % 3 != 0;
		boolean none = type / 3 % 2 == 1;
		meta = type < 6;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Choose Material");
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new GridLayout(0, 2, 5, 5));
		
		if (items){
			JButton customItemButton = new JButton("Custom Item");
			customItemButton.setToolTipText(customItemsTooltip);
			mainPanel.add(customItemButton);
			customItemButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					customItem();
				}
			});
			
			JButton itemButton = new JButton("Vanilla Item");
			itemButton.setToolTipText(vanillaItemsTooltip);
			mainPanel.add(itemButton);
			itemButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vanillaItem();
				}
			});
		}
		
		if (blocks){
			JButton customBlockButton = new JButton("Custom Block");
			customBlockButton.setToolTipText(customBlocksTooltip);
			mainPanel.add(customBlockButton);
			customBlockButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					customBlock();
				}
			});
			
			JButton blockButton = new JButton("Vanilla Block");
			blockButton.setToolTipText(vanillaBlocksTooltip);
			mainPanel.add(blockButton);
			blockButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vanillaBlock();
				}
			});
		}
		
		JButton otherButton = new JButton("Other");
		otherButton.setToolTipText(otherTooltip);
		mainPanel.add(otherButton);
		otherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				other();
			}
		});
		
		if (none){
			JButton noneButton = new JButton("None");
			noneButton.setToolTipText(noneTooltip);
			mainPanel.add(noneButton);
			noneButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					none();
				}
			});
		}
		
		addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowClosing(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {
				setBounds(100, 100, 300, (int) (mainPanel.getSize().getHeight() + getSize().getHeight() - getContentPane().getSize().getHeight()));
			}
		});
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
	
	private void customItem(){
		new ListWindow(Editor.getItemList(), new ObjectRunnable(){
			@Override
			public void run(Object obj){
				choose((String) obj);
			}
		});
	}
	
	private void customBlock(){
		new ListWindow(Editor.getBlockList(), new ObjectRunnable(){
			@Override
			public void run(Object obj){
				choose((String) obj);
			}
		});
	}
	
	private void vanillaItem(){
		String[] list;
		if (meta) list = MaterialResources.vanillaItems;
		else list = MaterialResources.getVanillaItemsMetaless();
		new ListWindow(list, new ObjectRunnable(){
			@Override
			public void run(Object obj){
				choose((String) obj);
			}
		});
	}
	
	private void vanillaBlock(){
		String[] list;
		if (meta) list = MaterialResources.vanillaBlocks;
		else list = MaterialResources.getVanillaBlocksMetaless();
		new ListWindow(list, new ObjectRunnable(){
			@Override
			public void run(Object obj){
				choose((String) obj);
			}
		});
	}
	
	private void other(){
		String material = JOptionPane.showInputDialog("Material Name:");
		if (material != null){
			choose(material);
		}
	}
	
	private void none(){
		choose("");
	}
	
	private void choose(String value){
		if (!meta) value = value.replaceAll("#0", "");
		runnable.run(value);
		dispose();
	}
}
