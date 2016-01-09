package twintro.minecraft.modbuilder.editor.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import scala.swing.event.WindowClosed;
import twintro.minecraft.modbuilder.data.resources.recipes.ItemStackResource;
import twintro.minecraft.modbuilder.data.resources.recipes.RecipeType;
import twintro.minecraft.modbuilder.data.resources.recipes.ShapelessRecipe;
import twintro.minecraft.modbuilder.editor.Editor;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.WindowClosingVerifierListener;
import twintro.minecraft.modbuilder.editor.resources.RecipeElement;

import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JFrame;

public class ShapelessRecipeEditor extends RecipeEditor {

	private JPanel contentPane;
	private String name;
	private RecipesActivityPanel main;
	private ItemStackButton[] buttons;
	
	/**
	 * Launch the application.
	 */
		private static List<String> stringSetToList(Set<String> set){
			List<String> output = new ArrayList<String>();
			for (String s : set){
				output.add(s);
			}
			return output;
		}
	
		public ShapelessRecipeEditor(String nameNew, RecipesActivityPanel parent, Set<String> items, Set<String> blocks) {
		this.name = nameNew;
		this.main = parent;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowClosingVerifierListener());
		setBounds(100, 100, 506, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		this.setTitle("Edit recipe:" + name);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 250, 250);
		panel_3.add(panel);
		panel.setLayout(new GridLayout(3, 3, 4, 4));
		
		buttons = new ItemStackButton[10];
		for (int i = 0; i<9; i++){
			buttons[i] = new ItemStackButton("", items, blocks);
			panel.add(buttons[i]);
		}
		
		buttons[9] = new ItemStackButton("",items, blocks);
		buttons[9].setIsProduct(true);
		buttons[9].setBounds(365, 115, 80, 80);
		
		panel_3.add(buttons[9]);
		
		JLabel label = new JLabel("\u2192");
		label.setFont(new Font("Times New Roman", Font.BOLD, 69));
		label.setBounds(288, 105, 80, 93);
		panel_3.add(label);
		buttons[9].setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblCreateTheShaped = new JLabel("Create the recipe, the shape does not matter");
		contentPane.add(lblCreateTheShaped, BorderLayout.NORTH);
		

		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameNew2 = JOptionPane.showInputDialog("Item name:");
				RecipeEditor temp = main.openEditors.get(name);
				main.openEditors.remove(name);
				name = nameNew2;
				main.openEditors.put(name, temp);
				setTitle("Edit structure: " + name);
			}
		});
		panel_2.add(btnRename);
		
		JButton btnSaveItem = new JButton("Save recipe");
		panel_2.add(btnSaveItem);
		btnSaveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveRecipe();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel();
			}
		});
		setVisible(true);
	}
		
	public ShapelessRecipeEditor(String value, RecipesActivityPanel recipesActivityPanel, RecipeElement recipe,
				Set<String> editorItems, Set<String> editorBlocks) {
			this(value, recipesActivityPanel, editorItems, editorBlocks);
			ShapelessRecipe shplsRcpy = (ShapelessRecipe)recipe.recipe;
			for (int i = 0; i < 10; i++){
				buttons[i].item = new ItemStackResource();
			}
			for(int i = 0; i < shplsRcpy.input.size();i++){
				buttons[i].item = shplsRcpy.input.get(i);
				if (shplsRcpy.input.get(i).item == "" || shplsRcpy.input.get(i).item == null)
					buttons[i].setText(shplsRcpy.input.get(i).amount + " "+shplsRcpy.input.get(i).block);
				else
					buttons[i].setText(shplsRcpy.input.get(i).amount + " "+shplsRcpy.input.get(i).item);
			}
			buttons[9].item = shplsRcpy.output;
			if (shplsRcpy.output.item == "" || shplsRcpy.output.item == null) 
			
				buttons[9].setText(shplsRcpy.output.amount + " "+shplsRcpy.output.block);
			else
				buttons[9].setText(shplsRcpy.output.amount + " "+shplsRcpy.output.item);

			this.name = value;
		}

	public void itemChanged(String old, String newName){
		for(int i=0; i <10; i++)
			if (buttons[i].item.item == old) buttons[i].item.item = newName;
	}
	
	public void blockChanged(String old, String newName){
		for (int i=0; 9<10; i++)
			if (buttons[i].item.block == old) buttons[i].item.block = newName;
	}

	protected void cancel() {
		WindowClosingVerifierListener.close(this);

	}
	
	@Override
	public void dispose() {
		main.closeEditor(name);
		super.dispose();
	}

	protected void saveRecipe() {
		List<ItemStackResource> savableInput = new ArrayList<ItemStackResource>();
		ItemStackResource savableOutput = new ItemStackResource();
		for (int i = 0; i < 9; i++){
			if (buttons[i].getText() != ""){
				savableInput.add(buttons[i].item);
			}
		}
		savableOutput = buttons[9].item;
		ShapelessRecipe recipe = new ShapelessRecipe();
		recipe.type = RecipeType.shapeless;
		recipe.input = savableInput;
		recipe.output = savableOutput;
		RecipeElement recipeElement = new RecipeElement();
		recipeElement.recipe = recipe;
		recipeElement.name = this.name;
		main.addRecipe(recipeElement);
		//TODO send recipe to activity panel to save
	}
}