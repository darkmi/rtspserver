package com.darkmi.vvs.rs.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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

import com.darkmi.vvs.rs.dto.VolumeInfoDTO;
import com.darkmi.vvs.service.TransferContentManager;
import com.darkmi.vvs.utils.ErrorCode;
import com.darkmi.vvs.utils.ServiceException;


/**
 * REST服务.
 */
@Component
@Path("/volume")
public class VolumeInfoService {
	private static Logger logger = LoggerFactory.getLogger(VolumeInfoService.class);
	private TransferContentManager transferContentManager;

	@Autowired
	public void setTransferContentManager(TransferContentManager transferContentManager) {
		this.transferContentManager = transferContentManager;
	}
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_XML })
	public VolumeInfoDTO GetVolumeInfo( @FormParam("volumeName") String volumeName,
			@Context HttpServletRequest request) {
		logger.debug("查询状态，volumeName为{}", volumeName);
		//效验请求参数
		if (StringUtils.isBlank(volumeName)) {
			throw buildException(Status.BAD_REQUEST, ErrorCode.ParamError.getErrorCode());
		}
		
		try {
			VolumeInfoDTO volumeInfoDto = transferContentManager.getVolumeInfo(volumeName);
			return volumeInfoDto;
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
