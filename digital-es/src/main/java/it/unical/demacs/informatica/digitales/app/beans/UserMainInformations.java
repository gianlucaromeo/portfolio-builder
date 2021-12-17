package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class UserMainInformations {

	private long userId; // also primary key
	private String profilePicture;
	private String logoPicture;
	private String logoName;
	private String bio;
	private String presentationPicture1;
	private String presentationPicture2;
	private String presentationPicture3;
	private String specialSkillName1;
	private String specialSkillName2;
	private String specialSkillName3;
	private String specialSkillDescr1;
	private String specialSkillDescr2;
	private String specialSkillDescr3;
	private String facebookLinkRef;
	private String instagramLinkRef;
	private String twitterLinkRef;
	
	public UserMainInformations() {
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getLogoPicture() {
		return logoPicture;
	}
	public void setLogoPicture(String logoPicture) {
		this.logoPicture = logoPicture;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getPresentationPicture1() {
		return presentationPicture1;
	}
	public void setPresentationPicture1(String presentationPicture1) {
		this.presentationPicture1 = presentationPicture1;
	}
	public String getPresentationPicture2() {
		return presentationPicture2;
	}
	public void setPresentationPicture2(String presentationPicture2) {
		this.presentationPicture2 = presentationPicture2;
	}
	public String getPresentationPicture3() {
		return presentationPicture3;
	}
	public void setPresentationPicture3(String presentationPicture3) {
		this.presentationPicture3 = presentationPicture3;
	}
	public String getSpecialSkillName1() {
		return specialSkillName1;
	}
	public void setSpecialSkillName1(String specialSkillName1) {
		this.specialSkillName1 = specialSkillName1;
	}
	public String getSpecialSkillName2() {
		return specialSkillName2;
	}
	public void setSpecialSkillName2(String specialSkillName2) {
		this.specialSkillName2 = specialSkillName2;
	}
	public String getSpecialSkillName3() {
		return specialSkillName3;
	}
	public void setSpecialSkillName3(String specialSkillName3) {
		this.specialSkillName3 = specialSkillName3;
	}
	public String getSpecialSkillDescr1() {
		return specialSkillDescr1;
	}
	public void setSpecialSkillDescr1(String specialSkillDescr1) {
		this.specialSkillDescr1 = specialSkillDescr1;
	}
	public String getSpecialSkillDescr2() {
		return specialSkillDescr2;
	}
	public void setSpecialSkillDescr2(String specialSkillDescr2) {
		this.specialSkillDescr2 = specialSkillDescr2;
	}
	public String getSpecialSkillDescr3() {
		return specialSkillDescr3;
	}
	public void setSpecialSkillDescr3(String specialSkillDescr3) {
		this.specialSkillDescr3 = specialSkillDescr3;
	}
	public String getFacebookLinkRef() {
		return facebookLinkRef;
	}
	public void setFacebookLinkRef(String facebookLinkRef) {
		this.facebookLinkRef = facebookLinkRef;
	}
	public String getInstagramLinkRef() {
		return instagramLinkRef;
	}
	public void setInstagramLinkRef(String instagramLinkRef) {
		this.instagramLinkRef = instagramLinkRef;
	}
	public String getTwitterLinkRef() {
		return twitterLinkRef;
	}
	public void setTwitterLinkRef(String twitterLinkRef) {
		this.twitterLinkRef = twitterLinkRef;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bio, facebookLinkRef, instagramLinkRef, logoName, logoPicture, presentationPicture1,
				presentationPicture2, presentationPicture3, profilePicture, specialSkillDescr1, specialSkillDescr2,
				specialSkillDescr3, specialSkillName1, specialSkillName2, specialSkillName3, twitterLinkRef, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMainInformations other = (UserMainInformations) obj;
		return Objects.equals(bio, other.bio) && Objects.equals(facebookLinkRef, other.facebookLinkRef)
				&& Objects.equals(instagramLinkRef, other.instagramLinkRef) && Objects.equals(logoName, other.logoName)
				&& Objects.equals(logoPicture, other.logoPicture)
				&& Objects.equals(presentationPicture1, other.presentationPicture1)
				&& Objects.equals(presentationPicture2, other.presentationPicture2)
				&& Objects.equals(presentationPicture3, other.presentationPicture3)
				&& Objects.equals(profilePicture, other.profilePicture)
				&& Objects.equals(specialSkillDescr1, other.specialSkillDescr1)
				&& Objects.equals(specialSkillDescr2, other.specialSkillDescr2)
				&& Objects.equals(specialSkillDescr3, other.specialSkillDescr3)
				&& Objects.equals(specialSkillName1, other.specialSkillName1)
				&& Objects.equals(specialSkillName2, other.specialSkillName2)
				&& Objects.equals(specialSkillName3, other.specialSkillName3)
				&& Objects.equals(twitterLinkRef, other.twitterLinkRef) && userId == other.userId;
	}
	
	
	
}
