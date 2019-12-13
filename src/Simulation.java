import java.awt.Graphics;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    // cette partie sert a simuler le fonctionnement du reste de la plateforme
    // donc il faut qu'il contienne (simule) toute les fonctions comme la demande de
    // position et celle de l'heure
    // pour pouvoir faire plus tard une simulation ou le docteur pourra ce deplacer
    // en temps reel dans la simulation
    // cest aussi elle qui contient notre partie du projet representer par
    // EmploiDuTemps

    public JunctionInformation schedule = new JunctionInformation(this);
    public List<Docteur> docList = new ArrayList<Docteur>();
    public List<Patient> malades = new ArrayList<Patient>();
    public List<RendezVous> listRDV = new ArrayList<RendezVous>();
    private LocalDateTime DateHeureActuel;

    public Simulation() {

        this.DateHeureActuel = LocalDateTime.now();

        Docteur papa = new Docteur("doc", "gyneco", "mentoniste", new Position(10, 10));
        Patient fils = new Patient(new Position(11, 3), "krusty", "le clown");
        RendezVous papafils = new RendezVous(DateHeureActuel.toLocalDate(), papa.getHoraires(0).of(10,0), Duration.ofMinutes(45),
                papa, new Diagnostic(1, "gastro", fils));

        this.docList.add(papa);
        this.addPatient(fils);
        this.listRDV.add(papafils);

    }

    public void addPatient(Patient nouveauxPatient) {
        this.malades.add(nouveauxPatient);
    }

    public List<Docteur> getDoc() {
        return docList;
    }

    public void addDoc(Docteur doc) {
        this.docList.add(doc);
    }

    public List<Patient> getMalades() {
        return malades;
    }

    public void setMalades(List<Patient> malades) {
        this.malades = malades;
    }

    public LocalDateTime getDateHeureActuel() {
        return DateHeureActuel;
    }

    public void setDateHeureActuel(LocalDateTime dateHeureActuel) {
        DateHeureActuel = dateHeureActuel;
    }

    public List<RendezVous> getListRDV() {
        return listRDV;
    }

    public void setListRDV(List<RendezVous> listRDV) {
        this.listRDV = listRDV;
    }

    public ArrayList<Position> montrerScheduleSelonDocteur(Docteur docteurChoisit) {
        ArrayList<Position> ListePositionRendezVous = new ArrayList<Position>();
        if (listRDV.size() > 0) {
            for (RendezVous rdv : listRDV) {

                if (rdv.getMedecinAffecte().equals(docteurChoisit)) {
                    System.out.println("simultaion : montrerScheduleSelonDocteur() : affichage : " + rdv);
                    ListePositionRendezVous.add(rdv.getLieu());
                    
                }
            }
        }
        return ListePositionRendezVous;
    }

    public String retourStringDesRDV(Docteur docteurChoisit) {
        String result = " ";
        for (RendezVous rdv : listRDV) {
            if (rdv.getMedecinAffecte().equals(docteurChoisit)) {
                result += rdv.toString() + "\n";
            }
        }
        return result;
    }

    public Patient retourPatientSelonID(String idPatient) {

        for (Patient pat : malades) {
            if (pat.getIDPatient() == idPatient) {
                return pat;
            }
        }
        return null;
    }

    public void nouveauDiagnostic(int crit, String descript, String patientid) {
        schedule.recuperationNouveauxDiagnostic(crit, descript, patientid);
    }

    public void addRdv(List<RendezVous> listeTriee) {
        for (RendezVous rendezVous : listeTriee) {

            System.out.println("simulation : addRdv() : " + rendezVous);
            listRDV.add(rendezVous);
            System.out.println("simulation : addRdv() : " + retourStringDesRDV(rendezVous.getMedecinAffecte()));
        }

    }

}