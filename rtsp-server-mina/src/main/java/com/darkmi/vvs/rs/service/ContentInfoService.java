package com.darkmi.vvs.rs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.xwork.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darkmi.vvs.rs.dto.ContentInfoDTO;
import com.darkmi.vvs.service.TransferContentManager;
import com.darkmi.vvs.utils.ErrorCode;
import com.darkmi.vvs.utils.ServiceException;


/**
 * tstv REST服务.
 */
@Component
@Path("/content")
public class ContentInfoService {
	private static final String CHARSET = ";charset=UTF-8";
	private static Logger logger = LoggerFactory.getLogger(ContentInfoService.class);
	private TransferContentManager transferContentManager;

	@Autowired
	public void setTransferContentManager(TransferContentManager transferContentManager) {
		this.transferContentManager = transferContentManager;
	}
	@GET
	@Path("contentInfo")
	@Produces({ MediaType.APPLICATION_JSON + CHARSET })
	public List<ContentInfoDTO> GetContentInfo(@FormParam("providerID") String providerID, @FormParam("assetID") String assetID, @FormParam("volumeName") String volumeName,
			@Context HttpServletRequest request) {
		logger.debug("查询状态，providerID为{}", providerID);
		logger.debug("查询状态，assetID为{}", assetID);
		logger.debug("查询状态，volumeName为{}", volumeName);
		//效验请求参数
		if (StringUtils.isBlank(providerID)||StringUtils.isBlank(assetID)||StringUtils.isBlank(volumeName)) {
			throw buildException(Status.BAD_REQUEST, ErrorCode.ParamError.getErrorCode());
		}
		
		try {
			List<ContentInfoDTO>  contentInfoDtoList = transferContentManager.getContentInfo(providerID,assetID,volumeName);
			return contentInfoDtoList;
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, e.getMessage());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, ErrorCode.SystemError.getErrorCode());
		}
	}
	@DELETE
	@Path("delete")
	@Produces({ MediaType.APPLICATION_JSON + CHARSET })
	public String DeleteContent(@FormParam("providerID") String providerID, @FormParam("assetID") String assetID, @FormParam("volumeName") String volumeName,
			@FormParam("reasonCode") Integer reasonCode,@Context HttpServletRequest request) {
		logger.debug("查询状态，providerID为{}", providerID);
		logger.debug("查询状态，assetID为{}", assetID);
		logger.debug("查询状态，volumeName为{}", volumeName);
		//效验请求参数
		if (StringUtils.isBlank(providerID)||StringUtils.isBlank(assetID)||StringUtils.isBlank(volumeName)) {
			throw buildException(Status.BAD_REQUEST, ErrorCode.ParamError.getErrorCode());
		}
		
		try {
			transferContentManager.deleteContent(providerID,assetID,volumeName,reasonCode);
			return "ok";
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, e.getMessage());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, ErrorCode.SystemError.getErrorCode());
		}
	}
	@POST
	@Path("contentChecksum")
	@Produces({ MediaType.APPLICATION_JSON + CHARSET })
	public void GetContentChecksum(@FormParam("providerID") String providerID, @FormParam("assetID") String assetID, @FormParam("volumeName") String volumeName,
			@FormParam("responseURL") String responseURL,@Context HttpServletRequest request) {
		logger.debug("查询状态，providerID为{}", providerID);
		logger.debug("查询状态，assetID为{}", assetID);
		logger.debug("查询状态，volumeName为{}", volumeName);
		//效验请求参数
		if (StringUtils.isBlank(providerID)||StringUtils.isBlank(assetID)||StringUtils.isBlank(volumeName)) {
			throw buildException(Status.BAD_REQUEST, ErrorCode.ParamError.getErrorCode());
		}

		try {
			
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, e.getMessage());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw buildException(Status.BAD_REQUEST, ErrorCode.SystemError.getErrorCode());
		}
	}
	/**
	 * 创建WebApplicationException, 使用标准状态码与自定义信息.
	 */
	private WebApplicationException buildException(Status status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type(MediaType.APPLICATION_JSON)
				.build());
	}

}
