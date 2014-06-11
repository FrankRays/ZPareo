package beans;

import java.util.Set;

public class Examen implements Comparable<Examen>
{
	private Long id;
	private String nom;
	private String date;
	private FormatExamen format;
	private Professeur professeur;
	private Groupe groupe;
	private Matiere matiere;
	private Set<Note> listeNotes;
	private Float moyenneGenerale;
	private Float coefficient;
	
	/**
	 * Constructeur
	 */
	public Examen()
	{
		this.id = null;
		this.nom = null;
		this.date = null;
		this.format = null;
		this.professeur = null;
		this.groupe = null;
		this.matiere = null;
		this.listeNotes = null;
		this.moyenneGenerale = null;
		this.coefficient = null;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param examen Un examen.
	 */
	public Examen(Examen examen)
	{
		this.setId(examen.getId());
		this.setNom(examen.getNom());
		this.setDate(examen.getDate());
		this.setFormat(examen.getFormat());
		this.setProfesseur(examen.getProfesseur());
		this.setGroupe(examen.getGroupe());
		this.setMatiere(examen.getMatiere());
		this.setListeNotes(examen.getListeNotes());
		this.setMoyenneGenerale(examen.getMoyenneGenerale());
		this.setCoefficient(examen.getCoefficient());
	}
	
	/**
	 * Retourne le num�ro d'identification de l'examen
	 * 
	 * @return id Le num�ro d'identification de l'examen.
	 */
	public Long getId() 
	{
		return id;
	}
	
	/**
	 * D�fini le num�ro d'identification de l'examen
	 * 
	 * @param id Un num�ro d'identification.
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}

	/**
	 * Retourne le nom de l'examen
	 * 
	 * @return nom Le nom de l'examen.
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * D�fini le nom de l'examen
	 * 
	 * @param nom Un nom.
	 */
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	/**
	 * Retourne la date de l'examen
	 * 
	 * @return date La date de l'examen.
	 */
	public String getDate() 
	{
		return date;
	}

	/**
	 * D�fini la date de l'examen
	 * 
	 * @param date Une date.
	 */
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	/**
	 * Retourne le format de l'examen
	 * 
	 * @return format Le format de l'examen.
	 */
	public FormatExamen getFormat() 
	{
		return format;
	}
	
	/**
	 * D�fini le format de l'examen
	 * 
	 * @param format Un format d'examen.
	 */
	public void setFormat(FormatExamen format) 
	{
		this.format = format;
	}

	/**
	 * Retourne le professeur responsable de l'examen
	 * 
	 * @return professeur Le professeur responsable de l'examen.
	 */
	public Professeur getProfesseur()
	{
		return professeur;
	}
	
	/**
	 * D�fini le professeur responsable de l'examen
	 * 
	 * @param professeur Un professeur.
	 */
	public void setProfesseur(Professeur professeur) 
	{
		this.professeur = professeur;
	}
	
	/**
	 * Retourne le groupe de l'examen
	 * 
	 * @return groupe Le groupe de l'examen.
	 */
	public Groupe getGroupe()
	{
		return groupe;
	}
	
	/**
	 * D�fini le groupe de l'examen
	 * 
	 * @param groupe Un groupe.
	 */
	public void setGroupe(Groupe groupe)
	{
		this.groupe = groupe;
	}
	
	/**
	 * Retourne la mati�re de l'examen
	 * 
	 * @return matiere La mati�re de l'examen.
	 */
	public Matiere getMatiere()
	{
		return matiere;
	}
	
	/**
	 * D�fini la mati�re de l'examen
	 * 
	 * @param matiere Une mati�re.
	 */
	public void setMatiere(Matiere matiere)
	{
		this.matiere = matiere;
	}
	
	/**
	 * Retourne la liste de notes de l'examen
	 * 
	 * @return listeNotes La liste des notes de l'examen
	 */
	public Set<Note> getListeNotes()
	{
		return listeNotes;
	}
	
	/**
	 * D�fini la liste de notes de l'examen
	 * 
	 * @param listeNotes Une liste de notes.
	 */
	public void setListeNotes(Set<Note> listeNotes) 
	{
		this.listeNotes = listeNotes;
	}
	
	/**
	 * Retourne la moyenne g�n�rale de l'examen
	 * 
	 * @return moyenneGenerale La moyenne g�n�rale de l'examen.
	 */
	public Float getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * D�fini la moyenne g�n�rale de l'examen
	 * 
	 * @param moyenneGenerale Une moyenne g�n�rale.
	 */
	public void setMoyenneGenerale(Float moyenneGenerale) 
	{
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * Retourne le coefficient de l'examen
	 * 
	 * @return coefficient Le coefficient de l'examen.
	 */
	public Float getCoefficient()
	{
		return coefficient;
	}
	
	/**
	 * D�fini le coefficient de l'examen
	 * 
	 * @param coefficient Un coefficient.
	 */
	public void setCoefficient(Float coefficient) 
	{
		this.coefficient = coefficient;
	}
	
	/**
	 * Compare le num�ro d'identification de deux examens
	 * 
	 * @param examen2 Un examen � comparer.
	 */
	@Override
	public int compareTo(Examen examen2) 
	{
		int compId = this.getId().compareTo(examen2.getId());
        if(compId != 0) return compId;
        return 0;
	}
	
}
