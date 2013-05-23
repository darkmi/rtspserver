package com.darkmi.vvs.rs.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
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

import com.darkmi.vvs.service.TransferStatusManager;
import com.darkmi.vvs.utils.ErrorCode;
import com.darkmi.vvs.utils.ServiceException;


/**
 * tstv REST服务.
 */
@Component
@Path("/status")
public class TransferStatusService {
	private static final String CHARSET = ";charset=UTF-8";
	private static Logger logger = LoggerFactory.getLogger(TransferStatusService.class);
	private TransferStatusManager transferStatusManager;

	@Autowired
	public void setTransferStatusManager(TransferStatusManager transferStatusManager) {
		this.transferStatusManager = transferStatusManager;
	}
	@POST
	@Path("findStatus")
	@Produces({ MediaType.APPLICATION_JSON + CHARSET })
	public String getTransferStatus(@FormParam("providerID") String providerID, @FormParam("assetID") String assetID, @FormParam("volumeName") String volumeName,
			@Context HttpServletRequest request) {
		logger.debug("查询状态，providerID为{}", providerID);
		logger.debug("查询状态，assetID为{}", assetID);
		logger.debug("查询状态，volumeName为{}", volumeName);
		//效验请求参数
		if (StringUtils.isBlank(providerID)||StringUtils.isBlank(assetID)||StringUtils.isBlank(volumeName)) {
			throw buildException(Status.BAD_REQUEST, ErrorCode.ParamError.getErrorCode());
		}

		try {
			String status  = transferStatusManager.getAssetStatus(providerID,assetID,volumeName);
			if (StringUtils.isNotBlank(status)) {
				return "{\"state\":\""+status+"\"}";
			} else {
				return "{\"state\":\"none\"}";
			}
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
