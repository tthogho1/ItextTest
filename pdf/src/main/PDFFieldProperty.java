import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.kernel.pdf.PdfReader;


public class PDFFieldProperty {

	public static void main(String args[]) {
		// log.debug("#pdfFlatting: done. pdf size=[" + pdf.length + "]");
		// �t���b�g����
		byte[] pdf = null;

		File dir = new File("C:\\PROJECT\\AWCP0051\\DATA\\pdf-tpl\\"); // File�N���X�̃I�u�W�F�N�g�𐶐����Ώۂ̃f�B���N�g�����w��
		File[] list = dir.listFiles(); // listFiles���g�p���ăt�@�C���ꗗ���擾

		for (File f : list) {
			try {
				if (f.getName().endsWith("pdf")) {
					FileInputStream fileReader = new FileInputStream(f);
					BufferedInputStream bis = new BufferedInputStream(fileReader);
					pdf = new byte[(int) f.length()];
					bis.read(pdf);
					clearPdfField(pdf, f.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void clearPdfField(byte[] pdf, String outname) throws Exception {

		String pdf_font_file = "C:\\PROJECT\\AWCP0051\\DATA\\CONF\\msgothic.ttc,0";
		byte[] result = null;

		String outdir = "c:\\temp\\test\\";

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			PdfReader pdfForm = new PdfReader(pdf);
			PdfStamper stamp = new PdfStamper(pdfForm, bos);

			Map<String, AcroFields.Item> fieldMap = stamp.getAcroFields().getFields();
			AcroFields forms = stamp.getAcroFields();

			for (Map.Entry<String, AcroFields.Item> entry : fieldMap.entrySet()) {
				String fieldName = entry.getKey();
				Rectangle rectangle = forms.get .getFieldPositions(fieldName);

			}
			// stamp.setFormFlattening(true);
			stamp.close();
			result = bos.toByteArray();

		} catch (DocumentException | IOException e) {
			throw e;

			// log.error("PDF�t���b�g�����s�B�� " + e.getMessage());
			// throw new BusinessException("PDF�t���b�g�����s�B");
		}
	}

}
