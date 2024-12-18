package reportes;

import datos.ConexionDB;
import datos.Core;
import entidad.InfoTipoVenta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GenerarReportes {

    public void detalleVentas(ArrayList<InfoTipoVenta> datos, String usuario, String f_ini, String f_fin) {
        String cant_efec = "0";
        String total_efec = "0";
        String cant_consumo = "0";
        String total_consumo = "0";
        String cant_tarjeta = "0";
        String total_tarjeta = "0";
        try {
            Map parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            parametro.put("fecha_hora_g", sdf.format(new Date()));
            parametro.put("fecha_ini", f_ini);
            parametro.put("fecha_fin", f_fin);
            parametro.put("usuario", usuario);
            if (usuario.equals("%")) {
                parametro.put("repor_usuario", "Todos");
            } else {
                parametro.put("repor_usuario", usuario);
            }
            for (InfoTipoVenta info : datos) {
                switch (info.getTipo()) {
                    case "EFECTIVO":
                        cant_efec = info.getCant() + "";
                        total_efec = info.getMonto() + "";
                        break;
                    case "TARJETA":
                        cant_tarjeta = info.getCant() + "";
                        total_tarjeta = info.getMonto() + "";
                        break;
                    case "CONS. INTERNO":
                        cant_consumo = info.getCant() + "";
                        total_consumo = info.getMonto() + "";
                        break;

                }
            }
            parametro.put("cant_ventas", cant_efec);
            parametro.put("total_ventas", total_efec);
            parametro.put("cant_consumo", cant_consumo);
            parametro.put("total_consumo", total_consumo);
            parametro.put("cant_tarjeta", cant_tarjeta);
            parametro.put("total_tarjeta", total_tarjeta);
            JasperPrint print = JasperFillManager.fillReport(Core.DETALLE_VENTA, parametro, Core.CONEXION);
            JasperViewer jView = new JasperViewer(print, false);
            jView.setTitle("Detalle de Ventas");
            jView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas al iniciar el reporte diario " + e.getMessage());
        }
    }

    public void resumenVentas(String fecha_ini, String fecha_fin, String total_ventas, String total_pedidos, String promedio_ventas, String usuario) {
        try {
            ConexionDB db = new ConexionDB();
            Map parametro = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            parametro.put("fecha_hora_g", sdf.format(new Date()));
            parametro.put("fecha_ini", fecha_ini);
            parametro.put("fecha_fin", fecha_fin);
            parametro.put("total_ventas", total_ventas);
            parametro.put("total_pedidos", total_pedidos);
            parametro.put("prom_ventas", promedio_ventas);
            parametro.put("usuario", usuario);
            if (usuario.equals("%")) {
                parametro.put("repor_usuario", "Todos");
            } else {
                parametro.put("repor_usuario", usuario);
            }
            JasperPrint print = JasperFillManager.fillReport(Core.RESUMEN_VENTA, parametro, db.conexion());
            JasperViewer jView = new JasperViewer(print, false);
            jView.setTitle("Resumen de Ventas");
            jView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas al iniciar el reporte mensual " + e.getMessage());
        }
    }
}
