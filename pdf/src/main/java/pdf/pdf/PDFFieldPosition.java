package pdf.pdf;

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

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

public class PDFFieldPosition {

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
					getPdfFieldPosition("C:\\PROJECT\\AWCP0051\\DATA\\pdf-tpl\\", f.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void getPdfFieldPosition(String srcdir, String fileName) throws Exception {

		String pdf_font_file = "C:\\PROJECT\\AWCP0051\\DATA\\CONF\\msgothic.ttc,0";
		byte[] result = null;

		String outdir = "c:\\temp\\test\\";

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
			PdfDocument pdf = new PdfDocument(new PdfReader(srcdir + fileName));

			PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

			Map<String, PdfFormField> fieldMap = form.getFormFields();

			for (Map.Entry<String, PdfFormField> entry : fieldMap.entrySet()) {
				String fieldName = entry.getKey();
				PdfFormField field = entry.getValue();
			///	System.out.printf("%s - %s\n", fieldName, field.getFont());

				PdfArray position = entry.getValue().getWidgets().get(0).getRectangle();
				// entry.getValue().getFont().
				// System.out.println(entry.getValue().getFont().toString());
				PdfFormFieldExt extField = new PdfFormFieldExt(field.getPdfObject());
				Object[] fontAndSize =
						extField.getFontAndSize(field.getWidgets().get(0).getNormalAppearanceObject());
				PdfFont font = (PdfFont)fontAndSize[0];
				Float size = (Float) fontAndSize[1];
		//		PdfName resourceName = (PdfName)fontAndSize[2];
		//		System.out.printf("%s - %s - %s - %s\n",
		//				fieldName.length(),
		//				font.getFontProgram().getFontNames(), size, resourceName);
				System.out.printf("%s - %s - %s - %s \n",
										fileName,
										fieldName,
										font.getFontProgram().getFontNames(), size);
				if (!"8.0".equals(Float.toString(size))) {
					System.out.println("************************************" + size);
				}
				System.out.println(position.toString());
			}

			pdf.close();

		} catch (Exception e) {
			throw e;

			// log.error("PDF�t���b�g�����s�B�� " + e.getMessage());
			// throw new BusinessException("PDF�t���b�g�����s�B");
		}
	}

}
