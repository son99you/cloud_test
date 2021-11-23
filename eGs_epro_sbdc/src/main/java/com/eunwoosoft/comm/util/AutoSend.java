package com.eunwoosoft.comm.util;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import sun.util.logging.resources.logging;

import com.eunwoosoft.comm.batch.service.ComBatchService;

public class AutoSend extends QuartzJobBean{
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
		//comBatchService.plrEstpcCrtn();
		//comBatchService.bidOpenDo();
        //System.out.println("================>>>>>>>>>AutoSend start");
        //comBatchService.dailyBatchDo();
		//System.out.println("================>>>>>>>>>AutoSend end");
		
	}
	
}
