package cn.crap.utils;

import cn.crap.beans.Config;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Html2Pdf {
	/**
	 * 如果interFaceId不为空则单个下载，否则按模块下载
	 * @throws Exception
	 */
	public static File createPdf(HttpServletRequest request, String domain, String interFaceId , String moduleId, String secretKey) throws Exception {
		Document document = null;
		PdfWriter writer = null;
        File file = null;
		try {
			String destDir = Tools.getCanWriteDestDir() + "resources/download";
			destDir += "/pdf_" + System.currentTimeMillis() + Tools.getChar(20) + ".pdf";

            document = new Document();
            file = new File(destDir);
            boolean mkdirs = file.getParentFile().mkdirs();
            boolean newFile = file.createNewFile();
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
			
			InputStream pdfText = null;
			try{
				pdfText = HttpPostGet.getInputStream(domain+ "/visitor/interface/detail/pdf.do?id=" + interFaceId + "&moduleId="+moduleId+"&secretKey="+secretKey);
			}catch(Exception e){
				e.printStackTrace();
			}

			if(pdfText == null){
			    pdfText = HttpPostGet.getInputStream(domain+"/result.do?result=" +
						URLEncoder.encode("地址有误，生成pdf失败，请确认配置文件config.properties中的网站域名配置是否正确！"
                                + "当前配置的域名为：" + domain,"utf-8"));
			}

			XMLWorkerHelper.getInstance().parseXHtml(writer, document, pdfText,
					Charset.forName("UTF-8"), new ChinaFont());

//			return destDir;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (document != null) {
			    if (document.isOpen()) {
                    document.close();
                }
			}
			if (writer != null){
				writer.close();
			}
		}
		return file;
	}

}
