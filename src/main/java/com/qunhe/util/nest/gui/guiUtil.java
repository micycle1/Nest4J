package com.qunhe.util.nest.gui;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Color;
import java.io.*;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.w3c.dom.svg.SVGDocument;
import com.qunhe.util.nest.data.NestPath;

public class guiUtil {


	/**
	 * Load a list of NestPath from file
	 * @return List<Nestpath>
	 * @throws DocumentException
	 */
	public static List<NestPath> transferSvgIntoPolygons() throws DocumentException {

		int increment_id=1;
		List<NestPath> nestPaths = new ArrayList<>();
		SAXReader reader = new SAXReader();
		Document document = reader.read("input/test2.xml");
		//Document document = reader.read("input/simple.xml");


		List<Element> elementList = document.getRootElement().elements();
		for (Element element : elementList) {
			if ("polygon".equals(element.getName())) {
				String datalist = element.attribute("points").getValue();
				NestPath polygon = new NestPath();
				for (String s : datalist.split(" ")) {
					s = s.trim();
					if (s.indexOf(",") == -1) {
						continue;
					}
					String[] value = s.split(",");
					double x = Double.parseDouble(value[0]);
					double y = Double.parseDouble(value[1]);
					polygon.add(x, y);
				}
				polygon.setId(increment_id++);
				nestPaths.add(polygon);
			} else if ("rect".equals(element.getName())) {
				double width = Double.parseDouble(element.attribute("width").getValue());
				double height = Double.parseDouble(element.attribute("height").getValue());
				double x = Double.parseDouble(element.attribute("x").getValue());
				double y = Double.parseDouble(element.attribute("y").getValue());
				NestPath rect = new NestPath();
				rect.add(x, y);
				rect.add(x + width, y);
				rect.add(x + width, y + height);
				rect.add(x, y + height);
				rect.setId(increment_id++);
				nestPaths.add(rect);
			}
		}
		return nestPaths;
	}


	static void showError(String msg, JFrame frame, JLabel label)
	{
		JOptionPane.showMessageDialog(frame,msg,"error",JOptionPane.ERROR_MESSAGE);
		label.setText(msg);
		label.setForeground(Color.RED);
	}

	static void setMessageLabel(String msg, JLabel label)
	{
		label.setText(msg);
		label.setForeground(Color.BLACK);
	}

	/**
	 * @param strings	List<String> corresponding to the elements to save in the SVG file with specified bin dimentions
	 * @param htmlfile	output file
	 * @param binwidth	width of the bin
	 * @param binheight	height of the bin
	 * @throws Exception
	 */
	public static void saveSvgFile(List<String> strings, String htmlfile, double binwidth, double binheight) throws Exception {
		File f = new File(htmlfile);

		if (!f.exists()) {
			f.createNewFile();
		}
		Writer writer = new FileWriter(f, false);
		writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
				"\n" +
				"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
				"\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
				" \n" +
				//"<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n" +
				//added for correct "AUTOZOOM"
				"<svg width=\"" + (binwidth) + "\" height=\"" + (binheight )+ "\" viewBox=\"0 0 " + (binwidth) +" " +  (binheight)+"\" version=\"1.1\"\n" +
				"xmlns=\"http://www.w3.org/2000/svg\">\n");
		for(String s : strings){
			writer.write(s);
		}
		writer.write("</svg>");
		writer.close();
	}
	
	/**
	 * @param strings	List<String> corresponding to the elements to put in the SVGDocument file with specified bin dimentions
	 * @param binwidth	width of the bin
	 * @param binheight	height of the bin
	 * @return	SVGDocument object
	 * @throws Exception
	 */
	public static SVGDocument CreateSvgFile(List<String> strings,  double binwidth, double binheight) throws Exception {

		StringBuilder DocumentTextBuilder = new StringBuilder();
		DocumentTextBuilder.append("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
				"\n" +
				"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
				"\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
				" \n" +
				//"<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n" +
				//added to correct "AUTOZOOM"
				"<svg width=\"" + binwidth + 10 + "\" height=\"" + binheight + 10 + "\" viewBox=\"0 0 " + binwidth + 10 +" " +  binheight +10+"\" version=\"1.1\"\n" +
				"xmlns=\"http://www.w3.org/2000/svg\">\n");


		for(String s : strings){
			DocumentTextBuilder.append(s);
		}
		DocumentTextBuilder.append("</svg>");

		SAXSVGDocumentFactory factory;
		String parser = XMLResourceDescriptor.getXMLParserClassName();
		factory = new SAXSVGDocumentFactory(parser);

		SVGDocument doc = factory.createSVGDocument(null, new StringReader(DocumentTextBuilder.toString()));
		return doc;
	}


	static void refresh(JFrame frame)//TODO Doesn't work
	{
		SwingUtilities.updateComponentTreeUI(frame);
		frame.invalidate();
		frame.validate();
		frame.repaint();
		//		 frame.setVisible(false);
		//		 frame.setVisible(true);

	}
	
	/**
	 * @param strings	List<String> corresponding to the elements to save in the SVG file without giving bin dimensions
	 * @param htmlfile	output file
	 * @throws Exception
	 */
	public static void saveSvgFile(List<String> strings, String htmlfile) throws Exception {
		File f = new File(htmlfile);
		if (!f.exists()) {
			f.createNewFile();
		}
		Writer writer = new FileWriter(f, false);
		writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n" +
				"\n" +
				"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
				"\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
				" \n" +
				"<svg width=\"100%\" height=\"100%\" version=\"1.1\"\n" +
				"xmlns=\"http://www.w3.org/2000/svg\">\n");
		for(String s : strings){
			writer.write(s);
		}
		writer.write("</svg>");
		writer.close();
	}




}
