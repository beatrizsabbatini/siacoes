package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;
import br.edu.utfpr.dv.siacoes.model.ActivityUnit;
import br.edu.utfpr.dv.siacoes.model.Department;

public class ActivityUnitDAO extends TemplateMethodClass<ActivityUnit> {


	public List<ActivityUnit> listAll() throws SQLException{

		try(Connection conn = ConnectionDAO.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM activityunit ORDER BY description");
			ResultSet rs = stmt.executeQuery()	
			){
			
			List<ActivityUnit> list = new ArrayList<ActivityUnit>();
			
			while(rs.next()){
				list.add(this.loadObject(rs));
			}
			
			return list;
		}
	}
	
	public ActivityUnit findById(int id) throws SQLException{
		
		
		try(Connection conn = ConnectionDAO.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM activityunit WHERE idActivityUnit=?");
			ResultSet rs = stmt.executeQuery();
			){
			
			stmt.setInt(1, id);

			if(rs.next()){
				return this.loadObject(rs);
			}else{
				return null;
			}
			
		}
	}
	
	public int save(int idUser, ActivityUnit unit) throws SQLException{
		boolean insert = (unit.getIdActivityUnit() == 0);
		ResultSet rs = null;
		
		try(Connection conn = ConnectionDAO.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO activityunit(description, fillAmount, amountDescription) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
			PreparedStatement update = conn.prepareStatement("UPDATE activityunit SET description=?, fillAmount=?, amountDescription=? WHERE idActivityUnit=?")
			){
			
			
			if(insert){
				stmt.executeQuery();
			}else{
				update.executeQuery();
			}
			
			stmt.setString(1, unit.getDescription());
			stmt.setInt(2, (unit.isFillAmount() ? 1 : 0));
			stmt.setString(3, unit.getAmountDescription());
			
			if(!insert){
				stmt.setInt(4, unit.getIdActivityUnit());
			}
			
			stmt.execute();
			
			if(insert){
				rs = stmt.getGeneratedKeys();
				
				if(rs.next()){
					unit.setIdActivityUnit(rs.getInt(1));
				}
				
				new UpdateEvent(conn).registerInsert(idUser, unit);
			} else {
				new UpdateEvent(conn).registerUpdate(idUser, unit);
			}
			
			return unit.getIdActivityUnit();
		}
	}
	
	private ActivityUnit loadObject(ResultSet rs) throws SQLException{
		ActivityUnit unit = new ActivityUnit();
		
		unit.setIdActivityUnit(rs.getInt("idActivityUnit"));
		unit.setDescription(rs.getString("Description"));
		unit.setFillAmount(rs.getInt("fillAmount") == 1);
		unit.setAmountDescription(rs.getString("amountDescription"));
		
		return unit;
	}

	@Override
	public List<ActivityUnit> listAll(boolean onlyActive) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityUnit findbyId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



}
