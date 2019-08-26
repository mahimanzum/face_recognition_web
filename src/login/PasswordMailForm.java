package login;

import javax.servlet.http.HttpServletRequest;

public class PasswordMailForm
{
	private String m_username;
    private String m_mailAddress;
    private String m_secretQuestion;
    private String m_secretAnswer;
    private int reqType;
    
    public String getSecretQuestion()
    {
        return m_secretQuestion;
    }

    public void setSecretQuestion(String p_secretQuestion)
    {
        m_secretQuestion = p_secretQuestion;
    }

    public String getSecretAnswer()
    {
        return m_secretAnswer;
    }

    public void setSecretAnswer(String p_secretAnswer)
    {
        m_secretAnswer = p_secretAnswer;
    }

    public PasswordMailForm()
    {
    }

    public String getUsername()
    {
        return m_username;
    }

    public void setUsername(String p_username)
    {
        m_username = p_username;
    }

    public String getMailAddress()
    {
        return m_mailAddress;
    }

    public void setMailAddress(String p_mailAddress)
    {
        m_mailAddress = p_mailAddress;
    }


	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

    
}