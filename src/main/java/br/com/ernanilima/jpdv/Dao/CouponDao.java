package br.com.ernanilima.jpdv.Dao;

import br.com.ernanilima.jpdv.Connection.ConnectionSQLite;
import br.com.ernanilima.jpdv.Model.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static br.com.ernanilima.jpdv.Connection.ConnectionSQLite.closeSQLite;

/**
 * Classe DAO de cupom
 *
 * @author Ernani Lima
 */
public class CouponDao {

    /**
     * Salva os dados de venda de cupom
     * @param mCoupon {@link Coupon} - Model de cupom
     */
    public void saveSaleCoupon(Coupon mCoupon) {
        Connection conn = null;
        PreparedStatement pst = null;
        String sql = "INSERT INTO vendpdv ("
                + "cod_filial, cod_pdv, cod_pag01, val_fpg01, cod_pag02, val_fpg02, cod_pag03, val_fpg03, "
                + "cup_total, cup_desconto, cod_ope, cod_sup, cup_canc, data_data, data_hora, vend_status, cod_mesa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionSQLite.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setInt(1, mCoupon.getmCompany().getId());
            pst.setInt(2, 1); // PENDENTE DE DEFINIR CODIGO DO PDV
            pst.setInt(3, mCoupon.getFormOfPayment1());
            pst.setDouble(4,mCoupon.getPaymentAmount1());
            pst.setInt(5, mCoupon.getFormOfPayment2());
            pst.setDouble(6,mCoupon.getPaymentAmount2());
            pst.setInt(7, mCoupon.getFormOfPayment3());
            pst.setDouble(8,mCoupon.getPaymentAmount3());
            pst.setDouble(9, mCoupon.getTotalCouponValue());
            pst.setDouble(10, mCoupon.getTotalDiscount());
            pst.setInt(11, mCoupon.getmUser().getId());
            pst.setInt(12, 0); // PENDENTE DE DEFINIR CODIGO DO SUPERVISOR
            pst.setBoolean(13, mCoupon.isCouponCanceled());
            pst.setString(14, String.valueOf(mCoupon.getDate()));
            pst.setString(15, String.valueOf(mCoupon.getHour()));
            pst.setBoolean(16, mCoupon.isCouponStatus());
            pst.setInt(17, mCoupon.getTable());
            pst.executeUpdate();
            System.out.println("GRAVOU A VENDA NO BANCO DE DADOS");

        } catch (ClassNotFoundException e) {
            System.out.println("ERRO AO ABRIR CONEXAO COM DBJPDV: " + e);
        } catch (SQLException e) {
            System.out.println("ERRO AO REALIZAR SALVAR VENDA: " + e);
        } finally {
            closeSQLite(conn, pst);
        }
    }

    /**
     * Salva os produtos de cupom
     * @param lsCoupon {@link Coupon} - Model de cupom
     */
    public void saveCouponProducts(List<Coupon> lsCoupon) {
        Connection conn = null;
        PreparedStatement pst = null;

        String sql = "INSERT INTO vpropdv ("
                + "cod_filial, cod_pdv, cod_cupom, cod_pro, desc_pro, cod_bar, cod_un_medida, desc_un_medida, qtd_vend, prec_vend, "
                + "prec_total, cod_ope, cod_sup, pro_canc, data_data, data_hora, vend_status, cod_mesa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionSQLite.openConnection();
            pst = conn.prepareStatement(sql);

            for (Coupon mLsCoupon : lsCoupon) {
                pst.setInt(1, mLsCoupon.getmCompany().getId());
                pst.setInt(2, mLsCoupon.getmPDV().getId());
                pst.setInt(3, mLsCoupon.getCoupon());
                pst.setInt(4, mLsCoupon.getmProduct().getId());
                pst.setString(5, mLsCoupon.getmProduct().getDescriptionCoupon());
                pst.setLong(6, mLsCoupon.getmProduct().getBarcode());
                pst.setInt(7, mLsCoupon.getmProduct().getmUnits().getId());
                pst.setString(8, mLsCoupon.getmProduct().getmUnits().getDescription());
                pst.setDouble(9, mLsCoupon.getQuantity());
                pst.setDouble(10, mLsCoupon.getmProduct().getSalePrice());
                pst.setDouble(11, mLsCoupon.getTotalProductValue());
                pst.setInt(12, mLsCoupon.getmUser().getId());
                pst.setInt(13, 0); // PENDENTE DE IMPLEMENTACAO
                pst.setBoolean(14, mLsCoupon.isProductCanceled());
                pst.setString(15, String.valueOf(mLsCoupon.getDate()));
                pst.setString(16, String.valueOf(mLsCoupon.getHour()));
                pst.setBoolean(17, mLsCoupon.isCouponStatus());
                pst.setInt(18, mLsCoupon.getTable());

                pst.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO AO ABRIR CONEXAO COM DBJPDV: " + e);
        } catch (SQLException e) {
            System.out.println("ERRO AO REALIZAR SALVAR VENDA: " + e);
        } finally {
            closeSQLite(conn, pst);
        }
    }

    /**
     * @return String - Proximo ID de cupom disponivel
     */
    public String nextCouponID() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nextID;
        String sql = "SELECT seq FROM sqlite_sequence WHERE name='vendpdv'";

        try {
            conn = ConnectionSQLite.openConnection();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                return nextID = String.valueOf(rs.getInt("seq") + 1);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO AO ABRIR CONEXAO COM DBJPDV: " + e);
        } catch (SQLException e) {
            System.out.println("ERRO AO BUSCAR ULTIMO ID DO CUPOM: " + e);
        } finally {
            closeSQLite(conn, pst, rs);
        }
        return null;
    }
}