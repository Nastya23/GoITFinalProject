package ua.goit.finall.service;

import com.itextpdf.text.DocumentException;
import ua.goit.finall.model.Employee;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

public interface PdfService {
    OutputStream createEmployeeReport(Employee employee, OutputStream reportStream) throws DocumentException;
}
