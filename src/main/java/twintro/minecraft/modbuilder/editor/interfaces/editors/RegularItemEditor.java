package twintro.minecraft.modbuilder.editor.interfaces.editors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import twintro.minecraft.modbuilder.data.resources.TabResource;
import twintro.minecraft.modbuilder.data.resources.items.BaseItemResource;
import twintro.minecraft.modbuilder.data.resources.items.ItemResource;
import twintro.minecraft.modbuilder.data.resources.models.ItemModelResource;
import twintro.minecraft.modbuilder.data.resources.models.ItemModelResource.Display;
import twintro.minecraft.modbuilder.editor.Editor;
import twintro.minecraft.modbuilder.editor.interfaces.activitypanels.ItemsActivityPanel;
import twintro.minecraft.modbuilder.editor.interfaces.choosewindows.MaterialChooseWindow;
import twintro.minecraft.modbuilder.editor.interfaces.choosewindows.ObjectRunnable;
import twintro.minecraft.modbuilder.editor.interfaces.choosewindows.TextureChooseWindow;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.WindowClosingVerifierListener;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.WindowClosingVerifierUser;
import twintro.minecraft.modbuilder.editor.resources.ItemElement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class RegularItemEditor extends PropertiesEditor {
	protected JPanel texturePanel;
	protected JPanel containerPanel;
	protected JPanel containerSubPanel;
	protected JPanel burntimePanel;
	protected JPanel creativeTabsPanel;
	protected JPanel creativeTabsSubPanel;
	protected JLabel labelTexture;
	protected JLabel labelMaxStackSize;
	protected JLabel labelCreativeTabs;
	protected JLabel labelContainer;
	protected JLabel labelBurnTime;
	protected JLabel textureLabel;
	protected JLabel creativeTabsLabel;
	protected JLabel containerLabel;
	protected JButton textureChooseButton;
	protected JButton creativeTabsResetButton;
	protected JButton containerChooseButton;
	protected JSpinner maxStackSizeSpinner;
	protected JSpinner burntimeSpinner;
	protected JComboBox creativeTabsComboBox;
	protected JCheckBox containerCheckbox;
	protected JCheckBox burntimeCheckbox;

	private static final String textureTooltip = "The texture of the item";
	private static final String maxStackSizeTooltip = "The maximum amount of items in one stack";
	private static final String creativeTabsTooltip = "The tabs in the creative menu where the item can be found";
	private static final String containerTooltip = "<html>The item that will be left behind when you use this item in a crafing recipe<br>"
				+ "For example, when you craft a cake, you use milk buckets in the crafting recipe "
				+ "and when you grab the cake, you will get empty buckets back</html>";
	private static final String burntimeTooltip = "The amount of items that will get cooked when you use this item as a fuel source in a furnace";
	
	public RegularItemEditor(String name, ObjectRunnable runnable, ObjectRunnable closeHandler) {
		super(name, runnable, closeHandler);
		setBounds(100, 100, 400, 220);
		setTitle("Edit Item: " + this.name);
		saveButton.setText("Save Item");
		
		labelTexture = label("Texture", textureTooltip, labelPanel);
		textureChooseButton = button("Choose", textureTooltip);
		textureLabel = tooltipLabel("", textureTooltip);
		texturePanel = panel(textureLabel, textureChooseButton, interactionPanel);
		textureChooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textureChoose();
			}
		});
		
		if (!(this instanceof ToolItemEditor)){
			labelMaxStackSize = label("Maximum stack size", maxStackSizeTooltip, labelPanel);
			maxStackSizeSpinner = spinner(maxStackSizeTooltip, interactionPanel);
			maxStackSizeSpinner.setModel(new SpinnerNumberModel(64, 0, 64, 1));
		}
		
		labelCreativeTabs = label("Creative tabs", creativeTabsTooltip, labelPanel);
		creativeTabsComboBox = combobox(creativeTabsTooltip);
		creativeTabsResetButton = button("Reset", creativeTabsTooltip);
		creativeTabsLabel = tooltipLabel("", creativeTabsTooltip);
		creativeTabsSubPanel = panel(creativeTabsLabel, creativeTabsResetButton);
		creativeTabsPanel = panel(creativeTabsSubPanel, creativeTabsComboBox, interactionPanel);
		creativeTabsComboBox.setModel(new DefaultComboBoxModel(new String[] {"Add", "block", "decorations", "redstone", "transport", "misc", 
				"food", "tools", "combat", "brewing", "materials", "inventory"}));
		creativeTabsComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == e.SELECTED){
					if (e.getItem() != "Add"){
						creativeTabsChoose((String) e.getItem());
						((JComboBox) e.getSource()).setSelectedIndex(0);
					}
				}
			}
		});
		creativeTabsResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creativeTabsReset();
			}
		});
		
		labelContainer = label("Container", containerTooltip, labelPanel);
		containerCheckbox = checkbox("Use", containerTooltip);
		containerChooseButton = button("Choose", containerTooltip);
		containerLabel = tooltipLabel("", containerTooltip);
		containerSubPanel = panel(containerLabel, containerChooseButton);
		containerPanel = panel(containerSubPanel, containerCheckbox, interactionPanel);
		containerCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containerUse();
			}
		});
		containerChooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				containerChoose();
			}
		});
		containerUse();
		
		labelBurnTime = label("Burn time", burntimeTooltip, labelPanel);
		burntimeCheckbox = checkbox("Use", burntimeTooltip);
		burntimeSpinner = spinner(burntimeTooltip);
		burntimePanel = panel(burntimeSpinner, burntimeCheckbox, interactionPanel);
		burntimeCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				burntimeUse();
			}
		});
		burntimeSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		burntimeUse();
	}

	public RegularItemEditor(ItemElement item, ObjectRunnable runnable, ObjectRunnable closeHandler) {
		this(item.name, runnable, closeHandler);
		regularSetup(item);
		if (item.item.stacksize != null)
			maxStackSizeSpinner.setValue(item.item.stacksize);
		changed = false;
	}
	
	protected void regularSetup(ItemElement item){
		if (item.itemModel.textures.containsKey("layer0")) {
			textureLabel.setText(item.itemModel.textures.get("layer0"));
			setIconImage(Editor.getTextureList().get(item.itemModel.textures.get("layer0").split(":")[1]).getImage());
		}
		if (item.item.container != null){
			containerLabel.setText(item.item.container);
			containerCheckbox.setSelected(true);
			containerUse();
		}
		if (item.item.burntime != null){
			burntimeSpinner.setValue(item.item.burntime);
			burntimeCheckbox.setSelected(true);
			burntimeUse();
		}
		if (item.item.tabs != null)
			for (TabResource s : item.item.tabs)
				creativeTabsChoose(s.name());
	}

	@Override
	public boolean save() {
		if (textureLabel.getText().length() > 0){
			ItemResource base = new ItemResource();
			
			ItemElement item = writeItem(base);
			runnable.run(item);
			dispose();
		}
		else{
			int selected = JOptionPane.showConfirmDialog(this, "You haven't given the item a texture yet.", 
					"Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			if (selected == JOptionPane.OK_OPTION)
				return false;
		}
		return true;
	}
	
	protected ItemElement writeItem(BaseItemResource base){
		ItemElement item = new ItemElement();
		item.name = name;
		
		ItemModelResource model = new ItemModelResource();
		model.parent = "builtin/generated";
		model.textures = new HashMap<String, String>();
		model.textures.put("layer0", textureLabel.getText());
		model.display = Display.regular();
		item.itemModel = model;

		base.tabs = new HashSet<TabResource>();
		if (creativeTabsLabel.getText().length() > 0)
			for (String s : creativeTabsLabel.getText().split(","))
				base.tabs.add(TabResource.valueOf(s));
		base.model = "modbuilder:" + name;
		if (!(this instanceof ToolItemEditor))
			base.stacksize = (Integer) maxStackSizeSpinner.getValue();
		if (containerLabel.getText().length() > 0 && containerCheckbox.isSelected()) 
			base.container = containerLabel.getText();
		if (burntimeCheckbox.isSelected())
			base.burntime = (Integer) burntimeSpinner.getValue();
		item.item = base;
		
		return item;
	}

	private void burntimeUse() {
		boolean use = burntimeCheckbox.isSelected();
		burntimeSpinner.setEnabled(use);
		labelBurnTime.setEnabled(use);
	}

	private void containerChoose() {
		new MaterialChooseWindow(MaterialChooseWindow.ITEMS_AND_BLOCKS, new ObjectRunnable() {
			@Override
			public void run(Object obj) {
				change();
				containerLabel.setText((String) obj);
			}
		});
	}

	private void containerUse() {
		boolean use = containerCheckbox.isSelected();
		containerChooseButton.setEnabled(use);
		containerLabel.setEnabled(use);
		labelContainer.setEnabled(use);
	}

	private void creativeTabsReset() {
		change();
		creativeTabsLabel.setText("");
	}

	private void creativeTabsChoose(String tab) {
		change();
		if (creativeTabsLabel.getText().length() > 0) creativeTabsLabel.setText(creativeTabsLabel.getText() + ",");
		creativeTabsLabel.setText(creativeTabsLabel.getText() + tab);
	}

	private void textureChoose() {
		new TextureChooseWindow(new ObjectRunnable() {
			@Override
			public void run(Object obj) {
				change();
				String texture = (String) obj;
				textureLabel.setText(texture);
				setIconImage(Editor.getTextureList().get(texture.split(":")[1]).getImage());
			}
		});
	}
}
