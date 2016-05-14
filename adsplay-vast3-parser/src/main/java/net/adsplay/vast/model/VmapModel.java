package net.adsplay.vast.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.adsplay.util.HttpGetUtil;
import net.adsplay.util.XmlTools;

public class VmapModel implements Serializable {
	
	public static String TAG = "VmapModel";

	private static final long serialVersionUID = -4060497826275236955L;

	String adBreakXPATH = "/AdBreak";

	private transient Document vmapDocument;

	public VmapModel(Document xmlDoc) {
		this.vmapDocument = xmlDoc;
	}

	public Document getVmapDocument() {
		return vmapDocument;
	}

	public List<VASTModel> getVASTModels() {
		List<VASTModel> models = new ArrayList<>(5);
		
		NodeList adbreakNodes = vmapDocument.getElementsByTagName("vmap:AdBreak");
		int l = adbreakNodes.getLength();
		for (int i = 0; i < l; i++) {
			Node node = adbreakNodes.item(i);			
			
			String timeOffset = XmlTools.getAttributeValue(node, "timeOffset");
			//String breakId = XmlTools.getAttributeValue(node, "breakId");
			
			NodeList childs = node.getChildNodes();
			for (int j = 0; j < childs.getLength(); j++) {
				Node item = childs.item(j);
				if(item.getNodeName().equalsIgnoreCase("vmap:AdSource")){
					String vastUrl = item.getTextContent().trim();
					String vastXml = HttpGetUtil.get(vastUrl);					
					VASTModel vastModel = new VASTModel(XmlTools.stringToDocument(vastXml),timeOffset);
					models.add(vastModel);
				}
			}
		}		
		return models;
	}

}
