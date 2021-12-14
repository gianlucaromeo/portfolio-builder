package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserMainInformationsDAOImpl extends DAOImpl implements DAO<UserMainInformations> {

	@Override
	public String create(UserMainInformations userMainInfo) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO users_main_informations VALUES(?,?,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setString(2, userMainInfo.getProfilePicture());
			p.setString(3, userMainInfo.getLogoPicture());
			p.setString(4, userMainInfo.getLogoName());
			p.setString(5, userMainInfo.getBio());
			p.setString(6, userMainInfo.getPresentationPicture1());
			p.setString(7, userMainInfo.getPresentationPicture2());
			p.setString(8, userMainInfo.getPresentationPicture3());
			p.setString(9, userMainInfo.getSpecialSkillName1());
			p.setString(10, userMainInfo.getSpecialSkillName2());
			p.setString(11, userMainInfo.getSpecialSkillName3());
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperiencesDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}

}
