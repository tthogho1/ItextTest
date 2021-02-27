package pdf.pdf;

import java.io.IOException;

import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDictionary;

class PdfFormFieldExt extends PdfFormField {
    public PdfFormFieldExt(PdfDictionary pdfObject) {
        super(pdfObject);
    }

    public Object[] getFontAndSize(PdfDictionary asNormal) throws IOException {
        return super.getFontAndSize(asNormal);
    }
}