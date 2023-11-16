package com.ads.project.utils;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ads.project.entity.InformeRequisicion;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class InformeRequisicionPDF {

    private List<InformeRequisicion> listaInforme;

    public InformeRequisicionPDF(List<InformeRequisicion> listaInforme) {
        this.listaInforme = listaInforme;
    }

    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(documento, response.getOutputStream());

        PieDePagina pieDePagina = new PieDePagina();
        writer.setPageEvent(pieDePagina);

        documento.open();

        Image imagen = Image.getInstance("classpath:ministerio.jpg");
        imagen.scaleToFit(65, 60);
        imagen.setAbsolutePosition(36, PageSize.A4.getHeight() - 36 - imagen.getScaledHeight());
        documento.add(imagen);

        documento.add(new Paragraph(" "));

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fuenteTitulo.setColor(Color.BLACK);
        fuenteTitulo.setSize(19);

        Paragraph titulo = new Paragraph("Ministerio Publico", fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);

        Font fuenteSeccionPequena = FontFactory.getFont(FontFactory.COURIER);
        fuenteSeccionPequena.setColor(Color.BLACK);
        fuenteSeccionPequena.setSize(10);

        documento.add(new Paragraph(" "));
        
        documento.add(crearSeccion("Logistica", fuenteSeccionPequena));
        documento.add(crearSeccion("RUC: 20131370301", fuenteSeccionPequena));
        documento.add(crearSeccion("Direccion: Av. Abancay Nro. 491", fuenteSeccionPequena));

        Font fuenteFechaWeb = FontFactory.getFont(FontFactory.HELVETICA);
        fuenteFechaWeb.setColor(Color.BLACK);
        fuenteFechaWeb.setSize(10);

        DateFormat fecha1 = new SimpleDateFormat("yyyy-MM-dd");
        String cy = fecha1.format(new Date());
        
        Font fuente2 = FontFactory.getFont(FontFactory.HELVETICA);
        fuente2.setColor(Color.BLUE);
        fuente2.setSize(10);
        
        Paragraph fecha = new Paragraph("Fecha: " + cy , fuenteFechaWeb);
        Paragraph paginaWeb = new Paragraph("Pagina Web: http://www.mpfn.gob.pe", fuenteFechaWeb);

        fecha.setAlignment(Element.ALIGN_RIGHT);
        paginaWeb.setAlignment(Element.ALIGN_RIGHT);

        documento.add(fecha);
        documento.add(paginaWeb);

        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] { 30, 100, 100, 100 });

        escribirCabeceraTabla(tabla);
        escribirDatosTabla(tabla);

        documento.add(tabla);
        documento.close();
    }

    private Paragraph crearSeccion(String contenido, Font fuente) {
        Paragraph seccion = new Paragraph(contenido, fuente);
        return seccion;
    }

    private void escribirCabeceraTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(Color.BLACK);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Paragraph("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Paragraph("Nombre", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Paragraph("Descripcion", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Paragraph("Requerimiento", fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosTabla(PdfPTable tabla) {
        for (InformeRequisicion r : listaInforme) {
            tabla.addCell(String.valueOf(r.getIdinforequisicion()));
            tabla.addCell(r.getNomrequisicion());
            tabla.addCell(r.getDescripcion());
            tabla.addCell(r.getReque().getNomrequerimiento());
        }
    }

    class PieDePagina extends PdfPageEventHelper {
    	
        public void footer(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell = new PdfPCell(new Paragraph("(©) 2023 - Ministerio Publico"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);

            table.setTotalWidth(document.right() - document.left());
            table.writeSelectedRows(0, -1, document.left(), document.bottom() + table.getTotalHeight(), writer.getDirectContent());
        }
        
    }
}
