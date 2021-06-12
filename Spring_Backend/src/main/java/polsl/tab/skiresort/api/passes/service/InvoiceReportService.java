package polsl.tab.skiresort.api.passes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.passes.pdf.InvoiceReportBuilder;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.repository.InvoiceRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class InvoiceReportService {

    private final InvoiceRepository invoiceRepository;

    private final InvoiceReportBuilder invoiceReportBuilder;

    public InvoiceReportService(InvoiceRepository invoiceRepository, InvoiceReportBuilder invoiceReportBuilder) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceReportBuilder = invoiceReportBuilder;
    }

    public ByteArrayInputStream getInvoiceReport(Integer idInvoice) {

        Optional<Invoice> invoice = invoiceRepository.findById(idInvoice);

        if(invoice.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find invoice with this id");
        else{
            try{
                return invoiceReportBuilder.generateReportPDF(invoice.get());
            }catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create pdf");
            }
        }
    }

}
