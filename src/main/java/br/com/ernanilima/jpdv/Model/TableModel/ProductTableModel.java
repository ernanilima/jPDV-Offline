package br.com.ernanilima.jpdv.Model.TableModel;

import br.com.ernanilima.jpdv.Model.Coupon;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Table Model de produtos vendidos
 *
 * @author Ernani Lima
 */
public class ProductTableModel extends AbstractTableModel {

    // Lista de produtos do cupom
    private final List<Coupon> lsProducts = new ArrayList<>();

    /**
     * Com esta informacao, a JTable sabe quantas linhas devem ser exibidas
     * @return int - Quantidade total de linhas
     */
    @Override
    public int getRowCount() {
        return lsProducts.size();
    }

    /**
     * A JTable eh criada com a quantidade retornada
     * @return int - Quantidade total de colunas
     */
    @Override
    public int getColumnCount() {
        return 1;
    }

    /**
     * @param rowIndex int - Linha
     * @param columnIndex int - Coluna
     * @return Object - Conteudo da celula especificada
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lsProducts.get(rowIndex).getProductHTML();
        }
        return null;
    }

    /**
     * Metodo que adiciona produto na JTable
     * @param mCoupon {@link Coupon} - Model de cupom
     */
    public void addRow(Coupon mCoupon) {
        // Adiciona novo produto a tabela
        this.lsProducts.add(mCoupon);

        // Notifica que todos os dados da tabela podem ter sido alterados.
        // A JTable usa esta informacao para redesenhar todas as celulas, atualizando seu conteudo.
        this.fireTableDataChanged();
    }
}
