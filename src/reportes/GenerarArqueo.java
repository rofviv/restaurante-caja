package reportes;

import datos.Core;
import entidad.ArqueoEntidad;
import entidad.DetalleArqueo;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import presentacion.VentanaCajaPresentacion;

public class GenerarArqueo {
    
    public void nuevoArqueo(Collection<DetalleArqueo> detalleArqueo, ArqueoEntidad arq) {
        try {          
            HashMap parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            parametro.put("fecha", sdf.format(new Date()));
            parametro.put("cajero", VentanaCajaPresentacion.CAJERO.getNombre() + " " + VentanaCajaPresentacion.CAJERO.getApellido());
            parametro.put("arqueo", arq.getArqueo() + "");
            parametro.put("balance", arq.getBalance() + "");
            parametro.put("diferencia", arq.getDiferencia() + "");
            parametro.put("nota", arq.getNota());
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(detalleArqueo);
            JasperPrint print = JasperFillManager.fillReport(Core.ARQUEO, parametro, jcd);
            JasperViewer jView = new JasperViewer(print, false);
            jView.setTitle("Arqueo");
            jView.setVisible(true);
            
            //JasperViewer.viewReport(print, false);
            //JasperPrintManager.printReport(print, false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ARQUEO: " + e.getMessage());
        }
    }
}
