package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import fr.isen.m1.schedule.builder.SocialDetailsBuilder;

/**
 * docteur
 */
@Entity
@Table(name = "doctor")
@NamedQueries({@NamedQuery(name = "Doctor.findAll", query = "SELECT doctor FROM Doctor doctor"),
        @NamedQuery(name = "Doctor.findByName",
                query = "SELECT doctor FROM Doctor doctor INNER JOIN doctor.socialDetails socialDetails WHERE socialDetails.lastName = :lastName AND socialDetails.firstName = :firstName")})
public class Doctor implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -3373115033031181964L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_socdet")
    private SocialDetails socialDetails;
    @Column(name = "rpps")
    private String cdhp;
    @Transient
    private Position realPosition;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_gpsc")
    private Position startPosition;
    @Transient
    private LocalTime[] horaires = new LocalTime[] {LocalTime.of(8, 0), LocalTime.of(12, 0),
            LocalTime.of(13, 0), LocalTime.of(18, 0)};

    public Doctor() {
    }

    public Doctor(String nom, String prenom, String cdhp, Position lieuDeDepart) {

        this.socialDetails = new SocialDetailsBuilder().setFirstName(prenom).setLastName(nom).build();
        this.cdhp = cdhp;
        this.startPosition = lieuDeDepart;
        this.realPosition = lieuDeDepart;
    }


    public String getLastname() {
        return this.socialDetails.getLastName();
    }

    public void setNom(String nom) {
        this.socialDetails.setLastName(nom);
    }

    public String getFirstname() {
        return this.socialDetails.getFirstName();
    }

    public void setPrenom(String prenom) {
        this.socialDetails.setFirstName(prenom);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialDetails getDetails() {
        return socialDetails;
    }

    public void setDetails(SocialDetails details) {
        this.socialDetails = details;
    }



    @Override
    public String toString() {
        return "Docteur " + this.getLastname() + " " + this.getFirstname();
    }

    public Position getEmplacement() {
        return realPosition;
    }

    public void setEmplacement(Position emplacement) {
        this.realPosition = emplacement;
    }



    public LocalTime getHoraires(int index) {

        return horaires[index];
    }

    public void setHoraires(LocalTime[] horaires) {
        this.horaires = horaires;
    }

    public Position getLieuDeDepart() {
        return startPosition;
    }

    public void setLieuDeDepart(Position lieuDeDepart) {
        this.startPosition = lieuDeDepart;
    }

    /**
     * @return the cdhp
     */
    public String getCdhp() {
        return cdhp;
    }

    /**
     * @param cdhp the cdhp to set
     */
    public void setCdhp(String cdhp) {
        this.cdhp = cdhp;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(socialDetails, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doctor other = (Doctor) obj;
        return Objects.equals(socialDetails, other.socialDetails) && Objects.equals(id, other.id);
    }

}
