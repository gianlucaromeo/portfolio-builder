package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserMainInformationDAOImplTest {
	private static UserMainInformations userInfo;
	private static UserMainInformationsDAOImpl userInfoDAOImpl = UserMainInformationsDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		userInfo=new UserMainInformations();
		userInfo.setUserId(2);
		userInfo.setLogoName("MyName");
		userInfo.setBio("This is a biography");
		userInfo.setProfilePicture("/profile.jpg");	
		userInfo.setPresentationPicture1("/img1.jpg");
		userInfo.setPresentationPicture2("/img3.jpg");
		userInfo.setPresentationPicture3("/img3.jpg");
		userInfo.setSpecialSkillName1("Java dev");
		userInfo.setSpecialSkillName2("c++ dev");
		userInfo.setSpecialSkillName3("python dev");
		userInfo.setSpecialSkillDescr1("good java developer");
		userInfo.setSpecialSkillDescr2("good c++ developer");
		userInfo.setSpecialSkillDescr3("good python developer");
		userInfo.setFacebookLinkRef("facebook.it/...");
		userInfo.setInstagramLinkRef("Instagram.com/...");
		userInfo.setTwitterLinkRef("twitter.com/...");
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumSkillToDatabase() {
		System.out.println("[checkAddCurriculumSkillToDatabase]");
		String res = userInfoDAOImpl.create(userInfo);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkFindBannedUserId() {
		System.out.println("[checkUserMainInformationId]");
		long id = userInfoDAOImpl.findId(userInfo);
		assertEquals(userInfo.getUserId(), id);
	}
	

}
