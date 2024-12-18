package entidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Hora implements Runnable {

    JLabel lblMostrar;

    public Hora(JLabel lblMostrar) {
        this.lblMostrar = lblMostrar;
    }
    
    @Override
    public void run() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                lblMostrar.setText(sdf.format(d));
            }
        }).start();
    }
    
}
