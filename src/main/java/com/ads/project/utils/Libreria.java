package com.ads.project.utils;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Libreria {

	public static JasperPrint generarReporte(File file, JRBeanCollectionDataSource data) throws JRException {
        // Crear objeto de la clase JasperReport y manipular el objeto file
        JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
        // Crear reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, null, data);
        return jasperPrint;
    }
	
}
