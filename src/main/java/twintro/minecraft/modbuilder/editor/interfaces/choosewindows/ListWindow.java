package twintro.minecraft.modbuilder.editor.interfaces.choosewindows;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.IconDialog;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.ImageListCellRenderer;
import twintro.minecraft.modbuilder.editor.interfaces.helperclasses.ListPanel;
import twintro.minecraft.modbuilder.editor.resources.MaterialResources;

public class ListWindow extends IconDialog {
	private JPanel panel;
	private JList list;
	private String[] values;
	private ObjectRunnable runnable;
	
	private ListWindow(JPanel panel, ObjectRunnable runnable){
		this.panel = panel;
		this.runnable = runnable;

		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 650, 300);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Choose Material");
		
		this.panel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(this.panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.panel.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setVisibleRowCount(0);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	}
	
	public ListWindow(Map<String, ImageIcon> elements, ObjectRunnable runnable){
		this(new ListPanel(), runnable);
		((ListPanel)panel).elements = elements;
		
		list.setFixedCellWidth(125);
		list.setCellRenderer(new ImageListCellRenderer((ListPanel)panel));
		list.setModel(new AbstractListModel() {
			@Override
			public int getSize() {
				return ((ListPanel)panel).elements.size();
			}
			@Override
			public Object getElementAt(int index) {
				return ((String) ((ListPanel)panel).elements.keySet().toArray()[index]).replaceAll("_", " ");
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2){
					String value = ((String) ((JList) e.getSource()).getSelectedValue()).replaceAll(" ", "_");
					choose("modbuilder:" + value);
				}
			}
		});
		
		setVisible(true);
	}
	
	public ListWindow(String[] elements, ObjectRunnable runnable){
		this(new JPanel(), runnable);
		values = elements;

		list.setFixedCellWidth(200);
		list.setModel(new AbstractListModel() {
			@Override
			public int getSize() {
				return values.length;
			}
			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2){
					String value = (String) ((JList) e.getSource()).getSelectedValue();
					choose(MaterialResources.getId(value));
				}
			}
		});
		
		setVisible(true);
	}
	
	private void choose(String value){
		runnable.run(value);
		dispose();
	}
}
