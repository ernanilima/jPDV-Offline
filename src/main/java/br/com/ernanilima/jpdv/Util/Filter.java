package br.com.ernanilima.jpdv.Util;

/**
 * Classe de filtros para substituir/limpar trechos de String
 *
 * @author Ernani Lima
 */
public class Filter {

    /**
     * Filtra e converte uma String para long.
     * Limpa todos os caracteres que nao sejam numericos
     *
     * @param numberLong String - Conteudo a ser filtrado
     * @return long - Conteudo filtrado e convertido para long
     */
    public static long filterLong(String numberLong) {
        numberLong = numberLong.replaceAll("[^0-9]", "");
        return Long.parseLong(numberLong);
    }

    /**
     * Filtra e converte uma String para double.
     * Converte todas as virgulas(,) para pontos(.)
     *
     * @param numberDouble String - Conteudo a ser filtrado
     * @return double - Conteudo filtrado e convertido para double
     */
    public static double filterDouble(String numberDouble) {
        numberDouble = numberDouble.replace(",", ".");
        return Double.parseDouble(numberDouble);
    }
}
