package com.eunwoosoft.comm.util;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.eunwoosoft.comm.batch.service.ComBatchService;

public class AutoSend2 extends QuartzJobBean{
	private static int count=0;
	private int timeout;
	
	@Resource(name="comBatchService")
	private ComBatchService comBatchService;
	
	public void setTimeout(int timeout){
		this.timeout = timeout;
	}

	public void setComBatchService(ComBatchService comBatchService){
		this.comBatchService = comBatchService;
	}
	
	//5분 주기
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		
		//System.out.println("================>>>>>>>>>AutoSend2 start");
		comBatchService.bereNotiClose();
		comBatchService.payClose();
		//System.out.println("================>>>>>>>>>AutoSend2 end");
	}
	
}
