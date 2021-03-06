package squadknowhow.dbmodels;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import squadknowhow.contracts.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class UserShort implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "description")
  private String description;
  @ManyToOne
  private City city;
  @ManyToOne
  private UserCategory skillset;
  @Column(name = "degree")
  private String degree;
  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "skills_users",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {
                  @JoinColumn(name = "skill_id")})
  private List<Skill> skills;
  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "interests_users",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {
                  @JoinColumn(name = "interest_id")})
  private List<Interest> interests;
  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "previous_employments_users", joinColumns = {
          @JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "company_id")})
  private List<Company> previousEmployment;
  @Column(name = "image")
  private String image;
  @Column(name = "rating")
  private double rating;
  @ManyToMany()
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "languages_users",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {
                  @JoinColumn(name = "language_id")})
  private List<Language> languages;
  @Column(name = "activated")
  private boolean activated;
  @Column(name = "dateCreated")
  private String dateCreated;
  @Column(name = "recommendations")
  private int recommendations;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public UserCategory getSkillset() {
    return skillset;
  }

  public void setSkillset(UserCategory skillset) {
    this.skillset = skillset;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skills) {
    this.skills = skills;
  }

  public List<Interest> getInterests() {
    return interests;
  }

  public void setInterests(List<Interest> interests) {
    this.interests = interests;
  }

  public List<Company> getPreviousEmployment() {
    return previousEmployment;
  }

  public void setPreviousEmployment(List<Company> previousEmployment) {
    this.previousEmployment = previousEmployment;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public List<Language> getLanguages() {
    return languages;
  }

  public void setLanguages(List<Language> languages) {
    this.languages = languages;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public static final Comparator<UserShort> DATECREATED_DESC_COMPARATOR = (o1, o2) -> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    Date first = new Date();
    Date second = new Date();

    try {
      first = sdf.parse(o1.getDateCreated());
      second = sdf.parse(o2.getDateCreated());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (first.before(second)) {
      return -1;
    } else if (first.after(second)) {
      return 1;
    } else {
      return 0;
    }
  };

  public static final Comparator<UserShort> RATING_ASC_COMPARATOR = (o1, o2) -> {
    if (o1.getRating() > o2.getRating()) {
      return 1;
    } else if (o1.getRating() < o2.getRating()) {
      return -1;
    } else {
      return 0;
    }
  };

  public static final Comparator<UserShort> RECOMMENDATIONS_ASC_COMPARATOR = (o1, o2) -> {
    if (o1.getRecommendations() > o2.getRecommendations()) {
      return 1;
    } else if (o1.getRecommendations() < o2.getRecommendations()) {
      return -1;
    } else {
      return 0;
    }
  };

  public String getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
  }

  public int getRecommendations() {
    return recommendations;
  }

  public void setRecommendations(int recommendations) {
    this.recommendations = recommendations;
  }
}
