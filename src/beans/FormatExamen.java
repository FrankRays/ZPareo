package beans;

public class FormatExamen
{
	private Long id;
	private String nom;
	
	/**
	 * Constructeur
	 */
	public FormatExamen() 
	{
		this.id = null;
		this.nom = null;
	}
	
	/**
	 * Constructeur
	 * 
	 * @param format Un format d'examen.
	 */
	public FormatExamen(FormatExamen format) 
	{
		this.setId(format.getId());
		this.setNom(format.getNom());
	}
	
	/**
	 * Retourne le num�ro d'identification du format d'examen
	 * 
	 * @return id Le num�ro d'identification du format d'examen.
	 */
	public Long getId() 
	{
		return id;
	}
	
	/**
	 * D�fini le num�ro d'identification du format d'examen
	 * 
	 * @param id Un num�ro d'identification.
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 *  Retourne le nom du format d'examen
	 *  
	 * @return nom Le nom du format d'examen.
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * D�fini le nom du format d'examen
	 * 
	 * @param nom Un nom.
	 */
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
 
}
