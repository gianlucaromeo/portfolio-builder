package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserMainInformationsDAOImpl extends DAOImpl implements DAO<UserMainInformations> {
	private static UserMainInformationsDAOImpl instance= null;
	
	private UserMainInformationsDAOImpl() {}
	
	public static UserMainInformationsDAOImpl getInstance(){
		if(instance==null)
			instance= new UserMainInformationsDAOImpl();
		return instance;
	}

	@Override
	public String create(UserMainInformations userMainInfo) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO users_main_informations VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
		
			p.setString(1, userMainInfo.getProfilePicture());
			p.setString(2, userMainInfo.getLogoPicture());
			p.setString(3, userMainInfo.getLogoName());
			p.setString(4, userMainInfo.getBio());
			p.setString(5, userMainInfo.getPresentationPicture1());
			p.setString(6, userMainInfo.getPresentationPicture2());
			p.setString(7, userMainInfo.getPresentationPicture3());
			p.setString(8, userMainInfo.getSpecialSkillName1());
			p.setString(9, userMainInfo.getSpecialSkillName2());
			p.setString(10, userMainInfo.getSpecialSkillName3());
			p.setString(11, userMainInfo.getSpecialSkillDescr1());
			p.setString(12, userMainInfo.getSpecialSkillDescr2());
			p.setString(13, userMainInfo.getSpecialSkillDescr3());
			p.setString(14, userMainInfo.getFacebookLinkRef());
			p.setString(15, userMainInfo.getInstagramLinkRef());
			p.setString(16, userMainInfo.getTwitterLinkRef());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[UserMainInformationsDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}

}
