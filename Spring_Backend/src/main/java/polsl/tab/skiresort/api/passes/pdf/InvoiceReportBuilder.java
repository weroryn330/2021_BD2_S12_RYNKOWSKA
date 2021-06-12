package polsl.tab.skiresort.api.passes.pdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.line.LineStyle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class InvoiceReportBuilder {

    private static final PDFont helveticaBold = PDType1Font.HELVETICA_BOLD;
    
    private static final PDFont helvetica = PDType1Font.HELVETICA;

    private static final float MARGIN = 50;
    
    private static final float BOTTOM_MARGIN = 70;

    public ByteArrayInputStream generateReportPDF(Invoice invoice) throws IOException {

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);

        document.addPage(page);

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page);

        PDRectangle rect = page.getMediaBox();
        float yStartNewPage = rect.getHeight() - (MARGIN); // starting y position is whole page height subtracted by top MARGIN
        float tableWidth = rect.getWidth() - (2 * MARGIN); // we want table across whole page width (subtracted by left and right MARGIN of course)
        float yPosition = rect.getHeight() - 305; // y position is your coordinate of top left corner of the table

        // Company's logo/name
        cos.beginText();
        cos.setFont(helveticaBold, 32);
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 75);
        cos.showText("Narcikowo.pl");
        cos.endText();

        // Basic header: invoice id and date
        cos.beginText();
        cos.setLeading(20);
        cos.setFont(helveticaBold, 14);
        cos.newLineAtOffset(375, rect.getHeight() - 60);
        cos.showText("Faktura nr: ");
        cos.setFont(helvetica, 14);
        cos.showText(invoice.getIdInvoice().toString());
        cos.newLine();
        cos.setFont(helveticaBold, 14);
        cos.showText("Z dnia: ");
        cos.setFont(helvetica, 14);
        cos.showText(invoice.getInvoiceDate().toString());
        cos.endText();

        // Company's credentials
        cos.beginText();
        cos.setFont(helveticaBold, 14);
        cos.newLineAtOffset(75, rect.getHeight() - 135);
        cos.showText("Sprzedawca: ");
        cos.newLine();
        cos.setFont(helvetica, 14);
        cos.showText("Narcikowo");
        cos.newLine();
        cos.showText("ul. Jakasulica");
        cos.newLine();
        cos.showText("23041 Livigno");
        cos.newLine();
        cos.showText("kontakt@narcikowo.pl");
        cos.endText();

        // Client's credentials
        cos.beginText();
        cos.setFont(helveticaBold, 14);
        cos.newLineAtOffset(325, rect.getHeight() - 135);
        cos.showText("Nabywca: ");
        cos.newLine();
        cos.setFont(helvetica, 14);
        cos.showText(invoice.getUserIdUser().getFirstName() + " " + invoice.getUserIdUser().getLastName());
        cos.newLine();
        cos.showText("ul. " + invoice.getUserIdUser().getAddress());
        cos.newLine();
        cos.showText(invoice.getUserIdUser().getPostalCode() + " " + invoice.getUserIdUser().getCity());
        cos.newLine();
        cos.showText(invoice.getUserIdUser().getEmail());
        cos.endText();

        // Items table header
        cos.beginText();
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 280);
        cos.setFont(helveticaBold, 14);
        cos.showText("Pozycje faktury: ");
        cos.endText();

        // Items table
        BaseTable table = new BaseTable(yPosition, yStartNewPage,
                BOTTOM_MARGIN, tableWidth, MARGIN, document, page, true, true);

        // Create and define header row
        Row<PDPage> headerRow = table.createRow(20);

        // Create and define header row cells
        Cell<PDPage> cell = headerRow.createCell(5, "Lp");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell = headerRow.createCell(40, "Nazwa");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(35, "Typ");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(20, "Cena");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);

        table.addHeaderRow(headerRow);

        int count = 1;
        float priceSum = 0;

        // For every item in invoice add row to items table
        for(Pass pass: invoice.getPassList()){

            Color color = count % 2 == 1 ? Color.LIGHT_GRAY : Color.WHITE;

            Row<PDPage> row = table.createRow(20);
            Cell<PDPage> passCell = row.createCell(5, Integer.toString(count));
            passCell.setAlign(HorizontalAlignment.RIGHT);
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            // Check age discount and set item name
            String itemName = "Karnet ";
            long age = TimeUnit.MILLISECONDS.toDays(invoice.getInvoiceDate().getTime() - pass.getBirthDate().getTime()) / 365;
            if(age < 3)
                itemName += "ulgowy - dziecko";
            else if(age < 18)
                itemName += "ulgowy - uczen";
            else if(age < 26)
                itemName += "ulgowy - student";
            else
                itemName += "normalny";

            passCell = row.createCell(40, itemName);
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            // Check type and time on time passes, set item type
            String type;
            if(pass.getUsesTotal() == null) {
                long time = TimeUnit.MILLISECONDS.toHours(pass.getEndDate().getTime() - pass.getStartDate().getTime());
                type = "Czasowy: ";
                if(time < 24)
                    type += time + " h";
                else if(time / 24 == 1)
                    type += "1 dzien";
                else
                    type += (time / 24) + " dni";
            } else
                type = "Liczba zjazdow: " + pass.getUsesTotal();

            passCell = row.createCell(35, type);
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            passCell = row.createCell(20, String.format( "%.02f",pass.getUnitPrice()) + " zl" );
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            passCell.setAlign(HorizontalAlignment.RIGHT);

            priceSum+=pass.getUnitPrice();
            count++;
        }

        // Add sum row
        Row<PDPage> sumRow = table.createRow(20);
        cell = sumRow.createCell(80, "Suma: ");
        cell.setFontSize(12);
        cell.setFont(helveticaBold);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBorderStyle(new LineStyle(Color.WHITE, 0));

        cell = sumRow.createCell(20, String.format("%.02f", priceSum) + " zl");
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell.setAlign(HorizontalAlignment.RIGHT);

        table.draw();
        cos.close();

        int pageNum = 1;
        int pagesTotal = document.getNumberOfPages();

        // For every page add footer
        for(PDPage newPage : document.getPages()){
            PDPageContentStream newCos = new PDPageContentStream(document, newPage, PDPageContentStream.AppendMode.APPEND, true);

            newCos.setStrokingColor(Color.BLACK);
            newCos.moveTo(MARGIN, 50);
            newCos.lineTo(rect.getWidth() - MARGIN, 50);
            newCos.stroke();

            newCos.beginText();
            newCos.newLineAtOffset(MARGIN, 40);
            newCos.setFont(helvetica, 10);
            newCos.showText("Narcikowo.pl©");
            newCos.endText();

            newCos.beginText();
            String pageString = "Strona " + pageNum + "/" + pagesTotal;
            newCos.newLineAtOffset(rect.getWidth() - MARGIN - (helvetica.getStringWidth(pageString) / 100), 40);
            newCos.setFont(helvetica, 10);
            newCos.showText(pageString);
            newCos.endText();

            pageNum++;
            newCos.close();
        }

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        document.save(stream);

        return new ByteArrayInputStream(stream.toByteArray());
    }
}
