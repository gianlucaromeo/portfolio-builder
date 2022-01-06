package it.unical.demacs.informatica.digitales.app.beans.validation;

public class ProfileValidatorResponse {
	//USER 
	private long id;
	private long userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String dateOfBirth; // YYYY-MM-DD
	private String mainPhoneNumber;
	private String secondaryPhoneNumber;
	private String contactEmail;
	private boolean validation=true;
	
	//USER MAIN INFORMATION
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
	
	//CURRICULUM SKILL
	private String title;
	private int level;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMainPhoneNumber() {
		return mainPhoneNumber;
	}
	public void setMainPhoneNumber(String mainPhoneNumber) {
		this.mainPhoneNumber = mainPhoneNumber;
	}
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}
	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
