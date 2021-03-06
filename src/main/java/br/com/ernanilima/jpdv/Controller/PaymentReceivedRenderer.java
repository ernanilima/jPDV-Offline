package br.com.ernanilima.jpdv.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * TableCellRenderer da JTable de pagamento recebido.
 *
 * @author Ernani Lima
 */
public class PaymentReceivedRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        final int DESCRIPTION = 0;
        final int VALUE = 1;

        // ALTERNA AS CORES DAS LINHAS
        if (row % 2 == 0) {
            setBackground(new Color(250, 250, 250));
        } else {
            setBackground(new Color(240, 240, 240));
        }

        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(DESCRIPTION).setResizable(false);
        table.getColumnModel().getColumn(DESCRIPTION).setPreferredWidth(100);
        table.getColumnModel().getColumn(VALUE).setResizable(false);
        table.getColumnModel().getColumn(VALUE).setPreferredWidth(10);

        return this;
    }
}
