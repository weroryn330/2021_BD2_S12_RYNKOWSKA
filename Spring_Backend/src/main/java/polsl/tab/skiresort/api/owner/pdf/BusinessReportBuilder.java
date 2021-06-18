package polsl.tab.skiresort.api.owner.pdf;

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
import polsl.tab.skiresort.model.SkiLift;
import polsl.tab.skiresort.model.Usage;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class BusinessReportBuilder {

    private static final PDFont helveticaBold = PDType1Font.HELVETICA_BOLD;

    private static final PDFont helvetica = PDType1Font.HELVETICA;

    private static final float MARGIN = 50;

    private static final float BOTTOM_MARGIN = 70;

    private static final float[] INVOICE_TABLE_CELL_WIDTHS = {5, 40, 25, 10, 20};

    private static final float[] PASS_TABLE_CELL_WIDTHS = {30, 30, 40};

    private static final int[] QUANTITY_PASS_TYPES = {10, 20, 30, 60, 90};

    private static final int[] TIME_PASS_TYPES = {3, 6, 24, 48, 72, 168, 288};

    public ByteArrayInputStream generateReportPDF(List<Invoice> invoices, List<SkiLift> skiLifts, List<Usage> usages, Date startDate, Date endDate) throws IOException {

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);

        document.addPage(page);

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page);

        PDRectangle rect = page.getMediaBox();
        float yStartNewPage = rect.getHeight() - (MARGIN); // starting y position is whole page height subtracted by top MARGIN
        float tableWidth = rect.getWidth() - (2 * MARGIN); // we want table across whole page width (subtracted by left and right MARGIN of course)

        // Company's logo/name
        cos.beginText();
        cos.setFont(helveticaBold, 32);
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 75);
        cos.showText("Narcikowo.pl");
        cos.endText();

        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");

        // Basic header: invoice id and date
        cos.beginText();
        cos.setLeading(20);
        cos.setFont(helveticaBold, 14);
        cos.newLineAtOffset(375, rect.getHeight() - 40);
        cos.showText("Raport biznesowy");
        cos.newLine();
        cos.showText("Okres: ");
        cos.newLine();
        cos.setFont(helvetica, 14);
        cos.showText("od " + dtf.format(startDate));
        cos.newLine();
        cos.showText("do " + dtf.format(endDate));
        cos.endText();

        cos.beginText();
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 160);
        cos.setFont(helveticaBold, 14);
        cos.showText("Podsumowanie faktur: ");
        cos.endText();

        // Invoices table
        BaseTable table = new BaseTable(rect.getHeight() - 185, yStartNewPage,
                BOTTOM_MARGIN, tableWidth, MARGIN, document, page, true, true);

        // Create and define header row
        Row<PDPage> headerRow = table.createRow(20);

        // Create and define header row cells
        Cell<PDPage> cell = headerRow.createCell(INVOICE_TABLE_CELL_WIDTHS[0], "Lp");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell = headerRow.createCell(INVOICE_TABLE_CELL_WIDTHS[1], "Nabywca");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(INVOICE_TABLE_CELL_WIDTHS[2], "Data");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(INVOICE_TABLE_CELL_WIDTHS[3], "Numer");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(INVOICE_TABLE_CELL_WIDTHS[4], "Przychod");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);

        table.addHeaderRow(headerRow);

        // For every invoice add row
        int count = 1;
        float priceSum = 0;
        for(Invoice invoice: invoices){
            Color color = count % 2 == 1 ? Color.LIGHT_GRAY : Color.WHITE;

            Row<PDPage> row = table.createRow(20);
            Cell<PDPage> passCell = row.createCell(INVOICE_TABLE_CELL_WIDTHS[0], Integer.toString(count));
            passCell.setAlign(HorizontalAlignment.RIGHT);
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            passCell = row.createCell(INVOICE_TABLE_CELL_WIDTHS[1], invoice.getUserIdUser().getFirstName() + " " + invoice.getUserIdUser().getLastName());
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            passCell = row.createCell(INVOICE_TABLE_CELL_WIDTHS[2], invoice.getInvoiceDate().toString());
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            passCell = row.createCell(INVOICE_TABLE_CELL_WIDTHS[3], invoice.getIdInvoice().toString());
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            passCell = row.createCell(INVOICE_TABLE_CELL_WIDTHS[4], String.format( "%.02f",invoice.getTotal()) + " zl" );
            passCell.setFontSize(12);passCell.setFillColor(color);passCell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            passCell.setAlign(HorizontalAlignment.RIGHT);

            priceSum += invoice.getTotal();
            count++;
        }

        // Add sum row
        Row<PDPage> sumRow = table.createRow(20);
        cell = sumRow.createCell(100 - INVOICE_TABLE_CELL_WIDTHS[4], "Suma: ");
        cell.setFontSize(12);
        cell.setFont(helveticaBold);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBorderStyle(new LineStyle(Color.WHITE, 0));

        cell = sumRow.createCell(INVOICE_TABLE_CELL_WIDTHS[4], String.format("%.02f", priceSum) + " zl");
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell.setAlign(HorizontalAlignment.RIGHT);

        table.draw();
        cos.close();

        List<Pass> passes = invoices.stream().map(Invoice::getPassList).flatMap(Collection::stream).collect(Collectors.toList());

        // Pass summary page
        page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        cos = new PDPageContentStream(document, page);

        cos.beginText();
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 50);
        cos.setFont(helveticaBold, 14);
        cos.showText("Podsumowanie karnetow zjazdowych: ");
        cos.endText();

        // Quantity passes table summary
        table = new BaseTable(rect.getHeight() - 80, yStartNewPage,
                BOTTOM_MARGIN, tableWidth, MARGIN, document, page, true, true);

        headerRow = table.createRow(20);

        // Create and define header row cells
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[0], "Typ karnetu");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[1], "Ilosc karnetow");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[2], "Przychod");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);


        List<Pass> quantityPasses = passes.stream().filter(pass -> pass.getUsesTotal() != null).collect(Collectors.toList());
        List<Pass> timePasses = passes.stream().filter(pass -> pass.getUsesTotal() == null).collect(Collectors.toList());

        for(int i = 0 ; i < QUANTITY_PASS_TYPES.length; i++) {
            Color color = i % 2 != 1 ? Color.LIGHT_GRAY : Color.WHITE;
            int finalI = i;
            List<Pass> passesFiltered = quantityPasses.stream().filter(pass -> pass.getUsesTotal() == QUANTITY_PASS_TYPES[finalI]).collect(Collectors.toList());
            Row<PDPage> row = table.createRow(20);

            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[0], QUANTITY_PASS_TYPES[i] + " Zjazdow");
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[1], Long.toString(passesFiltered.size()));
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[2], Double.toString(passesFiltered.stream().mapToDouble(Pass::getUnitPrice).sum()));
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell.setAlign(HorizontalAlignment.RIGHT);
        }

        // Add sum row
        sumRow = table.createRow(20);
        cell = sumRow.createCell(100 - PASS_TABLE_CELL_WIDTHS[2] - PASS_TABLE_CELL_WIDTHS[1], "Suma: ");
        cell.setFontSize(12);
        cell.setFont(helveticaBold);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBorderStyle(new LineStyle(Color.WHITE, 0));
        cell = sumRow.createCell(PASS_TABLE_CELL_WIDTHS[1], Integer.toString(quantityPasses.size()));
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell = sumRow.createCell(PASS_TABLE_CELL_WIDTHS[2], String.format("%.02f", quantityPasses.stream().mapToDouble(Pass::getUnitPrice).sum()) + " zl");
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell.setAlign(HorizontalAlignment.RIGHT);

        table.draw();

        cos.beginText();
        cos.newLineAtOffset(MARGIN, rect.getHeight() - 275);
        cos.setFont(helveticaBold, 14);
        cos.showText("Podsumowanie karnetow czasowych: ");
        cos.endText();

        // Time passes table summary
        table = new BaseTable(rect.getHeight() - 305, yStartNewPage,
                BOTTOM_MARGIN, tableWidth, MARGIN, document, page, true, true);

        headerRow = table.createRow(20);

        // Create and define header row cells
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[0], "Typ karnetu");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[1], "Ilosc karnetow");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell = headerRow.createCell(PASS_TABLE_CELL_WIDTHS[2], "Przychod");
        cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
        cell.setAlign(HorizontalAlignment.RIGHT);

        for(int i = 0; i < TIME_PASS_TYPES.length; i++){
            Color color = i % 2 != 1 ? Color.LIGHT_GRAY : Color.WHITE;
            int finalI = i;
            List<Pass> passesFiltered = timePasses.stream().filter(pass -> TimeUnit.MILLISECONDS.toHours(pass.getEndDate().getTime() - pass.getStartDate().getTime()) == TIME_PASS_TYPES[finalI]).collect(Collectors.toList());
            Row<PDPage> row = table.createRow(20);
            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[0], TIME_PASS_TYPES[i] + " godzin");
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[1], Long.toString(passesFiltered.size()));
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = row.createCell(PASS_TABLE_CELL_WIDTHS[2], Double.toString(passesFiltered.stream().mapToDouble(Pass::getUnitPrice).sum()));
            cell.setFontSize(12);cell.setFillColor(color);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
            cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell.setAlign(HorizontalAlignment.RIGHT);
        }

        // Add sum row
        sumRow = table.createRow(20);
        cell = sumRow.createCell(100 - PASS_TABLE_CELL_WIDTHS[2] - PASS_TABLE_CELL_WIDTHS[1], "Suma: ");
        cell.setFontSize(12);
        cell.setFont(helveticaBold);
        cell.setAlign(HorizontalAlignment.RIGHT);
        cell.setBorderStyle(new LineStyle(Color.WHITE, 0));
        cell = sumRow.createCell(PASS_TABLE_CELL_WIDTHS[1], Integer.toString(timePasses.size()));
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell = sumRow.createCell(PASS_TABLE_CELL_WIDTHS[2], String.format("%.02f", timePasses.stream().mapToDouble(Pass::getUnitPrice).sum()) + " zl");
        cell.setFontSize(12);
        cell.setFont(helvetica);
        cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
        cell.setAlign(HorizontalAlignment.RIGHT);
        table.draw();

        cos.close();

        // For every ski lift add summary page
        for(SkiLift skiLift: skiLifts){
            PDPage newPage = new PDPage(PDRectangle.A4);
            document.addPage(newPage);

            PDPageContentStream newCos = new PDPageContentStream(document, newPage);

            newCos.beginText();
            newCos.newLineAtOffset(MARGIN, rect.getHeight() - 50);
            newCos.setFont(helveticaBold, 20);
            newCos.showText("Podsumowanie dla wyciagu: " + skiLift.getName());
            newCos.endText();

            newCos.beginText();
            newCos.newLineAtOffset(MARGIN, rect.getHeight() - 90);
            newCos.setFont(helveticaBold, 14);
            newCos.showText("Karnety zjazdowe: ");
            newCos.endText();

            // Quantity passes table summary
            table = new BaseTable(rect.getHeight() - 120, yStartNewPage,
                    BOTTOM_MARGIN, tableWidth, MARGIN, document, newPage, true, true);

            headerRow = table.createRow(20);

            // Create and define header row cells
            cell = headerRow.createCell(50, "Typ karnetu");
            cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = headerRow.createCell(50, "Ilosc Zjazdow");
            cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));

            for(int i = 0 ; i < QUANTITY_PASS_TYPES.length; i++) {
                Color color = i % 2 != 1 ? Color.LIGHT_GRAY : Color.WHITE;
                int finalI = i;
                Row<PDPage> row = table.createRow(20);

                cell = row.createCell(50, QUANTITY_PASS_TYPES[i] + " Zjazdow");
                cell.setFontSize(12);cell.setFillColor(color);
                cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
                cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
                cell = row.createCell(50, Long.toString(usages.stream().filter(
                        usage -> usage.getSkiLiftIdSkiLift().equals(skiLift) &&
                                usage.getPassesIdInvoiceItem().getUsesTotal() != null &&
                                usage.getPassesIdInvoiceItem().getUsesTotal() == QUANTITY_PASS_TYPES[finalI]).count()));
                cell.setFontSize(12);cell.setFillColor(color);
                cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
                cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            }

            // Add sum row
            sumRow = table.createRow(20);
            cell = sumRow.createCell(50, "Suma: ");
            cell.setFontSize(12);
            cell.setFont(helveticaBold);
            cell.setAlign(HorizontalAlignment.RIGHT);
            cell.setBorderStyle(new LineStyle(Color.WHITE, 0));
            cell = sumRow.createCell(50, Long.toString(usages.stream().filter(
                    usage -> usage.getSkiLiftIdSkiLift().equals(skiLift) &&
                            usage.getPassesIdInvoiceItem().getUsesTotal() != null).count()));
            cell.setFontSize(12);
            cell.setFont(helvetica);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            table.draw();

            newCos.beginText();
            newCos.newLineAtOffset(MARGIN, rect.getHeight() - 315);
            newCos.setFont(helveticaBold, 14);
            newCos.showText("Karnety czasowe: ");
            newCos.endText();

            // Time passes table summary
            table = new BaseTable(rect.getHeight() - 345, yStartNewPage,
                    BOTTOM_MARGIN, tableWidth, MARGIN, document, newPage, true, true);

            headerRow = table.createRow(20);

            // Create and define header row cells
            cell = headerRow.createCell(50, "Typ karnetu");
            cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            cell = headerRow.createCell(50, "Ilosc zjazdow");
            cell.setFontSize(12);cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));


            for(int i = 0; i < TIME_PASS_TYPES.length; i++){
                Color color = i % 2 != 1 ? Color.LIGHT_GRAY : Color.WHITE;
                int finalI = i;
                Row<PDPage> row = table.createRow(20);
                String hours;
                if(i % 2 == 0 && i != TIME_PASS_TYPES.length - 1)
                    hours = " godziny";
                else
                    hours = " godzin";

                cell = row.createCell(50, TIME_PASS_TYPES[i] + hours);
                cell.setFontSize(12);cell.setFillColor(color);
                cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
                cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
                cell = row.createCell(50, Long.toString(usages.stream().filter(
                        usage -> usage.getSkiLiftIdSkiLift().equals(skiLift) &&
                                usage.getPassesIdInvoiceItem().getUsesTotal() == null &&
                                TimeUnit.MILLISECONDS.toHours(usage.getPassesIdInvoiceItem().getEndDate().getTime() - usage.getPassesIdInvoiceItem().getStartDate().getTime()) == TIME_PASS_TYPES[finalI]).count()));
                cell.setFontSize(12);cell.setFillColor(color);
                cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));
                cell.setBottomBorderStyle(new LineStyle(Color.DARK_GRAY, 1));
            }

            // Add sum row
            sumRow = table.createRow(20);
            cell = sumRow.createCell(50, "Suma: ");
            cell.setFontSize(12);
            cell.setFont(helveticaBold);
            cell.setAlign(HorizontalAlignment.RIGHT);
            cell.setBorderStyle(new LineStyle(Color.WHITE, 0));
            cell = sumRow.createCell(50, Long.toString(usages.stream().filter(
                    usage -> usage.getSkiLiftIdSkiLift().equals(skiLift) &&
                            usage.getPassesIdInvoiceItem().getUsesTotal() == null).count()));
            cell.setFontSize(12);
            cell.setFont(helvetica);
            cell.setBorderStyle(new LineStyle(Color.GRAY, 0.5f));

            table.draw();

            // Sum of usages for ski lift
            newCos.beginText();
            newCos.newLineAtOffset(50, rect.getHeight() - 600);
            newCos.setFont(helveticaBold, 16);
            newCos.showText("Suma zjazdow:  " + Long.toString(usages.stream().filter(
                    usage -> usage.getSkiLiftIdSkiLift().equals(skiLift)).count()));
            newCos.endText();

            newCos.close();
        }

        // For every page add footer
        int pageNum = 1;
        int pagesTotal = document.getNumberOfPages();
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
