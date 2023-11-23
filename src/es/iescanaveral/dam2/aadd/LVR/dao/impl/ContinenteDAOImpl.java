package es.iescanaveral.dam2.aadd.LVR.dao.impl;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import es.iescanaveral.dam2.aadd.LVR.dao.IContinenteDao;
import es.iescanaveral.dam2.aadd.LVR.uttilidades.Conexiones;
import es.iescanaveral.dam2.aadd.LVR.uttilidades.GestorProperties;
import es.iescanaveral.dam2.aadd.LVR.vo.Continente;

public class ContinenteDAOImpl implements IContinenteDao

{    public int eliminarContinente(String codContinente) throws SQLException, ClassNotFoundException {
	
    
	int resultado = 0;
    
	Connection conexion = Conexiones.Conexiones();
    Statement statement = conexion.createStatement();
    String deletePaisesSQL = "DELETE FROM T_PAIS WHERE cod_continente  ="+codContinente+";";
     resultado = statement.executeUpdate(deletePaisesSQL);

String deleteContinenteSQL = "DELETE FROM T_CONTINENTE WHERE cod_continente ="+codContinente+";";
   resultado =  statement.executeUpdate(deleteContinenteSQL);
	        statement.close();
    conexion.close();
    
    return resultado;
}


public int agregarContinente(Continente continente) throws SQLException, ClassNotFoundException
{
	int resultado;
	Connection conexion = Conexiones.Conexiones();
   
    Statement statement = conexion.createStatement();
    String sql = "INSERT INTO T_CONTINENTE (cod_continente, nombre_continente) VALUES ('"+continente.getCodigo()+ "','"+continente.getNombre()+"')";
    resultado= statement.executeUpdate(sql);
    
    statement.close();
    conexion.close();
    
    return resultado;
    
}




}
