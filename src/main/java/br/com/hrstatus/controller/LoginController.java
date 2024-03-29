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

package br.com.hrstatus.controller;

/*
 * @author spolti
 */

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.hrstatus.utils.PropertiesLoaderImpl;
import br.com.hrstatus.utils.UserInfo;

@Resource
public class LoginController {
	
	private Result result;

	UserInfo userInfo = new UserInfo();
	
	public LoginController(Result result) {
		this.result = result;
	}
	
	@SuppressWarnings("static-access")
	@Get("/login")
	public void login(String message){
		Logger.getLogger(getClass()).info("[ Not Logged ] URI Called: /login");
		PropertiesLoaderImpl load = new PropertiesLoaderImpl();
	    String version = load.getValor("version"); 
	    result.include("version",version);
		result.include("info",message);

	}
}