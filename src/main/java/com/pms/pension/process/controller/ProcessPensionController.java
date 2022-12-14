package com.pms.pension.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pension.process.exception.AadharNumberNotFound;
import com.pms.pension.process.exception.AuthorizationException;
import com.pms.pension.process.exception.PensionerDetailException;
import com.pms.pension.process.feignclient.AuthorisingClient;
import com.pms.pension.process.model.PensionDetail;
import com.pms.pension.process.model.PensionerInput;
import com.pms.pension.process.model.ProcessPensionInput;
import com.pms.pension.process.model.ProcessPensionResponse;
import com.pms.pension.process.service.ProcessPensionServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class ProcessPensionController {

	@Autowired
	ProcessPensionServiceImpl processPensionServiceImpl;
	
	@Autowired
	private AuthorisingClient authorisingClient;
	
	@PostMapping("/PensionDetail")
	@ApiOperation(notes = "Returns the Pension Details", value = "Find the pension details")
	public PensionDetail getPensionDetail(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody PensionerInput pensionerInput) throws AuthorizationException, PensionerDetailException, AadharNumberNotFound
	{
		System.out.println("In process pension controller");
			if(authorisingClient.authorizeTheRequest(requestTokenHeader)) 
			{
				System.out.println("Hlllo+++authorization suceess");
				return processPensionServiceImpl.CalculatePension(requestTokenHeader,pensionerInput);
			}
			else
			{
				throw new AuthorizationException("Not allowed hhhhhhhhhhhhhhhhhh");
			}
	}

	
	@PostMapping("/ProcessPension")
	@ApiOperation(notes = "Returns the Process Responce Code(10 or 21)", value = "Find Process Responce Code, If Process code is 10 then Suceess and 21 means not success")
	public ProcessPensionResponse getprocessingCode(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody ProcessPensionInput processPensionInput) throws AuthorizationException, AadharNumberNotFound
	{
		if(authorisingClient.authorizeTheRequest(requestTokenHeader)) 
		{
			return processPensionServiceImpl.getCode(requestTokenHeader,processPensionInput);
		}
		else
		{
			throw new AuthorizationException("Not allowed");
		}
		
		
	}
	

	
}
