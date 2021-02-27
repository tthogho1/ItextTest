import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSmartCopy;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

public class PDFResize {

	public static void main(String args[]) {
		// log.debug("#pdfFlatting: done. pdf size=[" + pdf.length + "]");
		// フラット化済
		byte[] pdf = null;

		File dir = new File("C:\\PROJECT\\AWCP0051\\DATA\\pdf-tpl\\"); // Fileクラスのオブジェクトを生成し対象のディレクトリを指定
		File[] list = dir.listFiles(); // listFilesを使用してファイル一覧を取得

		for (File f : list) {
			try {
				if (f.getName().endsWith("pdf")) {
					resizePdf("C:\\PROJECT\\AWCP0051\\DATA\\pdf-tpl\\" , f.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void resizePdf(String inputDir,String fileName) throws Exception {

		byte[] result = null;

		String outdir = "c:\\temp\\test\\";

		PdfReader reader = new PdfReader(inputDir + fileName);
		PdfStamper stamper = new PdfStamper(reader,
				new FileOutputStream(outdir + fileName),PdfWriter.VERSION_1_7);
        stamper.getWriter().setCompressionLevel(9);
        int total = reader.getNumberOfPages() + 1;
        for (int i = 1;i< total ;i++) {
        	reader.setPageContent(i, reader.getPageContent(i));
        }
		stamper.setFullCompression();
        stamper.close();


	}

}
