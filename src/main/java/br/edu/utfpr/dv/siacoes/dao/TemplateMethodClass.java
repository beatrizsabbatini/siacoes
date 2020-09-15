package br.edu.utfpr.dv.siacoes.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.utfpr.dv.siacoes.model.Department;

public abstract class TemplateMethodClass<TM> {
	
	public abstract List<TM> listAll() throws SQLException;
	public abstract List<TM> listAll(boolean onlyActive) throws SQLException;
	public abstract TM findbyId(int id) throws SQLException;
	public abstract int save(int idUser, TM unit) throws SQLException;
	public int save(int idUser, Department unit) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
