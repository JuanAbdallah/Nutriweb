/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.util;

import br.com.davesmartins.nutriweb.model.RefeicoesObjetivo;
import br.com.davesmartins.nutriweb.util.exception.CriptografiaException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daves
 */
public class Util {

    public static final List<RefeicoesObjetivo> listaDieta = new ArrayList<>();

    static {
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Café da Manhã", 7, 120));
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Lanche da Manhã", 10, 80));
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Almoço", 12, 500));
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Lanche da Tarde", 15, 80));
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Jantar", 18, 300));
        listaDieta.add(new RefeicoesObjetivo("Emagrecer", "Lanche da Noite", 21, 50));

        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Café da Manhã", 7, 350));
        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Lanche da Manhã", 10, 150));
        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Almoço", 12, 700));
        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Lanche da Tarde", 15, 150));
        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Jantar", 18, 500));
        listaDieta.add(new RefeicoesObjetivo("Ganhar Massa", "Lanche da Noite", 21, 150));

        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Café da Manhã", 7, 200));
        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Lanche da Manhã", 10, 100));
        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Almoço", 12, 500));
        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Lanche da Tarde", 15, 100));
        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Jantar", 18, 400));
        listaDieta.add(new RefeicoesObjetivo("Melhorar Alimentação", "Lanche da Noite", 21, 90));
    }

    public static RefeicoesObjetivo findDieta(String objetivo, String refeicao) {
        for (RefeicoesObjetivo ro : listaDieta) {
            if (ro.getObjetivo().equals(objetivo) && ro.getTipoRefeicao().equals(refeicao)) {
                return ro;
            }
        }
        return null;
    }

    public static String toMd5(String valor) throws CriptografiaException {
        MessageDigest mDigest;
        try {
            //Instanciamos o nosso HASH MD5, poderíamos usar outro como
            //SHA, por exemplo, mas optamos por MD5.
            mDigest = MessageDigest.getInstance("MD5");

            //Convert a String valor para um array de bytes em MD5
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

            //Convertemos os bytes para hexadecimal, assim podemos salvar
            //no banco para posterior comparação se senhas
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF)
                        | 0x100).substring(1, 3));
            }

            return sb.toString();

        } catch (UnsupportedEncodingException e) {
            throw new CriptografiaException("Erro na Criptografia: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new CriptografiaException("Erro na Criptografia: " + e.getMessage());
        }
    }


    public static Date LocalDateTimeToDate(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public static String formatarMoeda(double valor) {
        return new DecimalFormat("Cr$ #,##0.00").format(valor);
    }

    public static String formatEmK(long numero) {
        if (numero >= 1000) {
            int valor = (int) (numero / 1000);
            return "+" + valor + "k";
        } else {
            return numero + "";
        }

    }

    public static Date toDate(String parameter) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.parse(parameter);
    }

    public static String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(data);
    }

    public static BigDecimal truncateDecimal(double x, int numberofDecimals) {
        if (x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }

    public static long tempoEmMinutos(LocalTime entrada, LocalTime saida) {
        Duration duracao = Duration.between(entrada, saida);
        return duracao.toMinutes();
    }

	public static String formatarData(LocalDateTime data) {
				
		return data.format( DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss") );
	}

}
