package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String mobilePhone;
  private String homePhone;
  private String workPhone;
  private String allPhones;
  private String allEmails;
  private String emailSecond;
  private String emailThird;
  private String email;


  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getEmailSecond() {
    return emailSecond;
  }

  public ContactData withEmailSecond(String emailSecond) {
    this.emailSecond = emailSecond;
    return this;
  }

  public String getEmailThird() {
    return emailThird;
  }

  public ContactData withEmailThird(String emailThird) {
    this.emailThird = emailThird;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
