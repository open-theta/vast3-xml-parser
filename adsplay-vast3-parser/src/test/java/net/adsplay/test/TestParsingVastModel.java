package net.adsplay.test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.adsplay.util.HttpGetUtil;
import net.adsplay.util.XmlTools;
import net.adsplay.vast.model.VASTModel;
import net.adsplay.vast.model.VmapModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestParsingVastModel {
	
	
	void parseVASTModel(String url) throws IOException{
		String xml = HttpGetUtil.get(url);
		//System.out.println(xml);
				
		Document mainDoc = XmlTools.stringToDocument(xml);
		VASTModel vastModel = new VASTModel(mainDoc);
		
		Assert.assertEquals(true, vastModel.getDuration() != null);
		Assert.assertEquals(true, vastModel.getImpressions().size() > 0);		
		Assert.assertEquals(true, vastModel.getMediaFiles().size()>0);
		
		System.out.println(vastModel.getDuration());
		System.out.println(vastModel.getImpressions());
		System.out.println(vastModel.getMediaFiles().get(0));
		System.out.println(vastModel.getSkipOffset());
	}
	
	@Test
	public void testParseVmapXml() throws IOException {
		String url = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&cmsid=496&vid=short_onecue&correlator=";
		String xml = HttpGetUtil.get(url);
		Document mainDoc = XmlTools.stringToDocument(xml);
		VmapModel vmapModel = new VmapModel(mainDoc);
		List<VASTModel> vastModels = vmapModel.getVASTModels();
		
		Assert.assertEquals(true, vastModels.size() > 0);
		
		for (VASTModel vastModel : vastModels) {
			Assert.assertEquals(true, vastModel.getImpressions().size() > 0);	
			System.out.println(vastModel.getImpressions().get(0));
		}		
	}
	
	@Test
	public void testAdsPlay() throws IOException{		
		String url = "https://d5.adsplay.net/delivery/zone/1001?placement=333";
		parseVASTModel(url);
	}	
	
	@Test
	public void testGoogleDFP() throws IOException{		
		String url = "https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&vad_type=linear&vpos=midroll&pod=2&mridx=1&ppos=1&min_ad_duration=0&max_ad_duration=30000&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpod&url=https://developers.google.com/interactive-media-ads/docs/sdks/android/tags&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0";
		parseVASTModel(url);
	}
	
	
}
