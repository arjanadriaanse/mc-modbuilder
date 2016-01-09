package twintro.minecraft.modbuilder.editor.interfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EffectPanel extends JPanel {
	protected JComboBox effectComboBox;
	protected JSpinner durationSpinner;
	protected JSpinner amplifierSpinner;
	protected JButton removeButton;
	
	public FoodItemEditor main;
	public int id;
	
	public EffectPanel(FoodItemEditor main){
		this.main = main;
		
		setLayout(new GridLayout(0, 4, 5, 0));
		
		effectComboBox = new JComboBox();
		effectComboBox.setToolTipText("The effect type");
		effectComboBox.setModel(new DefaultComboBoxModel(new String[] {"Effect", "Speed", "Slowness", "Haste", "Mining Fatigue", "Instant Health",
				"Instant Damage", "Jump Boost", "Nausea", "Regeneration", "Resistance", "Fire Resistance", "Water Breathing", "Invisibility",
				"Blindness", "Night Vision", "Hunger", "Weakness", "Poison", "Wither", "Health Boost", "Absorption", "Saturation"}));
		add(effectComboBox);
		
		durationSpinner = new JSpinner();
		durationSpinner.setToolTipText("<html>The duration of the effect in ticks<br>"
				+ "One second is equal to twenty ticks</html>");
		durationSpinner.setModel(new SpinnerNumberModel(new Integer(20), new Integer(0), null, new Integer(20)));
		add(durationSpinner);
		
		amplifierSpinner = new JSpinner();
		amplifierSpinner.setToolTipText("<html>The amplifier for the effect<br>"
				+ "An amplifier of zero means level one, an amplifier of one means level two, this pattern continues</html>");
		amplifierSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		add(amplifierSpinner);
		
		removeButton = new JButton("Remove");
		removeButton.setToolTipText("Remove this effect");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(removeButton);
	}
	
	public EffectPanel(FoodItemEditor main, Integer[] effect){
		this(main);
		
		if (effect.length == 3){
			effectComboBox.setSelectedIndex(effect[0]);
			durationSpinner.setValue(effect[1]);
			amplifierSpinner.setValue(effect[2]);
		}
	}
	
	protected void dispose(){
		main.removeEffect(id);
	}
}