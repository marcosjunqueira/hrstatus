/*
    Copyright (C) 2012  Filippe Costa Spolti

	This file is part of Hrstatus.

    Hrstatus is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.hrstatus.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import br.com.hrstatus.dao.UsersInterface;
import br.com.hrstatus.model.Users;

/*
 *Spring Framework 
 *Customization, rewrite LoginSuccessHandler
 */

@Service
public class LoginSucessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private UsersInterface userDAO;
	

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

    	UserInfo userInfo = new UserInfo();
    	DateUtils dt = new DateUtils();
     	
    	Users user = userDAO.getUserByID(userInfo.getLoggedUsername());
    	String lastLoginTime = dt.getTime();
    	user.setLastLogin(lastLoginTime);
    	Logger.getLogger(getClass()).info("[ " + userInfo.getLoggedUsername() + " ] Successful login at " + lastLoginTime);
    	userDAO.updateUser(user);
    	
    	
        super.setDefaultTargetUrl("/home");
        
        request.getSession().setMaxInactiveInterval(30*30);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}