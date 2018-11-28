package es.uca.gii.csi18.anakin.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import es.uca.gii.csi18.anakin.util.Config;

public class Data {

    public static String getPropertiesUrl() {
        return "./db.properties";
    }

    public static Connection Connection() throws Exception {
        try {
            Properties properties = Config.Properties(getPropertiesUrl());
            return DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
        } catch (Exception ee) {
            throw ee;
        }
    }

    public static void LoadDriver() throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, IOException {
        Class.forName(Config.Properties(Data.getPropertiesUrl()).getProperty("jdbc.driverClassName")).newInstance();
    }

    /**
     * Método String2Sql que transforma un string a formato soportable por SQL
     *
     * @param s
     * @param bAddQuotes
     * @param bAddWildcards
     * @return
     */
    public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildcards) {
        s = s.replace("'", "''");
        if (bAddWildcards)
            s = "%" + s + "%";
        if (bAddQuotes)
            s = "'" + s + "'";
        return s;
    }

    /**
     * Método Boolean2Sql que devuelve 0 si b es falso y 1 si es verdadero
     *
     * @param b valor booleano para pasar a entero
     * @return entero
     */
    public static int Boolean2Sql(boolean b) {
        if (b)
            return 1;
        else
            return 0;
    }

    /**
     * Método LastId que devuelve el último ID insertado en la BD
     *
     * @param con
     * @return
     * @throws Exception
     */
    public static int LastId(Connection con) throws Exception {
        ResultSet rs = null;
        try {
            Properties properties = Config.Properties(getPropertiesUrl());
            rs = con.createStatement().executeQuery(properties.getProperty("jdbc.lastIdSentence"));
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ee) {
            throw ee;
        } finally {
            if (rs != null) rs.close();
        }
    }
}