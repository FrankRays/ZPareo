package filtres;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/pi/*")
public class ProfesseurFiltre implements Filter 
{
	private static final String URL_CONNEXION          = "/connexion";
	private static final String ATT_SESSION_PROFESSEUR = "sessionProfesseur";
    
    public void init(FilterConfig config) throws ServletException
    {
    }

    /**
     * Filtre l'ensemble des pages commencant par "/pi/*"
     * 
     * @param req La requete.
     * @param rep La r�ponse.
     * @param chain Le pattern URL.
     */
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        HttpSession session = request.getSession();
        String chemin = request.getRequestURI().substring(request.getContextPath().length());
        
        if ((chemin.startsWith("/ressources")) || (chemin.startsWith("/connexion"))) 
        {
        	chain.doFilter(request, response);
            return;
        }
        
        if (session.getAttribute(ATT_SESSION_PROFESSEUR) == null)
        {
        	request.getRequestDispatcher(URL_CONNEXION).forward(request, response);
        } 
        else 
        {
            chain.doFilter(request, response);
        }
    }

    public void destroy()
    {
    }
}