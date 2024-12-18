package reportes;

import datos.Core;
import entidad.DetalleTicket;
import entidad.VentaEntidad;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import presentacion.VentanaCajaPresentacion;

public class GenerarTicket {
    
    public void nuevoTicket(Collection<DetalleTicket> detalleTicket, VentaEntidad ventaE, String pago, String cambio) {
        try {          
            HashMap parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            parametro.put("cliente", ventaE.getCliente().toUpperCase());
            parametro.put("fecha", sdf.format(new Date()));
            parametro.put("cajero", VentanaCajaPresentacion.CAJERO.getUsuario());
            parametro.put("nroventa", ventaE.getNroVenta() + "");
            parametro.put("tipo", ventaE.getTipo().toUpperCase());
            parametro.put("total", ventaE.getTotal() + "");
            parametro.put("pago", pago);
            parametro.put("cambio", cambio);
            parametro.put("red", VentanaCajaPresentacion.redWifi.getRed());
            parametro.put("clave", VentanaCajaPresentacion.redWifi.getClave());
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(detalleTicket);
            JasperPrint print = JasperFillManager.fillReport(Core.TICKET, parametro, jcd);
            
            //JasperViewer.viewReport(print, false);
            JasperPrintManager.printReport(print, false);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "TICKET CLIENTE: " + e.getMessage());
        }
    }
    
    public void ticketCocina(Collection<DetalleTicket> detalleTicket, VentaEntidad ventaE) {
        try {          
            HashMap parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            parametro.put("hora", sdf.format(new Date()));
            parametro.put("orden", ventaE.getNroVenta() + "");
            parametro.put("cliente", ventaE.getCliente());
            parametro.put("tipo", ventaE.getTipo());
            parametro.put("nota", ventaE.getObservacion());
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(detalleTicket);
            JasperPrint print = JasperFillManager.fillReport(Core.TCOCINA, parametro, jcd);
            
            //JasperViewer.viewReport(print, false);
            JasperPrintManager.printReport(print, false);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "TICKET COCINA" + e.getMessage());
        }
    }
    
}
