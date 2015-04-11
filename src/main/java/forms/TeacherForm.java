package forms;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Administrator;
import beans.Teacher;
import beans.Group;
import beans.Matter;
import dao.TeacherDao;

public class TeacherForm
{
	private static final String ADMINISTRATOR_SESSION = "administratorSession";
	private static final String ID_FIELD              = "id";
    private static final String LAST_NAME_FIELD       = "lastName";
    private static final String FIRST_NAME_FIELD      = "firstName";
    private static final String EMAIL_ADDRESS_FIELD   = "emailAddress";
    private static final String PASSWORD_FIELD        = "password";
    private static final String CONFIRMATION_FIELD    = "confirmation";
    private static final String GROUPS_FIELD          = "groups[]";
    private static final String MATTERS_FIELD         = "matters[]";
    private Map<String, String> errors                = new HashMap<String, String>();
	private TeacherDao teacherDao;
	 
	/**
     * Constructor
     * 
     * @param teacherDao
     */
    public TeacherForm(TeacherDao teacherDao) 
    {
    	this.teacherDao = teacherDao;
    }
    
    /**
     * Creates a teacher into database
     * 
     * @param request
     * @return teacher
     */
    public Teacher create(HttpServletRequest request) 
    {
    	String lastName       = getFieldVar(request, LAST_NAME_FIELD);
    	String firstName      = getFieldVar(request, FIRST_NAME_FIELD);
    	String emailAddress   = getFieldVar(request, EMAIL_ADDRESS_FIELD);
    	String password       = getFieldVar(request, PASSWORD_FIELD);
    	String confirmation   = getFieldVar(request, CONFIRMATION_FIELD);
    	String[] groups       = getFieldVars(request, GROUPS_FIELD);
    	String[] matters      = getFieldVars(request, MATTERS_FIELD);
    	Administrator creator = (Administrator) getSessionVar(request, ADMINISTRATOR_SESSION);
    	Teacher teacher       = new Teacher();
    	
        try 
        {
            treatLastName(lastName, teacher);
            treatFirstName(firstName, teacher);
            treatEmailAddress(emailAddress, teacher);
            treatPassword(password, confirmation, teacher);
            treatTeacher(teacher);
            treatsMatters(matters, teacher);
        	treatsGroups(groups, teacher);
        	treatCreator(creator, teacher);
        	
            if (errors.isEmpty()) teacherDao.create(teacher);
            
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }

        return teacher;
    }
    
    /**
     * Searches one or more teachers into database
     * 
     * @param request
     * @return teachers
     */
    public Set<Teacher> search(HttpServletRequest request) 
    {
    	String id             = getFieldVar(request, ID_FIELD);
    	String lastName       = getFieldVar(request, LAST_NAME_FIELD);
    	String firstName      = getFieldVar(request, FIRST_NAME_FIELD);
    	String emailAddress   = getFieldVar(request, EMAIL_ADDRESS_FIELD);
    	Set<Teacher> teachers = new HashSet<Teacher>();
    	Teacher teacher       = new Teacher();
    	
    	if (id != null) teacher.setId(Long.parseLong(id));
    
    	teacher.setLastName(lastName);
    	teacher.setFirstName(firstName);
    	teacher.setEmailAddress(emailAddress);
    	teachers = teacherDao.search(teacher);
        
    	return teachers;
    }
    
    /**
     * Returns a teacher into database
     * 
     * @param request
     * @return teacher
     */
    public Teacher get(HttpServletRequest request)
    {
    	String id       = getFieldVar(request, ID_FIELD);
    	Teacher teacher = new Teacher();
    	
    	treatId(id, teacher);
    	teacher = teacherDao.get(teacher);
    	
    	return teacher;
    }
    
    /**
     * Edits a teacher into database
     * 
     * @param request
     * @return teacher
     */
    public Teacher edit(HttpServletRequest request) 
    {
    	String id            = getFieldVar(request, ID_FIELD);
    	String lastName      = getFieldVar(request, LAST_NAME_FIELD);
    	String firstName     = getFieldVar(request, FIRST_NAME_FIELD);
    	String emailAddress  = getFieldVar(request, EMAIL_ADDRESS_FIELD);
    	String password      = getFieldVar(request, PASSWORD_FIELD);
    	String confirmation  = getFieldVar(request, CONFIRMATION_FIELD);
    	String[] groups      = getFieldVars(request, GROUPS_FIELD);
    	String[] matters     = getFieldVars(request, MATTERS_FIELD);
    	Administrator editor = (Administrator) getSessionVar(request, ADMINISTRATOR_SESSION);
    	Teacher teacher      = new Teacher();
    	
        try 
        {
        	treatId(id, teacher);
            treatLastName(lastName, teacher);
            treatFirstName(firstName, teacher);
            treatEmailAddress(emailAddress, teacher);
            treatTeacher(teacher);
            treatsMatters(matters, teacher);
            treatsGroups(groups, teacher);
            treatEditor(editor, teacher);
            
            if ((password != null) || (confirmation != null)) treatPassword(password, confirmation, teacher);
            
            if (errors.isEmpty()) teacherDao.edit(teacher);
            
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }

        return teacher;
    }
    
    /**
     * Deletes a teacher into database
     * 
     * @param request
     */
    public void delete(HttpServletRequest request)
    {
    	String id            = getFieldVar(request, ID_FIELD);
    	Administrator editor = (Administrator) getSessionVar(request, ADMINISTRATOR_SESSION);
    	Teacher teacher      = new Teacher();
    	
    	treatId(id, teacher);
    	treatEditor(editor, teacher);
    	teacherDao.delete(teacher);

    }
    
    /**
     * Checks teacher's login into database
     * 
     * @param request
     * @return teacher
     */
    public Teacher checkLogin(HttpServletRequest request) 
    {
    	String emailAddress = getFieldVar(request, EMAIL_ADDRESS_FIELD);
    	String password     = getFieldVar(request, PASSWORD_FIELD);
    	Teacher teacher     = new Teacher();
    	
        try 
        {
        	treatEmailAddress(emailAddress, teacher);
        	teacher.setPassword(cryptPassword(password));
        	teacher = teacherDao.checkLogin(teacher);
        	treatLogin(teacher);
        	teacher.setPassword(cryptPassword(password));
        	teacher.setEmailAddress(emailAddress);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }

        return teacher;
    }
    
    /**
     *  Treats teacher's id
     *  
     * @param id
     * @param teacher
     */
    private void treatId(String id, Teacher teacher)
    {
    	try
    	{
    		validateId(id);
    		teacher.setId(Long.parseLong(id));
    	}
    	catch (Exception e) 
    	{
            setError(ID_FIELD, e.getMessage());
        }
    	
    }
    /**
     *  Treats teacher's last name
     *  
     * @param lastName
     * @param teacher
     */
    private void treatLastName(String lastName, Teacher teacher)
    {
    	try 
    	{
            validateLastName(lastName);
        } 
    	catch (Exception e) 
    	{
            setError(LAST_NAME_FIELD, e.getMessage());
        }
    	
    	teacher.setLastName(lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase());
    }
    
    /**
     *  Treat teacher's first name
     *  
     * @param firstName
     * @param teacher
     */
    private void treatFirstName(String firstName, Teacher teacher)
    {
    	try 
    	{
            validateFirstName(firstName);
        } 
    	catch (Exception e) 
    	{
            setError(FIRST_NAME_FIELD, e.getMessage());
        }
    	
    	teacher.setFirstName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase());
    }
    
    /**
     *  Treats teacher's login
     *  
     * @param teacher
     */
    private void treatLogin(Teacher teacher) 
    {
    	try 
    	{
    		validateLogin(teacher);
        } 
    	catch (Exception e) 
    	{
            setError("connexion", e.getMessage());
        }
    }
    
    /**
     *  Treats teacher's email address
     *  
     * @param emailAddress
     * @param teacher
     */
    private void treatEmailAddress(String emailAddress, Teacher teacher)  
    {
    	try 
    	{
            validateEmailAddress(emailAddress);
        } 
    	catch (Exception e) 
    	{
            setError(EMAIL_ADDRESS_FIELD, e.getMessage());
        }
    	
    	teacher.setEmailAddress(emailAddress.trim().toLowerCase());
    }
    
    /** 
     * Treats teacher's password & confirmation
     *  
     * @param password
     * @param confirmation
     * @param teacher
     */
    private void treatPassword(String password, String confirmation, Teacher teacher)  
    {		
    	try 
    	{
    		validatePassword(password, confirmation);
            password = cryptPassword(password);
    	} 
    	catch (Exception e)
    	{
    		setError(PASSWORD_FIELD, e.getMessage());
    		setError(CONFIRMATION_FIELD, null);
    	} 

    	teacher.setPassword(password);
    }

    /**
     *  Treats teacher's groups
     *  
     * @param groups
     * @param teacher 
     */
    private void treatsGroups(String[] groups, Teacher teacher) 
    {
    	Set<Group> groupList = new HashSet<Group>();
    	
    	if (validatesGroups(groups))
    	{
    		for(int i = 0; i < groups.length; i++) 
        	{
        		Group group = new Group();
        		group.setId(Long.parseLong(groups[i]));
        		groupList.add(group);
        	}
        	
        	teacher.setGroups(groupList);
    	}
    }
    
    /**
     *  Treats teacher's matters
     *  
     * @param matters
     * @param teacher
     */
    private void treatsMatters(String[] matters, Teacher teacher) 
    {
    	Set<Matter> matterList = new HashSet<Matter>();
    	
    	if (validateMatters(matters))
    	{
    		for (int i = 0; i < matters.length; i++) 
            {
            	Matter matiere = new Matter();
            	matiere.setId(Long.parseLong(matters[i]));
            	matterList.add(matiere);
            }
    		
    		teacher.setMatters(matterList);
    	}
    }
    
    /**
     *  Treats a teacher
     *  
     * @param teacher
     */
    private void treatTeacher(Teacher teacher) 
    {
    	try 
    	{
    		validateTeacher(teacher);
        } 
    	catch (Exception e) 
    	{
            setError("teacher", e.getMessage());
        }
    }
    
    /**
     *  Treats teacher's creator
     *  
     * @param creator
     */
    private void treatCreator(Administrator creator, Teacher teacher) 
    {
    	try 
    	{
    		validateCreator(creator);
        } 
    	catch (Exception e) 
    	{
            setError("administrateur", e.getMessage());
        }
    	
    	teacher.setCreator(creator);	
    }
    
    /**
     *  Treats teacher's editor
     *  
     * @param editor
     */
    private void treatEditor(Administrator editor, Teacher teacher) 
    {
    	try 
    	{
    		validateCreator(editor);
        } 
    	catch (Exception e) 
    	{
            setError("administrateur", e.getMessage());
        }
    	
    	teacher.setEditor(editor);
    }
    
    /**
     * Validates teacher's id
     * 
     * @param id
     * @throws Exception
     */
    private void validateId(String id) throws Exception 
    {
        if ((id == null)) throw new Exception("Le numéro d'identification est nul");  
    }
    
    /**
     * Validates teacher's last name
     * 
     * @param lastName
     * @throws Exception
     */
    private void validateLastName(String lastName) throws Exception 
    {
        if ((lastName == null) || (lastName.length() < 2) || (lastName.length() > 50)) 
        {
            throw new Exception("Veuillez entrer un nom de 2 à 50 caractères");
        }
    }
    
    /**
     * Validates teacher's first name
     * 
     * @param firstName
     * @throws Exception
     */
    private void validateFirstName(String firstName) throws Exception 
    {
        if ((firstName == null) || (firstName.length() < 2) || (firstName.length() > 50)) 
        {
            throw new Exception( "Veuillez entrer un prenom de 2 à 50 caractères" );
        }
    }
    
    /**
     * Validates teacher's email address
     * 
     * @param emailAddress
     * @throws Exception
     */
    private void validateEmailAddress(String emailAddress) throws Exception 
    {
        if ((emailAddress == null) || (emailAddress.length() < 8) || (emailAddress.length() > 100 ) || (!emailAddress.matches("[a-zA-Z0-9@.-_]+@[a-zA-Z.]{2,20}.[a-zA-Z]{2,3}"))) 
        {
            throw new Exception("Veuillez entrer une adresse mail correcte");
        }
    }
    
    /**
     * Validates teacher's group
     * 
     * @param groups
     */
    private boolean validatesGroups(String[] groups) 
    {
        return (groups != null);    
    }
    
    /**
     * Validates teacher's matters
     * 
     * @param matters
     */
    private boolean validateMatters(String[] matters) 
    {
        return (matters != null); 
     
    }
    
    /**
     * Validates teacher's password
     * 
     * @param password
     * @param confirmation
     * @throws Exception
     */
    private void validatePassword(String password, String confirmation) throws Exception 
    {
        if ((password == null) || (password.length() < 4) || (confirmation == null) || (confirmation.length() < 4)) 
        {
            throw new Exception("Veuillez entrez un mot de passe plus fort.");
        } 
        else if (!password.equals(confirmation)) 
        {
            throw new Exception("Les mots de passes sont différents");
        } 
    }
    
    /**
     * Validates a teacher
     * 
     * @param teacher
     * @throws Exception
     */
    private void validateTeacher(Teacher teacher) throws Exception 
    {
        if (teacherDao.check(teacher) != 0) throw new Exception("Ce professeur existe déja"); 
    }
    
    /**
     * Validates teacher's login
     * 
     * @param teacher
     * @throws Exception
     */
    private void validateLogin(Teacher teacher) throws Exception 
    {
        if (teacher.getId() == null) throw new Exception("Votre adresse mail ou votre mot de passe est incorrect"); 
    }
    
    /**
     * Validates teacher's creator
     * 
     * @param creator
     * @throws Exception
     */
    private void validateCreator(Administrator creator) throws Exception 
    {
        if (creator.getId() == null) 
        {
            throw new Exception("Administrateur inconnu");
        }
    }
    
    /**
     * Cryptes a password
     * 
     * @param password
     * @return cryptedPassword
     */
    private String cryptPassword(String password)
    {
    	StringBuffer cryptedPassword = new StringBuffer();
    	
    	try 
    	{
    		MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            
            for (int i = 0; i < byteData.length; i++) 
            {
            	cryptedPassword.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
    	} 
    	catch (Exception e)
    	{
    		
    	} 

    	return cryptedPassword.toString();
    }
    
    /**
     * Returns errors
     * 
     * @return errors
     */
    public Map<String, String> getErrors() 
    {
        return errors;
    }
    
    /**
     * Set an error
     * 
     * @param field
     * @param message
     */
    private void setError(String field, String message)
    {
        errors.put(field, message);
    }

    /**
     * Returns a field variable
     * 
     * @param request
     * @param fieldVar
     * @return var
     */
    private static String getFieldVar(HttpServletRequest request, String fieldVar) 
    {
        String var = request.getParameter(fieldVar);
   
        return (((var == null) || (var.trim().length() == 0)) ? null : var.trim());
    }
    
    /**
     * Returns a session variable
     * 
     * @param request
     * @param sessionVar
     * @return object
     */
    private static Object getSessionVar(HttpServletRequest request, String sessionVar) 
    {
    	HttpSession session = request.getSession();
    	Object object       = session.getAttribute(sessionVar);
    	
        return ((object == null) ? null : object);
    }
    
    /**
     * Returns field variables
     * 
     * @param request
     * @param fieldVar
     * @return var
     */
    private static String[] getFieldVars(HttpServletRequest request, String fieldVar) 
    {
    	return request.getParameterValues(fieldVar);
    }
    
}