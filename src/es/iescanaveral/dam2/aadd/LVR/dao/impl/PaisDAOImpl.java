package es.iescanaveral.dam2.aadd.LVR.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iescanaveral.dam2.aadd.LVR.dao.IPaisDao;
import es.iescanaveral.dam2.aadd.LVR.uttilidades.Conexiones;
import es.iescanaveral.dam2.aadd.LVR.uttilidades.GestorProperties;
import es.iescanaveral.dam2.aadd.LVR.vo.Continente;
import es.iescanaveral.dam2.aadd.LVR.vo.Pais;

public class PaisDAOImpl   implements IPaisDao
{public List<Pais> ListaPaisesAConsultar(String codContinente, String consulta)
		throws SQLException, ClassNotFoundException { // llamamos la conexion
	Connection conexion = Conexiones.Conexiones();

	List<Pais> listaPaises = new ArrayList<Pais>();
	ResultSet resultado = null;
	PreparedStatement prestatePais = null;
	try {
		
		String senetenciaSQL = "SELECT p.nombre_pais, p.capital, p.cod_pais, c.nombre_continente, c.cod_continente FROM T_PAIS p "
				+ "JOIN T_CONTINENTE c ON p.cod_continente = c.cod_continente " + "WHERE c.cod_continente = '?' AND p.capital LIKE '?%';";
		
		prestatePais.setString(1,codContinente);
		prestatePais.setString(2,consulta);
		resultado = prestatePais.executeQuery(senetenciaSQL);
		
		if (resultado != null) {
			while (resultado.next()  ) {
				Pais pais = new Pais();
				pais.setCapital(resultado.getString("capital")); 
				pais.setCodigopais(resultado.getInt("cod_pais"));
				pais.setNombre(consulta);
				
				String nombreContinente = resultado.getNString("nombre_continente");
				
				Continente continente = new Continente();
				continente.setNombre(resultado.getString("nombreContinente"));
				continente.setCodigo(resultado.getString("cod_continente"));
				continente.setCodigo(codContinente);
				pais.setContinente(continente);
				listaPaises.add(pais);
			}
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally { // si la conexion esta bien que cierre todos los clase.
		if (conexion != null) {
			try {
				resultado.close();
				prestatePais.close();
				conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	return listaPaises;
}

// metodo para actulizar el nombre de un pais

public int actulizarNombrePais(String capital, int codPais) throws SQLException, ClassNotFoundException {
	int resultado = 0;
	Connection conexion = Conexiones.Conexiones();
	// Creamos un 2 variable con el nombre y el numero del co
	

	Statement state = conexion.createStatement();

	String actNombrePais = "UPDATE T_PAIS SET  capital ='"+capital+"' WHERE cod_pais ="+codPais+";";
	 resultado=  state.executeUpdate(actNombrePais);
	
	 state.close();


	state.close();
	conexion.close();

	
	return resultado;
}
}
